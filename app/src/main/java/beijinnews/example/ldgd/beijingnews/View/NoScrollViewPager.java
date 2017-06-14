package beijinnews.example.ldgd.beijingnews.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ldgd on 2017/5/24.
 * 自定义不可滑动的ViewPager
 */

public class NoScrollViewPager extends ViewPager {

    /**
     * 通常在代码中实例化的时候用该方法
     *
     * @param context
     */
    public NoScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 在布局文件中使用该类的时候，实例化该类用该构造方法，这个方法不能少，少的化会崩溃。
     *
     * @param context
     * @param attrs
     */
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写触摸事件，消费掉（使其不可滚动） 屏蔽了系统自带带特有的左右滑动事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


}
