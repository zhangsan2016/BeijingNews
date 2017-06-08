package beijinnews.example.ldgd.beijingnews.menudetaipager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.menudetaipager.tabdetailpager.TabDetailPager;

/**
 * Created by ldgd on 2017/6/1.
 * 新闻详情页面
 */

public class NewsMenuDetailPager extends MenuDetaiBasePager {

    /**
     * 页签页面的数据的集合-数据
     */
    private List<NewCenterPagerBase.DataBean.ChildrenBean> children;
    /**
     * 页签页面的集合-页面
     */
    private ArrayList<TabDetailPager> tabDetailPagers;

    @ViewInject(R.id.indicator)
    private TabPageIndicator indicator;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.ib_tab_next)
    private ImageButton ib_tab_next;

    public NewsMenuDetailPager(Context context, NewCenterPagerBase.DataBean detailPagerData) {
        super(context);

        this.children = detailPagerData.getChildren();

    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.newsmenu_detail_pager, null);
        // xutils框架绑定数据
        x.view().inject(NewsMenuDetailPager.this, view);

        // 设置点击事件
        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换到下一个页签
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        // 初始化数据
        tabDetailPagers = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            tabDetailPagers.add(new TabDetailPager(context, children.get(i)));
        }

        // 设置适配器
        viewPager.setAdapter(new MyNewsMenuDetailPagerAdapter());
       //  ViewPager和 TabPageIndicator关联
        indicator.setViewPager(viewPager);


    }

        private class MyNewsMenuDetailPagerAdapter extends PagerAdapter {

            @Override
            public CharSequence getPageTitle(int position) {
                return children.get(position).getTitle();
            }

            @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            tabDetailPager.initData();  // 初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
