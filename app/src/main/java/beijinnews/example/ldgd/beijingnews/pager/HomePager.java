package beijinnews.example.ldgd.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import beijinnews.example.ldgd.beijingnews.base.BasePager;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/5/23.
 */

public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("主页面数据被初始化了..");
        //1.设置标题
        tv_title.setText("主页面");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("主页面内容");
    }
}
