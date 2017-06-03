package beijinnews.example.ldgd.beijingnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beijinnews.example.ldgd.beijingnews.base.BasePager;

/**
 * Created by ldgd on 2017/5/23.
 */

public class ContentFragmentAdapter extends PagerAdapter {

    private ArrayList<BasePager> basePagers;

    public ContentFragmentAdapter(ArrayList<BasePager> basePagers) {
        this.basePagers = basePagers;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager =  basePagers.get(position); //各个页面的实例
        View rootView = basePager.rootView;
        container.addView(rootView);
        return rootView;
    }

    @Override
    public int getCount() {
        return basePagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
