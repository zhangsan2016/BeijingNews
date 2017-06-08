package beijinnews.example.ldgd.beijingnews.domain;

import java.util.List;

/**
 * Created by ldgd on 2017/6/8.
 */

public class TabDetailPagerBase {


    /**
     * retcode : 200
     * data : {"title":"体育","topnews":[{"id":147213,"title":"【国安伪球迷】冠军没了，掌声还在","topimage":"/static/images/2015/10/18/9/1994688361TZ1Q.jpg","url":"/static/html/2015/10/18/704D6E504E6B1E7D6F257D43.html","pubdate":"2015-10-18 02:29","comment":true,"commenturl":"/client/user/newComment/147213","type":"news","commentlist":"/static/api/news/10010/13/147213/comment_1.json"},{"id":147207,"title":"奥多姆同院病友抱怨被打扰","topimage":"/static/images/2015/10/17/63/1485924659MOYB.jpg","url":"/static/html/2015/10/17/774A6A54486D1B786B207A40.html","pubdate":"2015-10-17 12:16","comment":true,"commenturl":"/client/user/newComment/147207","type":"news","commentlist":"/static/api/news/10010/07/147207/comment_1.json"}],"topic":[],"news":[{"id":147118,"title":"上海赛：穆雷2-1逆转进8强","url":"/static/html/2015/10/16/714C6C5240651B7B6D277C49.html","listimage":"/static/images/2015/10/16/31/467283569K11.jpg","pubdate":"2015-10-16 08:48","comment":true,"commenturl":"/client/user/newComment/147118","type":"news","commentlist":"/static/api/news/10010/18/147118/comment_1.json"},{"id":147035,"title":"李毅再发声：走自己的路一笑而过","url":"/static/html/2015/10/15/704D6E504C6919786C247D45.html","listimage":"/static/images/2015/10/15/11/4672835639ZI.jpg","pubdate":"2015-10-15 09:56","comment":true,"commenturl":"/client/user/newComment/147035","type":"news","commentlist":"/static/api/news/10010/35/147035/comment_1.json"},{"id":147029,"title":"深度揭秘：足协为何不换佩兰?","url":"/static/html/2015/10/15/704D6A544A6F1170662F7D49.html","listimage":"/static/images/2015/10/15/28/46728356NIYP.jpg","pubdate":"2015-10-15 09:52","comment":true,"commenturl":"/client/user/newComment/147029","type":"news","commentlist":"/static/api/news/10010/29/147029/comment_1.json"},{"id":147027,"title":"35岁国安旧将加盟西乙B球队","url":"/static/html/2015/10/15/704D6B554C6919786C257D47.html","listimage":"/static/images/2015/10/15/22/46728356RF8W.jpg","pubdate":"2015-10-15 09:48","comment":true,"commenturl":"/client/user/newComment/147027","type":"news","commentlist":"/static/api/news/10010/27/147027/comment_1.json"},{"id":147026,"title":"书豪首发 黄蜂42分狂胜快船","url":"/static/html/2015/10/15/774A67594C691A7B6B227A41.html","listimage":"/static/images/2015/10/15/16/467283569142.jpg","pubdate":"2015-10-15 09:46","comment":true,"commenturl":"/client/user/newComment/147026","type":"news","commentlist":"/static/api/news/10010/26/147026/comment_1.json"},{"id":147025,"title":"名宿讽刺德佩：只有态度像巨星","url":"/static/html/2015/10/15/714C6B5541641B7A6E277C44.html","listimage":"/static/images/2015/10/15/97/467283568XRW.jpg","pubdate":"2015-10-15 09:44","comment":true,"commenturl":"/client/user/newComment/147025","type":"news","commentlist":"/static/api/news/10010/25/147025/comment_1.json"},{"id":147022,"title":"穆帅因批评裁判遭足总禁赛1场","url":"/static/html/2015/10/15/75486D534D6818796D247847.html","listimage":"/static/images/2015/10/15/89/467283568RMH.jpg","pubdate":"2015-10-15 09:41","comment":true,"commenturl":"/client/user/newComment/147022","type":"news","commentlist":"/static/api/news/10010/22/147022/comment_1.json"},{"id":147019,"title":"小德：盼冲击费德勒17冠纪录","url":"/static/html/2015/10/15/764B6D53486D1F7E662C7B4F.html","listimage":"/static/images/2015/10/15/76/46728356HPXR.jpg","pubdate":"2015-10-15 09:37","comment":true,"commenturl":"/client/user/newComment/147019","type":"news","commentlist":"/static/api/news/10010/19/147019/comment_1.json"},{"id":146944,"title":"国米曝将挖皇马1400万妖星","url":"/static/html/2015/10/14/764B68564165197168277B42.html","listimage":"/static/images/2015/10/14/26/467283569VUZ.jpg","pubdate":"2015-10-14 10:33","comment":true,"commenturl":"/client/user/newComment/146944","type":"news","commentlist":"/static/api/news/10010/44/146944/comment_1.json"},{"id":146942,"title":"米卢：这支国足只认识武磊郑智","url":"/static/html/2015/10/14/774A6E504C681E766A257A45.html","listimage":"/static/images/2015/10/14/5/46728356X8NC.jpg","pubdate":"2015-10-14 10:29","comment":true,"commenturl":"/client/user/newComment/146942","type":"news","commentlist":"/static/api/news/10010/42/146942/comment_1.json"},{"id":146941,"title":"中国香港1-0不丹超国足三分","url":"/static/html/2015/10/14/714C66584C681D7566297C40.html","listimage":"/static/images/2015/10/14/38/467283561ZQ8.jpg","pubdate":"2015-10-14 10:27","comment":true,"commenturl":"/client/user/newComment/146941","type":"news","commentlist":"/static/api/news/10010/41/146941/comment_1.json"},{"id":146940,"title":"上海赛：费德勒1-2首轮止步","url":"/static/html/2015/10/14/774A6A544B6F1B736D227D40.html","listimage":"/static/images/2015/10/14/38/46728356OYZO.jpg","pubdate":"2015-10-14 10:19","comment":true,"commenturl":"/client/user/newComment/146940","type":"news","commentlist":"/static/api/news/10010/40/146940/comment_1.json"},{"id":146934,"title":"霍华德解释不复出原因","url":"/static/html/2015/10/14/764B6C524165197168207B42.html","listimage":"/static/images/2015/10/14/30/467283561MCX.jpg","pubdate":"2015-10-14 10:13","comment":true,"commenturl":"/client/user/newComment/146934","type":"news","commentlist":"/static/api/news/10010/34/146934/comment_1.json"},{"id":146926,"title":"郁金香凋零！荷兰造32年之耻","url":"/static/html/2015/10/14/704D6F5140641F7769207D46.html","listimage":"/static/images/2015/10/14/21/4672835629A9.jpg","pubdate":"2015-10-14 10:04","comment":true,"commenturl":"/client/user/newComment/146926","type":"news","commentlist":"/static/api/news/10010/26/146926/comment_1.json"},{"id":146925,"title":"欧预赛：荷兰2-3无缘欧洲杯","url":"/static/html/2015/10/14/724F6E504F6B1D756D247F47.html","listimage":"/static/images/2015/10/14/68/46728356KJ9K.jpg","pubdate":"2015-10-14 10:02","comment":true,"commenturl":"/client/user/newComment/146925","type":"news","commentlist":"/static/api/news/10010/25/146925/comment_1.json"},{"id":146826,"title":"官网评10大得分后卫哈登领衔","url":"/static/html/2015/10/13/764B6C524F6B1E776E277B40.html","listimage":"/static/images/2015/10/13/31/46728356Z1HD.jpg","pubdate":"2015-10-13 10:09","comment":true,"commenturl":"/client/user/newComment/146826","type":"news","commentlist":"/static/api/news/10010/26/146826/comment_1.json"},{"id":146825,"title":"贝利：梅西是足坛近10年最佳","url":"/static/html/2015/10/13/764B68564C681B7268217B43.html","listimage":"/static/images/2015/10/13/34/46728356HFYI.jpg","pubdate":"2015-10-13 10:05","comment":true,"commenturl":"/client/user/newComment/146825","type":"news","commentlist":"/static/api/news/10010/25/146825/comment_1.json"},{"id":146824,"title":"荷兰内讧！范佩西冲突曼联新刀","url":"/static/html/2015/10/13/764B6C524A6E1B726A237B42.html","listimage":"/static/images/2015/10/13/98/46728356OI5Z.jpg","pubdate":"2015-10-13 10:02","comment":true,"commenturl":"/client/user/newComment/146824","type":"news","commentlist":"/static/api/news/10010/24/146824/comment_1.json"},{"id":146823,"title":"京媒：佩兰消极思想致国足输球","url":"/static/html/2015/10/13/704D6F514D69117869207D43.html","listimage":"/static/images/2015/10/13/70/46728356VIEP.jpg","pubdate":"2015-10-13 10:00","comment":true,"commenturl":"/client/user/newComment/146823","type":"news","commentlist":"/static/api/news/10010/23/146823/comment_1.json"},{"id":146822,"title":"詹皇14分 骑士负灰熊三连败","url":"/static/html/2015/10/13/75486D53486C1B726C257847.html","listimage":"/static/images/2015/10/13/73/46728356F6NF.jpg","pubdate":"2015-10-13 09:53","comment":true,"commenturl":"/client/user/newComment/146822","type":"news","commentlist":"/static/api/news/10010/22/146822/comment_1.json"}],"countcommenturl":"/client/content/countComment/","more":"/static/api/news/10010/list_4.json"}
     */

