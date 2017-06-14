package clone.ye0yeg.cloeqnews.net;

import clone.ye0yeg.cloeqnews.bean.JokerBean;
import clone.ye0yeg.cloeqnews.bean.NewsDataBean;
import clone.ye0yeg.cloeqnews.bean.RobotBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 6/7/2017.
 */

public interface QNewsService {

    public static final String DESC = "desc"; // 指定时间之前发布的
    public static final String ASC = "asc";   // 指定时间之后发布的
    /**
     * 根据 新闻类型 获取新闻数据
     *
     * @param type 新闻的类型
     * @return 查询结束 返回 数据的 被观察者
     */
    // http://v.juhe.cn/toutiao/index?key=d78b502268f7456b79fbe7228cecdd46
    @GET("toutiao/index?key=d78b502268f7456b79fbe7228cecdd46")
    Observable<NewsDataBean> getNewsData(
            @Query("type") String type
    );

    /**
     * @param time     要指定查询的时间
     * @param page     查询的页数
     * @param pagesize 一页数据显示的条数
     * @param sort     判断是在指定时间之前还是之后
     *                 {@value DESC 指定之前},{@value ASC 指定之后}
     * @return 查询结束返回的被观察者
     */
    // http://japi.juhe.cn/joke/content/list.from?key=ae240f7fba620fc370b803566654949e&page=1&pagesize=5&sort=desc
    @GET("list.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokerBean> getAssignJokeData(
            @Query("time") long time,
            @Query("page") int page,
            @Query("pagesize") int pagesize,
            @Query("sort") String sort
    );

    /**
     * @param page      查询的页数
     * @param pagesize  一页数据显示的条数
     * @return          查询结束返回的被观察者
     */
    // http://japi.juhe.cn/joke/content/text.from?key=ae240f7fba620fc370b803566654949e
    @POST("text.from?key=ae240f7fba620fc370b803566654949e")
        Observable<JokerBean> getCurrentJokeData(
            @Query("page") int page,
            @Query("pagesize") int pagesize
    );
    /**
     * 根据 日期 获取历史上的今天 数据
     *
     * @param date  当前日期
     * @return      查询结束 返回 历史上今天 数据 的被观察者
     */
    // http://v.juhe.cn/todayOnhistory/queryEvent.php?key=f5f7d655ef148f6bb777c80167f7f6de
    @GET("todayOnhistory/queryEvent.php?key=f5f7d655ef148f6bb777c80167f7f6de")
    Observable<TodayOfHistoryBean> getTodayOfHistoryData(
            @Query("date") String date
    );

    /**
     * 根据发送 消息内容， 回复
     *
     * @param info 发送的消息
     * @return  接收到发送的数据，然后回复数据的 被观察者
     */
    // http://op.juhe.cn/robot/index?key=98b8f13ededd2f7e1d593819a6bb3639
    @GET("index?key=98b8f13ededd2f7e1d593819a6bb3639")
    Observable<RobotBean> getQARobotData(
            @Query("info") String info
    );



}
