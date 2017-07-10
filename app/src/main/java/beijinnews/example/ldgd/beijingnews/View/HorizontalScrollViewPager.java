package beijinnews.example.ldgd.beijingnews.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ldgd on 2017/6/14.
 */

public class HorizontalScrollViewPager extends ViewPager {

    /**
     * 起始坐标
     */
    private float startX;
    private float startY;

    public HorizontalScrollViewPager(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //  请求父层视图不拦截，当前控件的事件
                getParent().requestDisallowInterceptTouchEvent(true); //都把事件传给当前控件（HorizontalScrollViewPager）
                // 记录起始位置
                startX = ev.getX();
                startY = ev.getY();


               // LogUtil.e("startX = " + startX + "   startY = " + startY);
                break;
            case MotionEvent.ACTION_MOVE:
                // 来到新坐标
                float endX = ev.getX();
                float endY = ev.getY();

             //   LogUtil.e("endX = " + endX + "   endY = " + endY);


                //  计算偏移量
                float distanceX = endX - startX;
                float distanceY = endY - startY;

         //       LogUtil.e("distanceX = " + distanceX + "   distanceY = " + distanceY);

                // 判断是否横向滑动（使用绝对值判断，因为从右到左横向滑行X坐标为负数）
                if (Math.abs(distanceX) > Math.abs(distanceY)) {        // 横向滑动

                    // x偏移量大于0表示左到右滑动
                    if (distanceX > 0 && getCurrentItem() == 0) {
                        // ViewPager第0个位置
                        getParent().requestDisallowInterceptTouchEvent(false);

                    }
                    // 当滑动到ViewPager的最后一个页面，并且是从右到左滑动
                    else if (distanceX < 0 && getCurrentItem() == getAdapter().getCount() - 1) {
                        // ViewPager最后一个位置
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {  // 其它，中间部分
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

                } else if (Math.abs(distanceX) == Math.abs(distanceY)) { // xy没有改变
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {    // 纵向滑动

                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                // getParent().requestDisallowInterceptTouchEvent(true);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }


        return super.dispatchTouchEvent(ev);
    }


}
