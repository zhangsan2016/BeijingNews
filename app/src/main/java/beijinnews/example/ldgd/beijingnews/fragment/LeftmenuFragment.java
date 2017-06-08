package beijinnews.example.ldgd.beijingnews.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.activity.MainActivity;
import beijinnews.example.ldgd.beijingnews.base.BaseFragment;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.pager.NewsCenterPager;
import beijinnews.example.ldgd.beijingnews.utils.DensityUtil;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/5/18.
 */

public class LeftmenuFragment extends BaseFragment {
    private List<NewCenterPagerBase.DataBean> data;
    private ListView listView;
    private int prePosition;
    private  LeftmenuFragmentAdapter adapter;

    @Override
    public View initView() {
        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context, 40), 0, 0);
      //  listView.setDividerHeight(0);//设置分割线高度为0
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setSelector(android.R.color.transparent);

        // listView Item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("Listview Item 点击" + position);

                //1.记录点击的位置，变成红色
                prePosition = position;
                adapter.notifyDataSetChanged();

                //2.把左侧菜单关闭
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();  // 开-关

                //3.切换到对应的详情页面：新闻详情页面，专题详情页面，图组详情页面，互动详情页面
                swichPager(prePosition);


            }


        });

        return listView;
    }

    /**
     * 根据位置切换不同详情页面
     * @param prePosition
     */
    private void swichPager(int prePosition) {
        // 获取内容页面
        MainActivity mainActivity = (MainActivity) context;
        ContentFragment contentFragment  = mainActivity.getContentFragment();
        // 从内容页面获取新闻中心页面
        NewsCenterPager NewsCenterPager =  contentFragment.getNewsCenterPager();
        NewsCenterPager.swichPager(prePosition);

    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setData(List<NewCenterPagerBase.DataBean> data) {
        this.data = data;
        adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);

        //设置默认页面
        swichPager(prePosition);


    }

    private class LeftmenuFragmentAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context,R.layout.item_leftmenu,null);
            NewCenterPagerBase.DataBean bean =  data.get(position);
            textView.setText(bean.getTitle());
            textView.setEnabled(position==prePosition);
            return textView;
        }
    }
}
