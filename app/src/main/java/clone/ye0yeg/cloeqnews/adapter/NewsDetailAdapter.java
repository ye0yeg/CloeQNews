package clone.ye0yeg.cloeqnews.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.bean.NewsDataBean;

/**
 * Created by Administrator on 6/6/2017.
 */
public class NewsDetailAdapter extends BaseQuickAdapter<NewsDataBean.ResultBean.DataBean, BaseViewHolder> {

    public NewsDetailAdapter() {
        super(R.layout.item_news_detail);
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsDataBean.ResultBean.DataBean dataBean) {
        holder.setText(R.id.tv_news_detail_title,dataBean.getDate());
        holder.setText(R.id.tv_news_detail_author_name,dataBean.getAuthor_name());
        holder.setText(R.id.tv_news_detail_date,dataBean.getDate());
        holder.addOnClickListener(R.id.ll_news_detail);
        Glide.with(mContext)
                .load(dataBean.getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .into((ImageView) holder.getView(R.id.iv_news_detail_pic));
    }
}
