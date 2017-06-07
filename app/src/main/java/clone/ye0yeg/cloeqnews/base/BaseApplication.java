package clone.ye0yeg.cloeqnews.base;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import clone.ye0yeg.cloeqnews.utils.Utils;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 6/5/2017.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());

        //之后会初始化的数据有极光推送以及OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS).build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
