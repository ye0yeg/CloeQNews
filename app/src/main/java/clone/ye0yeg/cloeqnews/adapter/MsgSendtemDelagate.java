package clone.ye0yeg.cloeqnews.adapter;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.bean.RobotMSGBean;

/**
 * Created by Administrator on 6/13/2017.
 */

public class MsgSendtemDelagate implements ItemViewDelegate<RobotMSGBean> {


    /*
    * 某个item界面
    * */
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_robot_send;
    }

    /*
    * ViewType
    * */
    @Override
    public boolean isForViewType(RobotMSGBean item, int position) {
        return item.getType() == RobotMSGBean.MSG_SEND;
    }

    /*
    * 界面
    * */
    @Override
    public void convert(ViewHolder holder, RobotMSGBean robotMSGBean, int position) {
        holder.setText(R.id.tv_msg_right, robotMSGBean.getMsg());
    }
}
