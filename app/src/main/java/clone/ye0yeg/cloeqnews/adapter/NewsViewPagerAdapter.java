package clone.ye0yeg.cloeqnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import clone.ye0yeg.cloeqnews.fragment.NewsDetailFragment;

/**
 * Created by Administrator on 6/6/2017.
 */
public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
    String[] types;

    public NewsViewPagerAdapter(FragmentManager fm, String[] types) {
        super(fm);
        this.types = types;
    }

    @Override
    public Fragment getItem(int position) {
        //详情的Fragment
//        return new Fragment()types[position];
        return new NewsDetailFragment(types[position]);
    }

    @Override
    public int getCount() {
        return types == null ? 0 : types.length;
    }
}
