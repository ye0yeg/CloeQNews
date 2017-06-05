package clone.ye0yeg.cloeqnews.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.utils.SPUtils;

/**
 * Created by Administrator on 6/5/2017.
 * 需要实现Animation
 * Theme的保存和切换
 *
 */
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.iv_splash_logo)
    ImageView iv_logo;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对于Theme的控制
        SPUtils utils = new SPUtils("theme_id");
        int theme_id = utils.getInt("theme_id", R.style.AppTheme);
        setTheme(theme_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        //对logo进行动画集操作
        set = new AnimatorSet();
        ObjectAnimator tX = ObjectAnimator.ofFloat(iv_logo,"translationX",600,0);
        ObjectAnimator tY = ObjectAnimator.ofFloat(iv_logo,"translationY", -100, 90, -80, 70, -60, 50);
        set.playTogether(tX,tY);
        set.setDuration(2000);
        set.start();
        //添加监听事件，When动画完毕需要做什么
        addListener();
    }

    private void addListener() {
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    Thread.sleep(2000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