    private int retcode;
    private DataBean data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 体育
         * topnews : [{"id":147213,"title":"【国安伪球迷】冠军没了，掌声还在","topimage":"/static/images/2015/10/18/9/1994688361TZ1Q.jpg","url":"/static/html/2015/10/18/704D6E504E6B1E7D6F257D43.html","pubdate":"2015-10-18 02:29","comment":true,"commenturl":"/client/user/newComment/147213","type":"news","commentlist":"/static/api/news/10010/13/147213/comment_1.json"},{"id":147207,"title":"奥多姆同院病友抱怨被打扰","topimage":"/static/images/2015/10/17/63/1485924659MOYB.jpg","url":"/static/html/2015/10/17/774A6A54486D1B786B207A40.html","pubdate":"2015-10-17 12:16","comment":true,"commenturl":"/client/user/newComment/147207","type":"news","commentlist":"/static/api/news/10010/07/147207/comment_1.json"}]
         * topic : []
         * news : [{"id":147118,"title":"上海赛：穆雷2-1逆转进8强","url":"/static/html/2015/10/16/714C6C5240651B7B6D277C49.html","listimage":"/static/images/2015/10/16/31/467283569K11.jpg","pubdate":"2015-10-16 08:48","comment":true,"commenturl":"/client/user/newComment/147118","type":"news","commentlist":"/static/api/news/10010/18/147118/comment_1.json"},{"id":147035,"title":"李毅再发声：走自己的路一笑而过","url":"/static/html/2015/10/15/704D6E504C6919786C247D45.html","listimage":"/static/images/2015/10/15/11/4672835639ZI.jpg","pubdate":"2015-10-15 09:56","comment":true,"commenturl":"/client/user/newComment/147035","type":"news","commentlist":"/static/api/news/10010/35/147035/comment_1.json"},{"id":147029,"title":"深度揭秘：足协为何不换佩兰?","url":"/static/html/2015/10/15/704D6A544A6F1170662F7D49.html","listimage":"/static/images/2015/10/15/28/46728356NIYP.jpg","pubdate":"2015-10-15 09:52","comment":true,"commenturl":"/client/user/newComment/147029","type":"news","commentlist":"/static/api/news/10010/29/147029/comment_1.json"},{"id":147027,"title":"35岁国安旧将加盟西乙B球队","url":"/static/html/2015/10/15/704D6B554C6919786C257D47.html","listimage":"/static/images/2015/10/15/22/46728356RF8W.jpg","pubdate":"2015-10-15 09:48","comment":true,"commenturl":"/client/user/newComment/147027","type":"news","commentlist":"/static/api/news/10010/27/147027/comment_1.json"},{"id":147026,"title":"书豪首发 黄蜂42分狂胜快船","url":"/static/html/2015/10/15/774A67594C691A7B6B227A41.html","listimage":"/static/images/2015/10/15/16/467283569142.jpg","pubdate":"2015-10-15 09:46","comment":true,"commenturl":"/client/user/newComment/147026","type":"news","commentlist":"/static/api/news/10010/26/147026/comment_1.json"},{"id":147025,"title":"名宿讽刺德佩：只有态度像巨星","url":"/static/html/2015/10/15/714C6B5541641B7A6E277C44.html","listimage":"/static/images/2015/10/15/97/467283568XRW.jpg","pubdate":"2015-10-15 09:44","comment":true,"commenturl":"/client/user/newComment/147025","type":"news","commentlist":"/static/api/news/10010/25/147025/comment_1.json"},{"id":147022,"title":"穆帅因批评裁判遭足总禁赛1场","url":"/static/html/2015/10/15/75486D534D6818796D247847.html","listimage":"/static/images/2015/10/15/89/467283568RMH.jpg","pubdate":"2015-10-15 09:41","comment":true,"commenturl":"/client/user/newComment/147022","type":"news","commentlist":"/static/api/news/10010/22/147022/comment_1.json"},{"id":147019,"title":"小德：盼冲击费德勒17冠纪录","url":"/static/html/2015/10/15/764B6D53486D1F7E662C7B4F.html","listimage":"/static/images/2015/10/15/76/46728356HPXR.jpg","pubdate":"2015-10-15 09:37","comment":true,"commenturl":"/client/user/newComment/147019","type":"news","commentlist":"/static/api/news/10010/19/147019/comment_1.json"},{"id":146944,"title":"国米曝将挖皇马1400万妖星","url":"/static/html/2015/10/14/764B68564165197168277B42.html","listimage":"/static/images/2015/10/14/26/467283569VUZ.jpg","pubdate":"2015-10-14 10:33","comment":true,"commenturl":"/client/user/newComment/146944","type":"news","commentlist":"/static/api/news/10010/44/146944/comment_1.json"},{"id":146942,"title":"米卢：这支国足只认识武磊郑智","url":"/static/html/2015/10/14/774A6E504C681E766A257A45.html","listimage":"/static/images/2015/10/14/5/46728356X8NC.jpg","pubdate":"2015-10-14 10:29","comment":true,"commenturl":"/client/user/newComment/146942","type":"news","commentlist":"/static/api/news/10010/42/146942/comment_1.json"},{"id":146941,"title":"中国香港1-0不丹超国足三分","url":"/static/html/2015/10/14/714C66584C681D7566297C40.html","listimage":"/static/images/2015/10/14/38/467283561ZQ8.jpg","pubdate":"2015-10-14 10:27","comment":true,"commenturl":"/client/user/newComment/146941","type":"news","commentlist":"/static/api/news/10010/41/146941/comment_1.json"},{"id":146940,"title":"上海赛：费德勒1-2首轮止步","url":"/static/html/2015/10/14/774A6A544B6F1B736D227D40.html","listimage":"/static/images/2015/10/14/38/46728356OYZO.jpg","pubdate":"2015-10-14 10:19","comment":true,"commenturl":"/client/user/newComment/146940","type":"news","commentlist":"/static/api/news/10010/40/146940/comment_1.json"},{"id":146934,"title":"霍华德解释不复出原因","url":"/static/html/2015/10/14/764B6C524165197168207B42.html","listimage":"/static/images/2015/10/14/30/467283561MCX.jpg","pubdate":"2015-10-14 10:13","comment":true,"commenturl":"/client/user/newComment/146934","type":"news","commentlist":"/static/api/news/10010/34/146934/comment_1.json"},{"id":146926,"title":"郁金香凋零！荷兰造32年之耻","url":"/static/html/2015/10/14/704D6F5140641F7769207D46.html","listimage":"/static/images/2015/10/14/21/4672835629A9.jpg","pubdate":"2015-10-14 10:04","comment":true,"commenturl":"/client/user/newComment/146926","type":"news","commentlist":"/static/api/news/10010/26/146926/comment_1.json"},{"id":146925,"title":"欧预赛：荷兰2-3无缘欧洲杯","url":"/static/html/2015/10/14/724F6E504F6B1D756D247F47.html","listimage":"/static/images/2015/10/14/68/46728356KJ9K.jpg","pubdate":"2015-10-14 10:02","comment":true,"commenturl":"/client/user/newComment/146925","type":"news","commentlist":"/static/api/news/10010/25/146925/comment_1.json"},{"id":146826,"title":"官网评10大得分后卫哈登领衔","url":"/static/html/2015/10/13/764B6C524F6B1E776E277B40.html","listimage":"/static/images/2015/10/13/31/46728356Z1HD.jpg","pubdate":"2015-10-13 10:09","comment":true,"commenturl":"/client/user/newComment/146826","type":"news","commentlist":"/static/api/news/10010/26/146826/comment_1.json"},{"id":146825,"title":"贝利：梅西是足坛近10年最佳","url":"/static/html/2015/10/13/764B68564C681B7268217B43.html","listimage":"/static/images/2015/10/13/34/46728356HFYI.jpg","pubdate":"2015-10-13 10:05","comment":true,"commenturl":"/client/user/newComment/146825","type":"news","commentlist":"/static/api/news/10010/25/146825/comment_1.json"},{"id":146824,"title":"荷兰内讧！范佩西冲突曼联新刀","url":"/static/html/2015/10/13/764B6C524A6E1B726A237B42.html","listimage":"/static/images/2015/10/13/98/46728356OI5Z.jpg","pubdate":"2015-10-13 10:02","comment":true,"commenturl":"/client/user/newComment/146824","type":"news","commentlist":"/static/api/news/10010/24/146824/comment_1.json"},{"id":146823,"title":"京媒：佩兰消极思想致国足输球","url":"/static/html/2015/10/13/704D6F514D69117869207D43.html","listimage":"/static/images/2015/10/13/70/46728356VIEP.jpg","pubdate":"2015-10-13 10:00","comment":true,"commenturl":"/client/user/newComment/146823","type":"news","commentlist":"/static/api/news/10010/23/146823/comment_1.json"},{"id":146822,"title":"詹皇14分 骑士负灰熊三连败","url":"/static/html/2015/10/13/75486D53486C1B726C257847.html","listimage":"/static/images/2015/10/13/73/46728356F6NF.jpg","pubdate":"2015-10-13 09:53","comment":true,"commenturl":"/client/user/newComment/146822","type":"news","commentlist":"/static/api/news/10010/22/146822/comment_1.json"}]
         * countcommenturl : /client/content/countComment/
         * more : /static/api/news/10010/list_4.json
         */

