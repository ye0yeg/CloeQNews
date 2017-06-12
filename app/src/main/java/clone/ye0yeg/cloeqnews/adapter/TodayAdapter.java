package clone.ye0yeg.cloeqnews.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.net.TodayOfHistoryBean;

/**
 * Created by Administrator on 6/12/2017.
 */
public class TodayAdapter extends BaseQuickAdapter<TodayOfHistoryBean.ResultBean, BaseViewHolder>{


    //item的layout
    public TodayAdapter() {
        super(R.layout.item_today);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayOfHistoryBean.ResultBean item) {
        helper.setText(R.id.tv_today_title, item.getTitle());
        helper.setText(R.id.tv_today_date,item.getDate());
        helper.addOnClickListener(R.layout.item_today);
    }
}
