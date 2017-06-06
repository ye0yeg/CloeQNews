package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.NewsDetailAdapter;

/**
 * Created by Administrator on 6/6/2017.
 */
public class NewsDetailFragment extends Fragment {

    @BindView(R.id.rv_new_detail)
    RecyclerView tvNewDetail;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    private NewsDetailAdapter newsDetailAdapter;

    public NewsDetailFragment(String type) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail,null);
        //界面，现在配置适配器


        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
