package beijinnews.example.ldgd.beijingnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class ShowImgesActivity extends AppCompatActivity {
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


    }
}
