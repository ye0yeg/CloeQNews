package clone.ye0yeg.cloeqnews.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.bean.JokerBean;

/**
 * Created by Administrator on 6/9/2017.
 */
public class JokeAdapter extends BaseQuickAdapter<JokerBean.ResultBean.DataBean, BaseViewHolder> {

    public JokeAdapter(List<JokerBean.ResultBean.DataBean> data) {
        super(R.layout.item_joke);
    }

    @Override
    protected void convert(BaseViewHolder helper, JokerBean.ResultBean.DataBean item) {
        helper.setText(R.id.tv_joke_content,item.getContent());
        helper.setText(R.id.tv_joke_date,item.getUpdatetime());
    }
}
