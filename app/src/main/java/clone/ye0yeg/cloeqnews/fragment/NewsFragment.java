package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import clone.ye0yeg.cloeqnews.R;

/**
 * Created by Administrator on 6/5/2017.
 */
/*
* News界面。设置新的界面
* */
public class NewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsdata,null);
        return view;
    }
}