package clone.ye0yeg.cloeqnews.net;

import clone.ye0yeg.cloeqnews.bean.NewsDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 6/7/2017.
 */

public interface QNewsService {

    /**
     * 根据 新闻类型 获取新闻数据
     *
     * @param type  新闻的类型
     * @return      查询结束 返回 数据的 被观察者
     */
    // http://v.juhe.cn/toutiao/index?key=d78b502268f7456b79fbe7228cecdd46
    @GET("toutiao/index?key=d78b502268f7456b79fbe7228cecdd46")
    Observable<NewsDataBean> getNewsData(
            @Query("type") String type
    );

}
