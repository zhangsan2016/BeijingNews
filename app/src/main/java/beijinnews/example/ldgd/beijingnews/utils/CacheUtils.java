package beijinnews.example.ldgd.beijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ldgd on 2017/5/6.
 */

public class CacheUtils {


    /**
     * 得到缓存值
     *
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }



}
