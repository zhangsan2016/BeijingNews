package beijinnews.example.ldgd.beijingnews.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.activity.MainActivity;

/**
 * Created by ldgd on 2017/5/20.
 */

public class BasePager {

    /**
     * 上下文
     */
    public final Context context;  //MainActivity
    /**
     * 视图，代表各个不同的页面
     */
    public final View rootView;

    /**
     * 加载各个子页面
     */
    public TextView tv_title;

    /**
     * 点击侧滑的
     */
    public ImageButton ib_menu;

    /**
     * 加载各个子页面
     */
    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;
        rootView = intView();
    }

    private View intView() {
        View view = View.inflate(context, R.layout.base_pager, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);

        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
            }
        });

        return view;
    }

    /**
       初始化数据，;当孩子需要初始化数据;或者绑定数据;联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){

    }
}
