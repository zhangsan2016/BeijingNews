package beijinnews.example.ldgd.beijingnews.menudetaipager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.URL;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.View.HorizontalScrollViewPager;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.domain.TabDetailPagerBase;
import beijinnews.example.ldgd.beijingnews.menudetaipager.NewsMenuDetailPager;
import beijinnews.example.ldgd.beijingnews.utils.Constants;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/6/5.
 * 页签详情页面
 */

public class TabDetailPager extends MenuDetaiBasePager {

    private NewCenterPagerBase.DataBean.ChildrenBean childrenData;
    private HorizontalScrollViewPager viewpager;
    private TextView textView;
    private LinearLayout ll_point_group;
    /**
     * 头部新闻集合
     */
    List<TabDetailPagerBase.DataBean.TopnewsBean> topnews;

    private String url;

    private ListView newsListView;
    private List<TabDetailPagerBase.DataBean.NewsBean> news;
    /**
     * 之前点高亮显示的位置
     */
    private int prePosition;
    private ImageOptions imageOptions;

    public TabDetailPager(Context context, NewCenterPagerBase.DataBean.ChildrenBean childrenData) {
        super(context);
        this.childrenData = childrenData;

        // 设置图片加载参数
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(100), DensityUtil.dip2px(100))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.news_pic_default)
                .setFailureDrawableId(R.drawable.news_pic_default)
                .build();
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tabdetail_pager, null);
        newsListView = (ListView) view.findViewById(R.id.tab_detail_listview);

        // xutils框架绑定数据
        //x.view().inject(TabDetailPager.this, view);


        View topNewsView = View.inflate(context, R.layout.topnews, null);
        viewpager = (HorizontalScrollViewPager) topNewsView.findViewById(R.id.top_news_viewpager);
        textView = (TextView) topNewsView.findViewById(R.id.tv_top_news_title);
        ll_point_group = (LinearLayout) topNewsView.findViewById(R.id.ll_top_news_point_group);

        //  把顶部轮播图部分视图，以头的方式添加到ListView中
        newsListView.addHeaderView(topNewsView);


        return view;
    }

    @Override
    public void initData() {
        url = Constants.BASE_URL + childrenData.getUrl();
        LogUtil.e("URL == " + url);
        getDataFromNet();

        // 初始化头部滚动图片中定位圆点的位置
        prePosition = 0;
    }

    public void getDataFromNet() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("TabDetailPager 页面数据请求成功 == ");
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("TabDetailPager 页面数据请求失败 == " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("TabDetailPager 页面数据请求onCancelled == " + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("TabDetailPager 页面数据请求onFinished == ");
            }
        });
    }

    /**
     * 处理数据
     *
     * @param json
     */
    private void processData(String json) {
        TabDetailPagerBase tabDetailPagerBase = parsedJson(json);
        LogUtil.e("TabDetailPager  解析json数据成功 === " + tabDetailPagerBase.getData().getNews().get(0).getTitle());

        //顶部轮播图数据
        topnews = tabDetailPagerBase.getData().getTopnews();
        // 设置滚动新闻标题
        textView.setText(topnews.get(prePosition).getTitle());


        // 添加定位圆点
          addPoint();
        // 监听viewpager变化，修改文本内容
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());


        viewpager.setAdapter(new TabDetailPagerTopNewsAdapter());


        //准备ListView对应的集合数据
        news = tabDetailPagerBase.getData().getNews();
        TabDetailPagerListAdapter tabDetailPagerListAdapter = new TabDetailPagerListAdapter();
        newsListView.setAdapter(tabDetailPagerListAdapter);


    }

    /**
     * 添加定位点
     */
    private void addPoint() {
        // 清空所有红点
        ll_point_group.removeAllViews();
        for (int i = 0; i < topnews.size() ; i++) {

            ImageView imageview = new ImageView(context);
            // 设置背景选择器
            imageview.setBackgroundResource(R.drawable.select_tab_detail_point);
            // 设置布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5),DensityUtil.dip2px(5));
            imageview.setLayoutParams(params);

            // 初始化激活状态（默认第一个）
            if(i == 0){
                imageview.setEnabled(true);
            }else{
                imageview.setEnabled(false);
                params.leftMargin = DensityUtil.dip2px(8);
            }
            ll_point_group.addView(imageview);

        }

    }

    /**
     * 解析json数据
     *
     * @param json
     */
    private TabDetailPagerBase parsedJson(String json) {
        return new Gson().fromJson(json, TabDetailPagerBase.class);
    }

    /**
     * 新闻头Pager适配器
     */
    private class TabDetailPagerTopNewsAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            //设置图片默认北京
            imageView.setBackgroundResource(R.drawable.home_scroll_default);
            // 拉伸xy轴
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //把图片添加到容器(ViewPager)中
            container.addView(imageView);

            TabDetailPagerBase.DataBean.TopnewsBean topnewsBean = topnews.get(position);
            //图片请求地址
            String imageUrl = Constants.BASE_URL + topnewsBean.getTopimage();

            //联网请求图片
           //   x.image().bind(imageView, imageUrl,imageOptions);
           // x.image().bind(imageView, imageUrl);

            //请求图片使用glide
            Glide.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.news_pic_default)
                    .error(R.drawable.news_pic_default)
                    .into(imageView);


            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }
    }

    class TabDetailPagerListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_tabdetail_pager, null);

                viewHolder = new ViewHolder();
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            TabDetailPagerBase.DataBean.NewsBean newsBean = news.get(position);
            //请求图片XUtils3
            String imageUrl = Constants.BASE_URL + newsBean.getListimage();
            // x.image().bind(viewHolder.iv_icon, imageUrl,imageOptions);
          /*  GlideApp.with(myFragment)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.loading_spinner)
                    .into(myImageView);*/

            //请求图片使用glide
            Glide.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.news_pic_default)
                    .error(R.drawable.news_pic_default)
                    .into(viewHolder.iv_icon);


            // 设置标题
            viewHolder.tv_title.setText(newsBean.getTitle());
            // 设置更新时间
            viewHolder.tv_time.setText(newsBean.getPubdate());

            return convertView;
        }


        private class ViewHolder {
            ImageView iv_icon;
            TextView tv_title;
            TextView tv_time;

        }
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置文本
            textView.setText(topnews.get(position).getTitle());

            LogUtil.e("prePosition = " + prePosition);

            // 设置圆点状态
            // 把之前的圆点变灰
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            // 设置当前红色圆点
            ll_point_group.getChildAt(position).setEnabled(true);
       /*     for (int i = 0; i < ll_point_group.getChildCount(); i++) {
                if(i == position){
                    ll_point_group.getChildAt(i).setEnabled(true);
                }else{
                    ll_point_group.getChildAt(i).setEnabled(false);
                }
            }*/

            prePosition = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
