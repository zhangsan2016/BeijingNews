package beijinnews.example.ldgd.beijingnews.fragment;


import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.activity.MainActivity;
import beijinnews.example.ldgd.beijingnews.adapter.ContentFragmentAdapter;
import beijinnews.example.ldgd.beijingnews.base.BaseFragment;
import beijinnews.example.ldgd.beijingnews.base.BasePager;
import beijinnews.example.ldgd.beijingnews.pager.GovaffairPager;
import beijinnews.example.ldgd.beijingnews.pager.HomePager;
import beijinnews.example.ldgd.beijingnews.pager.NewsCenterPager;
import beijinnews.example.ldgd.beijingnews.pager.SettingPager;
import beijinnews.example.ldgd.beijingnews.pager.SmartServicePager;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/5/16.
 */

public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    /**
     * 装五个页面的集合
     */
    private ArrayList<BasePager> basePagers;


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);

        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this, view);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        LogUtil.e("正文Fragment数据被初始化了");


        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));  //主页面
        basePagers.add(new NewsCenterPager(context));  //新闻中心页面
        basePagers.add(new SmartServicePager(context));  //智慧服务页面
        basePagers.add(new GovaffairPager(context));   //政要指南页面
        basePagers.add(new SettingPager(context));   //设置中心面


        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中，初始对应的页面的数据
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置默认选中首页
        rg_main.check(R.id.rb_home);

        basePagers.get(0).initData();
    }


    /**
     * 得到新闻中心
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {
        NewsCenterPager newsCenterPager = (NewsCenterPager) basePagers.get(1);
        return newsCenterPager;
    }


    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            switch (checkedId) {
                case R.id.rb_home:
                    viewpager.setCurrentItem(0, false);  //主页radioButton的id
                    isEnableSlidingMenn(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter:
                    viewpager.setCurrentItem(1, false);   //新闻中心radioButton的id
                    isEnableSlidingMenn(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice:
                    viewpager.setCurrentItem(2, false);   //智慧服务radioButton的id
                    isEnableSlidingMenn(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair:
                    viewpager.setCurrentItem(3, false);  //政要指南的RadioButton的id
                    isEnableSlidingMenn(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:
                    viewpager.setCurrentItem(4, false);  //设置中心RadioButton的id
                    isEnableSlidingMenn(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }

        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //调用被选中的页面的initData方法
            basePagers.get(position).initData();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 根据传人的参数设置是否让SlidingMenu可以滑动
     */
    private void isEnableSlidingMenn(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);

    }
}
