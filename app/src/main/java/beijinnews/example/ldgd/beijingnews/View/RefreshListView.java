package beijinnews.example.ldgd.beijingnews.View;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/6/15.
 * 自定义下拉刷新ListView
 */

public class RefreshListView extends ListView {

    /**
     * 下拉刷新和顶部轮播图
     */
    private LinearLayout headerView;
    /**
     * 下拉刷新控件
     */
    private LinearLayout ll_pull_down_refresh;
    private ImageView iv_arrow;
    private ProgressBar pb_status;
    private TextView tv_status;
    private TextView tv_time;
    /**
     * 下拉刷新控件的高
     */
    private int pullDownRefreshHeight;

    /**
     * 下拉刷新
     */
    private static final int PULL_DOWN_REFRESH = 0;

    /**
     * 手松刷新
     */
    private static final int RELEASE_REFRESH = 1;

    /**
     * 正在刷新
     */
    private static final int REFRESHING = 2;

    /**
     * 完成刷新
     */
    private static final int REFRESH_FINISH = 3;
    /**
     * 刷新状态
     */
    private int currentStatus;

    /**
     * 下拉旋转动画
     */
    private RotateAnimation upAnimation, downAnimation;
    /**
     * 回调
     */
    private OnRefreshListener myOnRefreshListener;
    /**
     * 加载更多控件高
     */
    private int footerViewHeight;
    /**
     * 加载更多控件
     */
    private View footerView;

    /**
     * 加载更多
     */
    private boolean isLoadMore;
    /**
     * 顶部轮播图部分
     */
    private View topNewsView;


    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化动画
        initAnimation();

        // 初始化下拉刷新界面
        initHeaderView(context);

