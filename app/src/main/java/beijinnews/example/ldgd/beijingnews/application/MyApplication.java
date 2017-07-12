package beijinnews.example.ldgd.beijingnews.application;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

import beijinnews.example.ldgd.beijingnews.volley.VolleyManager;

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



    }
}
