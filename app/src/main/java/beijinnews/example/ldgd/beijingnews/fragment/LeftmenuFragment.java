package beijinnews.example.ldgd.beijingnews.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.base.BaseFragment;
import beijinnews.example.ldgd.beijingnews.base.BasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("Listview Item 点击" + position);
                prePosition = position;
                adapter.notifyDataSetChanged();

            }
        });

        return listView;
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setData(List<NewCenterPagerBase.DataBean> data) {
        this.data = data;
        adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);

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
