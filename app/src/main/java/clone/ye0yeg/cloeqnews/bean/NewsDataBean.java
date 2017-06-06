package clone.ye0yeg.cloeqnews.bean;

/**
 * Created by Administrator on 6/6/2017.
 */

public class NewsDataBean {
    public static class ResultBean {
        public static class DataBean {
            //News的数据在这里体现
            private String title;
            private String date;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;


        }
    }

}