        // 初始化加载更多
        initFooterView(context);

    }

    private void initFooterView(Context context) {
        footerView = View.inflate(context, R.layout.refrensh_footer, null);

        // 设置ListView页脚加载更多为不见
        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);

        // 添加ListView页脚
        addFooterView(footerView);

        // 监听ListView的滚动
        setOnScrollListener(new MyOnScrollListener());


    }

    /**
     * 添加顶部轮播图
     *
     * @param topNewsView
     */
    public void addTopNewsView(View topNewsView) {
        if (topNewsView != null) {
            this.topNewsView = topNewsView;
            headerView.addView(topNewsView);
        }

    }


    public boolean isDisplayTopNews() {
        if(topNewsView != null){

            //1.得到ListView在屏幕上的坐标
            int[] location = new int[2];
            getLocationOnScreen(location);
            int listViewOnScreenY = location[1];

            //2.得到顶部轮播图在屏幕上的坐标
            headerView.getLocationInWindow(location);
            int headerViewOnScreenY = location[1];

            LogUtil.e("listViewOnScreenY = " + listViewOnScreenY + "   headerViewOnScreenY = " + headerViewOnScreenY + "   listViewOnScreenY <= headerViewOnScreenY = " + ( listViewOnScreenY <= headerViewOnScreenY));
            return listViewOnScreenY <= headerViewOnScreenY;
        }



        return true;
    }

    class MyOnScrollListener implements OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {


            //当静止或者惯性滚动的时候
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE || scrollState == OnScrollListener.SCROLL_STATE_FLING) {
                LogUtil.e("SCROLL_STATE_IDLE");
                //并且是最后一条可见
                if (getLastVisiblePosition() == getCount() - 1) {
                    LogUtil.e(" getCount()-1");

                    //1.显示加载更多布局
                    footerView.setPadding(8, 8, 8, 8);
                    //2.状态改变
                    isLoadMore = true;
                    //3.回调接口
                    if (myOnRefreshListener != null) {
                        myOnRefreshListener.onLoadMore();
                    }
                }
            }



 /*           if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                    && view.getLastVisiblePosition() == view
                    .getPositionForView(footerView) ){
                //1.显示加载更多布局
                footerView.setPadding(8, 8, 8, 8);
                // 2. 状态改变
                isLoadMore = true;
                // 3. 回调接口
                if (myOnRefreshListener != null) {
                    myOnRefreshListener.onLoadMore();
                }
            }*/
        /*    //     当静止 或者 惯性滚动 的时候
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE || scrollState == OnScrollListener.SCROLL_STATE_FLING) {
                // 并且是最后一条可见
                if (getLastVisiblePosition() >= getCount() - 1) {

                    //1.显示加载更多布局
                    footerView.setPadding(8, 8, 8, 8);
                    // 2. 状态改变
                    isLoadMore = true;
                    // 3. 回调接口
                    if (myOnRefreshListener != null) {
                        myOnRefreshListener.onLoadMore();
                    }
                }
            }*/

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }


    private void initAnimation() {

        upAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);

        downAnimation = new RotateAnimation(-180, -360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);

    }

    private void initHeaderView(Context context) {

        headerView = (LinearLayout) View.inflate(context, R.layout.refresh_header, null);
        ll_pull_down_refresh = (LinearLayout) headerView.findViewById(R.id.ll_pull_down_refresh);
        iv_arrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
        pb_status = (ProgressBar) headerView.findViewById(R.id.pb_status);
        tv_status = (TextView) headerView.findViewById(R.id.tv_status);
        tv_time = (TextView) headerView.findViewById(R.id.tv_time);

        // 测量
        ll_pull_down_refresh.measure(0, 0);
        pullDownRefreshHeight = ll_pull_down_refresh.getMeasuredHeight();

        //默认隐藏下拉刷新控件
        // View.setPadding(0,-控件高，0,0);//完全隐藏
        //View.setPadding(0, 0，0,0);//完全显示
        ll_pull_down_refresh.setPadding(0, -pullDownRefreshHeight, 0, 0);

        this.addHeaderView(headerView);

    }

    private float startY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                // 判断没有初始化 （避免distanceY结果为负数）
                if (startY == -1) {
                    startY = ev.getY();
                }

                // 判断顶部轮播图是否完全显示，只有完全显示才会有下拉刷新
                boolean isDisplayTopNews =  isDisplayTopNews();
                if(!isDisplayTopNews){
                    // 加载更多
                     break;
                }


                // 如果是正在刷新，就不让再重复刷新
                if (currentStatus == REFRESHING) {
                    break;
                }

                //来到新的坐标
                float endY = ev.getY();
                float distanceY = endY - startY;

                if (distanceY > 0) {
                    // 下拉
                    int paddingTop = (int) (-pullDownRefreshHeight + distanceY);
                    ll_pull_down_refresh.setPadding(0, paddingTop, 0, 0);

                    if (paddingTop < 0 && currentStatus != PULL_DOWN_REFRESH) {
                        currentStatus = PULL_DOWN_REFRESH;
                        // 更新View状态
                        refreshViewState();

                    } else if (paddingTop > 0 && currentStatus != RELEASE_REFRESH) {
                        currentStatus = RELEASE_REFRESH;
                        // 更新View状态
                        refreshViewState();
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                // 复位
                startY = -1;

                if (currentStatus == PULL_DOWN_REFRESH) {
                    ll_pull_down_refresh.setPadding(0, -pullDownRefreshHeight, 0, 0);

                } else if (currentStatus == RELEASE_REFRESH) {
                    //设置状态为正在刷新
                    currentStatus = REFRESHING;
                    //更新状态
                    refreshViewState();
                    // 下拉控件为可见状态
                    ll_pull_down_refresh.setPadding(0, 0, 0, 0);

                    // 设置回调接口
                    myOnRefreshListener.onPullDownRefresh();

                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void refreshViewState() {
        refresHandler.sendEmptyMessage(currentStatus);
       /* switch (currentStatus) {
            case PULL_DOWN_REFRESH:

                tv_status.setText("下拉刷新"); //下拉刷新状态
                iv_arrow.setAnimation(downAnimation);

                break;
            case RELEASE_REFRESH:
                tv_status.setText("手松刷新");  // 手松刷新状态
                iv_arrow.setAnimation(upAnimation);
                break;
            case REFRESHING:
                tv_status.setText("正在刷新");  // 正在刷新状态
                iv_arrow.clearAnimation();
                iv_arrow.setVisibility(GONE);
                pb_status.setVisibility(VISIBLE);

                break;
        }*/
    }

    private Handler refresHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case PULL_DOWN_REFRESH:

                    tv_status.setText("下拉刷新"); //下拉刷新状态
                    //iv_arrow.setAnimation(downAnimation);
                    iv_arrow.startAnimation(downAnimation);

                    break;
                case RELEASE_REFRESH:

                    tv_status.setText("手松刷新");  // 手松刷新状态
                    //iv_arrow.setAnimation(upAnimation);
                    iv_arrow.startAnimation(upAnimation);

                    break;
                case REFRESHING:

                    tv_status.setText("正在刷新");  // 正在刷新状态
                    iv_arrow.clearAnimation();
                    iv_arrow.setVisibility(GONE);
                    pb_status.setVisibility(VISIBLE);

                    break;
                case REFRESH_FINISH:
                    tv_status.setText("下拉刷新");
                    currentStatus = PULL_DOWN_REFRESH;
                    iv_arrow.clearAnimation();
                    pb_status.setVisibility(GONE);
                    iv_arrow.setVisibility(VISIBLE);

                    //  隐藏下拉刷新控件-重写显示数据，更新时间
                    ll_pull_down_refresh.setPadding(0, -pullDownRefreshHeight, 0, 0);
                    // 获取刷新结果
                    Bundle bundle = msg.getData();
                    boolean result = bundle.getBoolean("result");
                    if (result) {
                        //设置最新更新时间
                        tv_time.setText("上次更新时间：" + getSystemTime());
                    }
                    break;


            }
        }
    };

    public void onRefreshFinish(Boolean result) {

        if (isLoadMore) {
            // 加载更多
            isLoadMore = false;
            // 隐藏加载更多界面
            footerView.setPadding(0, -footerViewHeight, 0, 0);
        } else {
            // 更新ui
            Message msg = new Message();
            msg.what = REFRESH_FINISH;
            Bundle bundle = new Bundle();
            bundle.putBoolean("result", result);
            msg.setData(bundle);
            refresHandler.sendMessage(msg);
        }

    }

    /**
     * 监听控件刷新
     */
    public interface OnRefreshListener {
        /**
         * 当下拉刷新的时候回调这个方法
         */
        public void onPullDownRefresh();

        /**
         * 加载更多
         */
        void onLoadMore();
    }

    /**
     * 设置回调接口
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.myOnRefreshListener = listener;
    }


    /**
     * 得到当前Android系统的时间
     */
    private String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
