package clone.ye0yeg.cloeqnews.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import clone.ye0yeg.cloeqnews.bean.RobotMSGBean;

/**
 * Created by Administrator on 6/13/2017.
 * AI Studip people 完成
 *
 */
public class RobotAdapter extends MultiItemTypeAdapter<RobotMSGBean> {

    private Context context;
    private List<RobotMSGBean> datas;

    /*
    * 构造函数中，传入context， 和data
    * */
    public RobotAdapter(Context context, List<RobotMSGBean> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
    }

    /*
    * 添加数据到适配器中
    * */
    public void addDataToAdapter(RobotMSGBean bean) {
        if (datas != null) {
            datas.add(bean);
        }
    }
}
