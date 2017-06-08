package clone.ye0yeg.cloeqnews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import clone.ye0yeg.cloeqnews.utils.Utils;

/**
 * Created by Administrator on 6/5/2017.
 */

public class TodayFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(Utils.getContext());
        tv.setText("TodayFragment");
        tv.setTextColor(Color.RED);
        return tv;
    }
}
