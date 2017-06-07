package clone.ye0yeg.cloeqnews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.xiawei.webviewlib.WebViewActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.NewsDetailAdapter;
import clone.ye0yeg.cloeqnews.base.BaseFragment;
import clone.ye0yeg.cloeqnews.base.Constant;
import clone.ye0yeg.cloeqnews.bean.NewsDataBean;
import clone.ye0yeg.cloeqnews.net.QClitent;
import clone.ye0yeg.cloeqnews.net.QNewsService;
import clone.ye0yeg.cloeqnews.utils.LogUtils;
import clone.ye0yeg.cloeqnews.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 6/6/2017.
 */
public class NewsDetailFragment extends BaseFragment {

    @BindView(R.id.rv_new_detail)
    RecyclerView rvNewDetail;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    String type;
    private Document doc;

    private NewsDetailAdapter newsDetailAdapter;

    public NewsDetailFragment(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, null);
        //界面，现在配置适配器
        ButterKnife.bind(this, view);
        newsDetailAdapter = new NewsDetailAdapter();
        newsDetailAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置下拉刷新,刷新方法
        srl.setColorSchemeColors(Color.RED, Color.RED);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData();
            }
        });
        //recycleView 数据初始化
        //适配器初始化
        rvNewDetail.setAdapter(newsDetailAdapter);
        rvNewDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNewDetail.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Utils.getContext(), ((NewsDataBean.ResultBean.DataBean) adapter.getItem(
                        position)).getUrl(), Toast.LENGTH_SHORT).show();
                LogUtils.s(((NewsDataBean.ResultBean.DataBean) adapter.getItem(
                        position)).getUrl());
                new Thread() {


                    @Override
                    public void run() {
                        super.run();
                        try {
                            doc = Jsoup.connect("http://mini.eastday.com/mobile/170607160224888.html").get();
                            String title = doc.body().toString();
                            article();

//                            LogUtils.s(title);


                        } catch (IOException e) {
                            e.printStackTrace();
                            LogUtils.s("报错了");
                        }
                    }
                }.start();
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                WebViewActivity.startUrl(getActivity(),
                        ((NewsDataBean.ResultBean.DataBean) adapter.getItem(
                                position)).getUrl());
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        return view;
    }

    private void article() {
        //div
        Elements ListDiv = doc.getElementsByAttributeValue("class", "J-article-content article-content");
        for (Element element :
                ListDiv) {
            Elements links = element.getElementsByTag("p");
            for (Element link : links) {
                String linkText = link.text().trim();
                System.out.println(linkText);
            }
        }


    }

    @Override
    public void fetchData() {
        updateData();
    }

    /*
    * 更新数据使用，updata数据
    * */
    private void updateData() {
        srl.setRefreshing(true);

        QClitent.getInstance().create(QNewsService.class, Constant.BASE_JUHE_URL)
                .getNewsData(type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsDataBean>() {
                    @Override
                    public void accept(NewsDataBean newsDataBean) throws Exception {
                        newsDetailAdapter.setNewData(newsDataBean.getResult().getData());
                        srl.setRefreshing(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        srl.setRefreshing(false);
                    }
                });
    }
}