        private String title;
        private String countcommenturl;
        private String more;
        private List<TopnewsBean> topnews;
        private List<?> topic;
        private List<NewsBean> news;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        public List<?> getTopic() {
            return topic;
        }

        public void setTopic(List<?> topic) {
            this.topic = topic;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class TopnewsBean {
            /**
             * id : 147213
             * title : 【国安伪球迷】冠军没了，掌声还在
             * topimage : /static/images/2015/10/18/9/1994688361TZ1Q.jpg
             * url : /static/html/2015/10/18/704D6E504E6B1E7D6F257D43.html
             * pubdate : 2015-10-18 02:29
             * comment : true
             * commenturl : /client/user/newComment/147213
             * type : news
             * commentlist : /static/api/news/10010/13/147213/comment_1.json
             */

            private int id;
            private String title;
            private String topimage;
            private String url;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTopimage() {
                return topimage;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }
        }

        public static class NewsBean {
            /**
             * id : 147118
             * title : 上海赛：穆雷2-1逆转进8强
             * url : /static/html/2015/10/16/714C6C5240651B7B6D277C49.html
             * listimage : /static/images/2015/10/16/31/467283569K11.jpg
             * pubdate : 2015-10-16 08:48
             * comment : true
             * commenturl : /client/user/newComment/147118
             * type : news
             * commentlist : /static/api/news/10010/18/147118/comment_1.json
             */

            private int id;
            private String title;
            private String url;
            private String listimage;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }
        }
    }
}
