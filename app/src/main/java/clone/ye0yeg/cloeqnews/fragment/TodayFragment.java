package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.TodayAdapter;
import clone.ye0yeg.cloeqnews.base.Constant;
import clone.ye0yeg.cloeqnews.net.QClitent;
import clone.ye0yeg.cloeqnews.net.QNewsService;
import clone.ye0yeg.cloeqnews.net.TodayOfHistoryBean;
import clone.ye0yeg.cloeqnews.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 6/5/2017.
 */

public class TodayFragment extends Fragment {

    @BindView(R.id.rv_today_content)
    RecyclerView rv_content;
    @BindView(R.id.tb_fragment_today_toolbar)
    Toolbar tb_toolbar;
    @BindView(R.id.fab_fragment_today_tabtotop)
    FloatingActionButton fab_tab;
    private TodayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, null);
        ButterKnife.bind(this, view);
        //适配器的配置
        adapter = new TodayAdapter();
        //设置动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        //设置日期时间
        Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        String param = month + "/" + day;

        rv_content.setAdapter(adapter);
        //这个是用来定义样式的
        rv_content.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rv_content.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //设置点击事件。用来显示新的窗口用来显示内容
//                Intent intent  = new Intent();
                Toast.makeText(getActivity(), "点击了" + position + "的项目", Toast.LENGTH_SHORT).show();
            }
        });
        //从网络获取数据开始
        QClitent.getInstance().create(QNewsService.class, Constant.BASE_JUHE_URL)
                .getTodayOfHistoryData(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TodayOfHistoryBean>() {
                    @Override
                    public void accept(TodayOfHistoryBean todayOfHistoryBean) throws Exception {
                        List<TodayOfHistoryBean.ResultBean> result = todayOfHistoryBean.getResult();
                        adapter.addData(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(Utils.getContext(), "Bad Requs", Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }
}
