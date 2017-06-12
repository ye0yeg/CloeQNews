package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;

/**
 * Created by Administrator on 6/5/2017.
 */

public class AboutFragment extends Fragment {
    @BindView(R.id.iv_aboutfragment_bg)
    ImageView iv_bg;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);
        ButterKnife.bind(this, view);
        Glide.with(this)
                .load("http://img.17gexing.com/uploadfile/2016/07/2/20160725115727230.jpg")
                .centerCrop()
                .into(iv_bg);

        //可以为其他添加点击事件
        return view;
    }
}
