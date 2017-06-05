package clone.ye0yeg.cloeqnews.utils;

import android.content.Context;

/**
 * Created by Administrator on 6/5/2017.
 */

public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }


    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

}
