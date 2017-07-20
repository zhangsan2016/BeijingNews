package beijinnews.example.ldgd.beijingnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import beijinnews.example.ldgd.beijingnews.R;


public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showimage);

       String url = getIntent().getStringExtra("url");

        final PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);

        Picasso.with(this)
              //  .load("http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg")
                .load(url)
                .into(photoView);
    }
}
