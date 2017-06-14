package beijinnews.example.ldgd.beijingnews.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ldgd on 2017/6/14.
 */

public class HorizontalScrollViewPager extends ViewPager {


    public HorizontalScrollViewPager(Context context) {
        this(context,null);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
         getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
