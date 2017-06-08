package clone.ye0yeg.cloeqnews.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.utils.LogUtils;
import clone.ye0yeg.cloeqnews.utils.Utils;

/**
 * Created by Administrator on 6/8/2017.
 */

public class ShowNewsActivity extends AppCompatActivity {
    @BindView(R.id.ll_shownews_content)
    LinearLayout ll_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringBuilder wholeString =new StringBuilder() ;
        setContentView(R.layout.activity_shownews);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> content = bundle.getStringArrayList("content");
        ArrayList<String>  picUrl = bundle.getStringArrayList("picurl");
        LogUtils.s(content.size()+"asdsad"+picUrl.size());
        ImageView iv_pic = new ImageView(Utils.getContext());
        TextView tv_content= new TextView(Utils.getContext());
        for(int a = 0; a<content.size();a++){
            wholeString.append(content.get(a));
        }
        tv_content.setTextSize(30);
        tv_content.setTextColor(Color.BLACK);
        tv_content.setText(wholeString.toString().trim());

        ll_content.addView(tv_content);
    }
}
