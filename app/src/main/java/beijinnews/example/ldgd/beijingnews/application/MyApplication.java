package beijinnews.example.ldgd.beijingnews.application;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

import beijinnews.example.ldgd.beijingnews.volley.VolleyManager;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by ldgd on 2017/5/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        // 初始化Volley
        VolleyManager.init(this);

        // 极光推送
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush


    }
}
