package beijinnews.example.ldgd.beijingnews.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.SplashActivity;
import beijinnews.example.ldgd.beijingnews.utils.CacheUtils;
import beijinnews.example.ldgd.beijingnews.utils.DensityUtil;

public class GuideActivity extends AppCompatActivity {

    private static final String TAG = GuideActivity.class.getName();
    private ViewPager viewpager;
    private List imageViews;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private Button bt_start_main;
    private int leftMax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) this.findViewById(R.id.iv_red_point);
        bt_start_main = (Button) this.findViewById(R.id.bt_start_main);


        int ids[] = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView img = new ImageView(this);
            // 设置背景
            img.setBackgroundResource(ids[i]);
            // 添加到集合
            imageViews.add(img);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(this,10), DensityUtil.dip2px(this,10));
            if (i != 0) {
                //不包括第0个，所有的点距离左边有10个像数
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);


        }
        //设置ViewPager的适配器
        viewpager.setAdapter(new MyPagerAdapter());

        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //得到屏幕滑动的百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        bt_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存曾经进入过主页面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);

                // 跳转到主页面
                Intent intetnt = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intetnt);

                // 关闭引导页面
                finish();
            }
        });


    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面回调了会回调这个方法
         *
         * @param position             当前滑动页面的位置
         * @param positionOffset       页面滑动的百分比
         * @param positionOffsetPixels 滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间移动的距离 = 屏幕滑动百分比 * 间距
//            int leftmargin = (int) (positionOffset * leftmax);
//            Log.e(TAG,"position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);

            //两点间滑动距离对应的坐标 = 原来的起始位置 +  两点间移动的距离
            int leftmargin = (int) (position * leftMax + (positionOffset * leftMax));

            //params.leftMargin = 两点间滑动距离对应的坐标
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候，回调这个方法
         *
         * @param position 被选中页面的对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                // 最后个界面
                bt_start_main.setVisibility(View.VISIBLE);
            } else {
                // 其他界面
                bt_start_main.setVisibility(View.GONE);
            }

        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private class MyPagerAdapter extends PagerAdapter {

        /**
         * 作用，getView
         *
         * @param container ViewPager
         * @param position  要创业页面的位置
         * @return 返回和创建当前页面右关系的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView point = (ImageView) imageViews.get(position);
            container.addView(point);
            return point;
        }

        /**
         * 销毁页面
         *
         * @param container ViewPager
         * @param position  要销毁页面的位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 返回数据的总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断
         *
         * @param view   当前创建的视图
         * @param object 上面instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    private class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            //只执行一次
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(MyOnGlobalLayoutListener.this);
            // 间距  = 第1个点距离左边的距离 - 第0个点距离左边的距离
            leftMax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
            Log.e(TAG, "leftmax==" + leftMax);
        }
    }
}
