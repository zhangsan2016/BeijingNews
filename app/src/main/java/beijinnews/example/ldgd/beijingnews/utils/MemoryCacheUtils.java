package beijinnews.example.ldgd.beijingnews.utils;

import android.graphics.Bitmap;

import org.xutils.cache.LruCache;

/**
 * Created by ldgd on 2017/7/13.
 * Java之软引用&弱引用&虚引用
 */

class MemoryCacheUtils {

    /**
     * 集合
     */
    private LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtils() {

        //使用了系统分配给应用程序的八分之一内存来作为缓存大小
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {

            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //  return super.sizeOf(key, value);

                //value.getRowBytes() * value.getHeight();
                //  return (value.getRowBytes() * value.getHeight()) * 1024;

                return (value.getRowBytes() * value.getHeight()) / 1024;
            }
        };

    }

    /**
     * 根据url保存图片到lruCache集合中
     *
     * @param imageUrl 图片路径
     * @param bitmap   图片
     */
    public void putBitmap(String imageUrl, Bitmap bitmap) {
        lruCache.put(imageUrl, bitmap);
    }

    /**
     * 根据url从内存中获取图片
     *
     * @param imageUrl
     * @return
     */
    public Bitmap getBitmapFromUrl(String imageUrl) {
        return lruCache.get(imageUrl);
    }
}
