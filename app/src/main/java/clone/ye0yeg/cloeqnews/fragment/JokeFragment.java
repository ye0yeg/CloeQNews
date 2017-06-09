package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.JokeAdapter;

/**
 * Created by Administrator on 6/5/2017.
 */

public class JokeFragment extends Fragment {

    @BindView(R.id.rv_joke)
    RecyclerView rv_joker;
    @BindView(R.id.tb_fragment_joker_toolbar)
    Toolbar tb_jokertoolbar;
    @BindView(R.id.srl_fragment_joker_srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.tv_fragment_joker_reload)
    TextView tv_reload;
    @BindView(R.id.tv_fragment_joker_faild)
    TextView tv_faild;
    @BindView(R.id.tv_fragment_joker_loading)
    TextView tv_loading;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joker,null);
        ButterKnife.bind(this,view);
        //获取数据

        //适配器加载内容
        JokeAdapter jokerAdapter =new JokeAdapter();
        return view;
    }
}
