package beijinnews.example.ldgd.beijingnews.base;

import android.content.Context;
import android.view.View;

/**
 * Created by ldgd on 2017/6/1.
 * 左侧菜单详情界面基类
 */

public abstract class MenuDetaiBasePager {

    /**
     * 上下文
     */
    public Context context;

    /**
     * 各个详情页面视图
     */
    public View rootView;

    public MenuDetaiBasePager( Context context) {
        this.context = context;
        rootView = initView();

    }

    /**
     *  强制子类实现方法，初始化当前视图
     */
    public abstract View initView();


    /**
     * 子类需要绑定数据，联网请求的时候重写该方法
     */
    public void initData(){}

}
