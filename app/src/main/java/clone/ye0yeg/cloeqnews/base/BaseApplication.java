package clone.ye0yeg.cloeqnews.base;

import android.app.Application;

import clone.ye0yeg.cloeqnews.utils.Utils;

/**
 * Created by Administrator on 6/5/2017.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());

        //之后会初始化的数据有极光推送以及OkHttpClient
    }
}
