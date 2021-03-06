package clone.ye0yeg.cloeqnews.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.fragment.AboutFragment;
import clone.ye0yeg.cloeqnews.fragment.JokeFragment;
import clone.ye0yeg.cloeqnews.fragment.NewsFragment;
import clone.ye0yeg.cloeqnews.fragment.RobatFragment;
import clone.ye0yeg.cloeqnews.fragment.TodayFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.nv_left)
    NavigationView nv_left;
    @BindView(R.id.dl_activity_main)
    DrawerLayout dlActivityMain;

    private Fragment currentFragment;
    private BottomBar bottombar;

    private NewsFragment newsFragment;
    private JokeFragment jokeFragment;
    private TodayFragment todayFragment;
    private RobatFragment robatFragment;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在sp中对主题进行读取和设置
        setContentView(R.layout.activity_main);
        retrieveFragment();
        ButterKnife.bind(this);
        //初始化Fragment

        //对NewsFragment进行显示

        //设置Navigation图片

        //底部BottomBar事件


        //设置事件
        initEvent();
        //All I need Is News Page

        nv_left.setCheckedItem(R.id.nav_news);
        nv_left.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                nv_left.setCheckedItem(item.getItemId());
                closeDrawerLayout();
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        bottombar.selectTabAtPosition(0, true);
                        closeDrawerLayout();
                        break;
                    case R.id.nav_duanzi:
                        bottombar.selectTabAtPosition(1, true);
                        closeDrawerLayout();
                        break;
                    case R.id.nav_today_of_history:
                        bottombar.selectTabAtPosition(2, true);
                        closeDrawerLayout();
                        break;
                    case R.id.nav_robot:
                        bottombar.selectTabAtPosition(3, true);
                        closeDrawerLayout();
                        break;
                    case R.id.nav_other:
                        bottombar.selectTabAtPosition(4, true);
                        closeDrawerLayout();
                        break;
                }

                return false;
            }


        });

    }


    private void initEvent() {
        bottombar = (BottomBar) findViewById(R.id.bottomBar);
        bottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_news:
                        if (newsFragment == null) newsFragment = new NewsFragment();
                        switchFragment(newsFragment);
                        break;
                    case R.id.tab_joke:
                        if (jokeFragment == null) jokeFragment = new JokeFragment();
                        switchFragment(jokeFragment);
                        break;
                    case R.id.tab_today:
                        if (todayFragment == null) todayFragment = new TodayFragment();
                        switchFragment(todayFragment);
                        break;
                    case R.id.tab_robot:
                        if (robatFragment == null) robatFragment = new RobatFragment();
                        switchFragment(robatFragment);
                        break;
                    case R.id.tab_about:
                        if (aboutFragment == null) aboutFragment = new AboutFragment();
                        switchFragment(aboutFragment);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void retrieveFragment() {
        FragmentManager manager = getSupportFragmentManager();
        /*
         * 可以不用找到这个
         */
//        newsFragment = (NewsFragment) manager.findFragmentByTag("NewsFragment");
//        jokeFragment = (JokeFragment) manager.findFragmentByTag("JokeFragment");
//        jokeFragment = (JokeFragment) manager.findFragmentByTag("JokeFragment");
//        jokeFragment = (JokeFragment) manager.findFragmentByTag("JokeFragment");
//        jokeFragment = (JokeFragment) manager.findFragmentByTag("JokeFragment");
    }

    /**
     * 切换Fragment的显示
     *
     * @param target 要切换的 Fragment
     */
    private void switchFragment(Fragment target) {

        // 如果当前的fragment 就是要替换的fragment 就直接return
        if (currentFragment == target) return;

        // 获得 Fragment 事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // 如果当前Fragment不为空，则隐藏当前的Fragment
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        // 如果要显示的Fragment 已经添加了，那么直接 show
        if (target.isAdded()) {
            transaction.show(target);
        } else {
            // 如果要显示的Fragment没有添加，就添加进去
            transaction.add(R.id.fl_content, target, target.getClass().getName());
        }

        // 事务进行提交
        transaction.commit();

        //并将要显示的Fragment 设为当前的 Fragment
        currentFragment = target;
    }
    //左侧菜单事件

    /**
     * 关闭左侧 侧滑菜单
     */
    private void closeDrawerLayout() {
        if (dlActivityMain.isDrawerOpen(Gravity.LEFT)) {
            dlActivityMain.closeDrawers();
        }
    }
}
