package beijinnews.example.ldgd.beijingnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import beijinnews.example.ldgd.beijingnews.R;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        super.onCreate(savedInstanceState);

        initSlidingMenu();


    }

    private void initSlidingMenu() {
        setContentView(R.layout.activity_main);

    }
}
