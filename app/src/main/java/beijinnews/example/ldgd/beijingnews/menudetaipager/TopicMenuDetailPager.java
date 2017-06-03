package beijinnews.example.ldgd.beijingnews.menudetaipager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;

/**
 * Created by ldgd on 2017/6/3.
 * 专题详情页面
 */

public class TopicMenuDetailPager extends MenuDetaiBasePager {
    private TextView textView;

    public TopicMenuDetailPager(Context context) {
        super(context);

    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("专题详情页面");

    }
}