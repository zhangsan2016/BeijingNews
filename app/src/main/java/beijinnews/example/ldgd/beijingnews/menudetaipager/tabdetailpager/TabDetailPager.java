package beijinnews.example.ldgd.beijingnews.menudetaipager.tabdetailpager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.View.HorizontalScrollViewPager;
import beijinnews.example.ldgd.beijingnews.View.RefreshListView;
import beijinnews.example.ldgd.beijingnews.activity.NewsDetailActivity;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.domain.TabDetailPagerBase;
import beijinnews.example.ldgd.beijingnews.utils.CacheUtils;
import beijinnews.example.ldgd.beijingnews.utils.Constants;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

import static beijinnews.example.ldgd.beijingnews.utils.CacheUtils.getString;

/**
 * Created by ldgd on 2017/6/5.
 * 页签详情页面
 */

public class TabDetailPager extends MenuDetaiBasePager {

    public static final String READ_ARRAY_ID = "read_array_id";

    private NewCenterPagerBase.DataBean.ChildrenBean childrenData;
    private HorizontalScrollViewPager viewpager;
    private TextView textView;
    private LinearLayout ll_point_group;
    /**
     * 头部新闻集合
     */
    List<TabDetailPagerBase.DataBean.TopnewsBean> topnews;

    private String url;

    private RefreshListView newsListView;
    private List<TabDetailPagerBase.DataBean.NewsBean> news;
    /**
     * 之前点高亮显示的位置
     */
    private int prePosition;
    private ImageOptions imageOptions;
    /**
     * 加载更多
     */
    private boolean isLoadMore;
    /**
     * 下一页的联网路径
     */
    private String moreUrl;
    private TabDetailPagerListAdapter tabDetailPagerListAdapter;

