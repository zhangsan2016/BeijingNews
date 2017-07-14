package beijinnews.example.ldgd.beijingnews.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ldgd on 2017/7/13.
 */

 public class NetCacheUtils {
    private Handler handler;
    /**
     * 请求图片成功
     */
    public static final int SUCESS = 1;
    /**
     * 请求图片失败
     */
    public static final int FAIL = 2;

    /**
     * 固定大小的线程池
     */
    private ExecutorService service;
    /**
     * 本地缓存工具类
     */
    private LocalCacheUtils localCacheUtils;
    /**
     * 内存缓存工具类
     */
    private MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils(Handler handler, LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.handler = handler;
        service = Executors.newFixedThreadPool(10);
        this.localCacheUtils = localCacheUtils;
        this.memoryCacheUtils = memoryCacheUtils;

    }

    public void getBitmapFomNet(String imageUrl, int position) {

        service.execute(new MyRunnable(imageUrl, position));


    }

    private class MyRunnable implements Runnable {

        private final int position;
        private final String imageUrl;

        public MyRunnable(String imageUrl, int position) {
            this.imageUrl = imageUrl;
            this.position = position;
        }

        @Override
        public void run() {
            //子线程
            //请求网络图片
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(4000);
                connection.setReadTimeout(4000);
                connection.connect();  // 可写可不写

                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    //显示到控件上,发消息吧Bitmap发出去和position
                    Message msg =  Message.obtain();
                    msg.what = SUCESS;
                    msg.arg1 = position;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);

                    //  在内存中缓存一份
                    memoryCacheUtils.putBitmap(imageUrl,bitmap);

                    //  在本地中缓存一份
                    localCacheUtils.putBitmap(imageUrl,bitmap);

                }


            }catch (IOException e) {
                e.printStackTrace();

                Message msg =  Message.obtain();
                msg.what = FAIL;
                msg.arg1 = position;
                handler.sendMessage(msg);

            }


        }
    }
}
