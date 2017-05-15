package beijinnews.example.ldgd.beijingnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import beijinnews.example.ldgd.beijingnews.activity.GuideActivity;
import beijinnews.example.ldgd.beijingnews.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {
    private RelativeLayout rl_splahs_root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimges);
        rl_splahs_root = (RelativeLayout) findViewById(R.id.rl_splahs_root);


        // 渐变、缩放、旋转动画
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setFillAfter(true);

        ScaleAnimation scale = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        scale.setFillAfter(true);

        RotateAnimation rotate = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alpha);
        animationSet.addAnimation(scale);
        animationSet.addAnimation(rotate);
        animationSet.setDuration(2000);

        rl_splahs_root.setAnimation(animationSet);

        animationSet.setAnimationListener(new MyAnimationListener());

    }

    private class MyAnimationListener implements Animation.AnimationListener {

        private static final String START_MAIN = "start_main";

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);

            Intent intent = null;
            if(isStartMain){
                //如果进入过主页面，直接进入主页面
                //2.跳转到主页面
           //     intent = new Intent(SplashActivity.this,MainActivity.class);

            }else{
                //如果没有进入过主页面，进入引导页面
                intent = new Intent(SplashActivity.this, GuideActivity.class);
            }

            startActivity(intent);

            //关闭Splash页面
            finish();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