    private Handler internalHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //切换ViewPager的下一个页面
            int item = (viewpager.getCurrentItem() + 1) % topnews.size();
            viewpager.setCurrentItem(item);
            internalHandler.postDelayed(new MyRunnable(), 4000);

        }
    };
    private boolean isDragging = false;

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
        newsListView = (RefreshListView) view.findViewById(R.id.tab_detail_listview);

        // xutils框架绑定数据
        //x.view().inject(TabDetailPager.this, view);


        View topNewsView = View.inflate(context, R.layout.topnews, null);
        viewpager = (HorizontalScrollViewPager) topNewsView.findViewById(R.id.top_news_viewpager);
        textView = (TextView) topNewsView.findViewById(R.id.tv_top_news_title);
        ll_point_group = (LinearLayout) topNewsView.findViewById(R.id.ll_top_news_point_group);

        //  把顶部轮播图部分视图，以头的方式添加到ListView中
        //  newsListView.addHeaderView(topNewsView);
        newsListView.addTopNewsView(topNewsView);

        // 下拉刷新
        newsListView.setOnRefreshListener(new MyOnRefreshListener());
        //设置ListView的item的点击监听
        newsListView.setOnItemClickListener(new MyOnItemClickListener());

        return view;
    }


    private class MyOnRefreshListener implements RefreshListView.OnRefreshListener {

        @Override
        public void onPullDownRefresh() {
            //  Toast.makeText(context,"回调成功！",Toast.LENGTH_LONG).show();
            getDataFromNet();

        }

        @Override
        public void onLoadMore() {
            if (TextUtils.isEmpty(moreUrl)) {

                Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                newsListView.onRefreshFinish(false);

            } else {
                getMoreDataFromNet();
            }

        }
    }


    /**
     * 获取更多
     */
    private void getMoreDataFromNet() {

        RequestParams requestParams = new RequestParams(moreUrl);
        requestParams.setConnectTimeout(4000);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("加载更多 onSuccess== " + result);

                // 隐藏加载更多控件
                newsListView.onRefreshFinish(false);
                // 加载更多
                isLoadMore = true;
                // 解析数据
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("加载更多 onError== " + ex.getMessage());
                newsListView.onRefreshFinish(false);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("加载更多 onCancelled== " + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("加载更多 onFinished== ");
            }
        });

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
        requestParams.setConnectTimeout(4000);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.e("TabDetailPager 页面数据请求成功 == ");
                processData(result);
                // 隐藏下拉刷新控件-重写显示数据，更新时间
                newsListView.onRefreshFinish(true);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("TabDetailPager 页面数据请求失败 == " + ex.getMessage());

                //  隐藏下拉刷新控件 - 不更新时间，只是隐藏
                newsListView.onRefreshFinish(false);
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
        // 解析json数据
        TabDetailPagerBase tabDetailPagerBase = parsedJson(json);
        LogUtil.e("TabDetailPager  解析json数据成功 === " + tabDetailPagerBase.getData().getNews().get(0).getTitle());


        if (TextUtils.isEmpty(tabDetailPagerBase.getData().getMore())) {
            moreUrl = "";

        } else {
            moreUrl = Constants.BASE_URL + tabDetailPagerBase.getData().getMore();
        }

        LogUtil.e("加载更多的地址===" + moreUrl);

        // 默认加加载更多
        if (!isLoadMore) {
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
            tabDetailPagerListAdapter = new TabDetailPagerListAdapter();
            newsListView.setAdapter(tabDetailPagerListAdapter);
        } else {
            // 加载更多
            isLoadMore = false;
            //添加到原来的集合中
            news.addAll(tabDetailPagerBase.getData().getNews());
            //刷新适配器
            tabDetailPagerListAdapter.notifyDataSetChanged();


        }

        // 设置轮播图效果
        //是把消息队列所有的消息和回调移除
        internalHandler.removeCallbacksAndMessages(null);
        internalHandler.postDelayed(new MyRunnable(), 4000);


    }

    /**
     * 添加定位点
     */
    private void addPoint() {
        // 清空所有红点
        ll_point_group.removeAllViews();
        for (int i = 0; i < topnews.size(); i++) {

            ImageView imageview = new ImageView(context);
            // 设置背景选择器
            imageview.setBackgroundResource(R.drawable.select_tab_detail_point);
            // 设置布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5), DensityUtil.dip2px(5));
            imageview.setLayoutParams(params);

            // 初始化激活状态（默认第一个）
            if (i == 0) {
                imageview.setEnabled(true);
            } else {
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


            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:  // 按下
                            internalHandler.removeCallbacksAndMessages(null);
                            LogUtil.e("按下");
                            break;
                        case MotionEvent.ACTION_UP: // 离开
                            LogUtil.e("离开");
                            internalHandler.removeCallbacksAndMessages(null);
                            internalHandler.postDelayed(new MyRunnable(), 4000);

                            break;
                /*        case MotionEvent.ACTION_CANCEL:  // 取消  BUG：手动滑动视图的时候会执行这个操作
                            LogUtil.e("取消");
                            internalHandler.removeCallbacksAndMessages(null);
                            internalHandler.postDelayed(new MyRunnable(), 4000);
                            break;*/
                    }
                    return true;
                }
            });


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

            String idArray = CacheUtils.getString(context, READ_ARRAY_ID);
            if (idArray.contains(newsBean.getId() + "")) {
                viewHolder.tv_title.setTextColor(Color.RED);
            } else {
                viewHolder.tv_title.setTextColor(Color.BLACK);
            }

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
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                LogUtil.e("拖拽");
                isDragging = true;
                internalHandler.removeCallbacksAndMessages(null);
            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                LogUtil.e("惯性");
                isDragging = false;
                internalHandler.removeCallbacksAndMessages(null);
                internalHandler.postDelayed(new MyRunnable(), 4000);

            } else if (state == ViewPager.SCROLL_STATE_SETTLING && isDragging) {
                LogUtil.e("静止");
                isDragging = false;
                internalHandler.removeCallbacksAndMessages(null);
                internalHandler.postDelayed(new MyRunnable(), 4000);

            }

        }
    }

    /**
     * listView Item 点击事件
     */
    private class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // 标定点击标题变灰
            int realPosition = position - 1;
            TabDetailPagerBase.DataBean.NewsBean newsData = news.get(realPosition);

            //1,取出保存的id集合
            String idArray = getString(context, READ_ARRAY_ID);

            //2，判断是否存在，如果不存在，才保存，并且刷新适配器
            if (!idArray.contains(newsData.getId() + "")) {
                CacheUtils.putString(context, READ_ARRAY_ID, idArray + newsData.getId());
                //刷新适配器
                tabDetailPagerListAdapter.notifyDataSetChanged();//getCount-->getView
            }

            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra("url", Constants.BASE_URL + newsData.getUrl());
            context.startActivity(intent);

        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            internalHandler.sendEmptyMessage(0);
        }
    }
}
