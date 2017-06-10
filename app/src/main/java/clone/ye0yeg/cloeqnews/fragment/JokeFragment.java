package clone.ye0yeg.cloeqnews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.JokeAdapter;
import clone.ye0yeg.cloeqnews.base.Constant;
import clone.ye0yeg.cloeqnews.bean.JokerBean;
import clone.ye0yeg.cloeqnews.net.QClitent;
import clone.ye0yeg.cloeqnews.net.QNewsService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 6/5/2017.
 */

public class JokeFragment extends Fragment {

    @BindView(R.id.rv_joke)
    RecyclerView rv_joker;
    @BindView(R.id.tb_fragment_joker_toolbar)
    Toolbar tb_jokertoolbar;
    @BindView(R.id.srl_fragment_joker_srl)
    SwipeRefreshLayout srlJoker;
    @BindView(R.id.tv_fragment_joker_reload)
    TextView tv_reload;
    @BindView(R.id.tv_fragment_joker_faild)
    TextView tv_faild;
    @BindView(R.id.tv_fragment_joker_loading)
    TextView tv_loading;

    private JokeAdapter jokerAdapter;
    List<JokerBean.ResultBean.DataBean> mData;

    private int currentNum;
    private int totalNum = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joker, null);
        ButterKnife.bind(this, view);
        tv_loading.setVisibility(View.VISIBLE);
        tv_faild.setVisibility(View.GONE);
        tv_reload.setVisibility(View.GONE);
        //获取数据
        mData = new ArrayList<JokerBean.ResultBean.DataBean>();

        //适配器加载内容
        jokerAdapter = new JokeAdapter();
        jokerAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        //适配器和View
        rv_joker.setAdapter(jokerAdapter);
        rv_joker.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false));

        //第一次加载数据
        onFetch();

        srlJoker.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        srlJoker.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                upData();
            }
        });
        //下拉底部加载,加载更多
        jokerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rv_joker.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (currentNum >= totalNum) {
                            jokerAdapter.loadMoreEnd();
                        } else {
                            if (jokerAdapter.getItem(0) == null) {
                                return;
                            }
                            long unixtime = jokerAdapter.getItem(jokerAdapter.getItemCount() - 2).getUnixtime();
                            QClitent.getInstance().create(QNewsService.class, Constant.BASE_JOKE_URL)
                                    .getAssignJokeData(unixtime, 1, 5, QNewsService.DESC)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<JokerBean>() {
                                        @Override
                                        public void accept(JokerBean jokerBean) throws Exception {
                                            //获取数据
                                            List<JokerBean.ResultBean.DataBean> data = jokerBean.getResult().getData();
                                            jokerAdapter.addData(data);
                                            currentNum = totalNum;
                                            totalNum += 5;
                                            jokerAdapter.loadMoreComplete();
                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            jokerAdapter.loadMoreFail();
                                        }
                                    });
                        }


                    }
                }, 1000);
            }
        });

        return view;
    }

    private void onFetch() {
        upData();
    }

    private void upData() {
        tv_faild.setVisibility(View.GONE);
        tv_reload.setVisibility(View.GONE);
        tv_loading.setVisibility(View.GONE);

        srlJoker.setRefreshing(true);
        //获取数据
        QClitent.getInstance().create(QNewsService.class, Constant.BASE_JOKE_URL)
                .getCurrentJokeData(1, 8)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JokerBean>() {
                    @Override
                    public void accept(JokerBean jokerBean) throws Exception {
                        //获取数据
                        //加载数据
                        jokerAdapter.setNewData(jokerBean.getResult().getData());
                        srlJoker.setRefreshing(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //失败的界面
                        Toast.makeText(getActivity(), "获取数据失败!", Toast.LENGTH_SHORT)
                                .show();
                        srlJoker.setRefreshing(false);
                        tv_faild.setVisibility(View.VISIBLE);
                        tv_loading.setVisibility(View.GONE);
                        tv_reload.setVisibility(View.GONE);
                    }
                });
    }
}
