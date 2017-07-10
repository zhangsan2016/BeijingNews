package beijinnews.example.ldgd.beijingnews.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import beijinnews.example.ldgd.beijingnews.R;

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private ImageButton ibMenu;
    private ImageButton ibTextsize;
    private ImageButton ibShare;
    private ImageButton ibBack;
    private ImageButton ibSwichListGrid;
    private Button btnCart;
    private ProgressBar progerssBar;
    private WebView webview;
    private WebSettings webSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_news_detail);

        findViews();
        getData();
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-07-07 11:48:39 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ibMenu = (ImageButton) findViewById(R.id.ib_menu);
        ibTextsize = (ImageButton) findViewById(R.id.ib_textsize);
        ibShare = (ImageButton) findViewById(R.id.ib_share);
        ibSwichListGrid = (ImageButton) findViewById(R.id.ib_swich_list_grid);
        btnCart = (Button) findViewById(R.id.btn_cart);
        progerssBar = (ProgressBar) findViewById(R.id.progerssBar);
        webview = (WebView) findViewById(R.id.webview);
        ibBack = (ImageButton) findViewById(R.id.ib_back);

        tvTitle.setVisibility(View.GONE);
        ibMenu.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        ibTextsize.setVisibility(View.VISIBLE);
        ibShare.setVisibility(View.VISIBLE);

        ibMenu.setOnClickListener(this);
        ibTextsize.setOnClickListener(this);
        ibShare.setOnClickListener(this);
        ibSwichListGrid.setOnClickListener(this);
        btnCart.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-07-07 11:48:39 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibMenu) {
            // Handle ccks for ibMenu
        } else if (v == ibTextsize) {

            Toast.makeText(this,"showChangeTextSizeDialog",Toast.LENGTH_SHORT).show();
            showChangeTextSizeDialog();


        } else if (v == ibShare) {
            // Handle clicks for ibShare
        } else if (v == ibSwichListGrid) {
            // Handle clicks for ibSwichListGrid
        } else if (v == btnCart) {
            // Handle clicks for btnCart
        }
    }

    private int tempSize = 2;
    private int realSize = tempSize;

    private void showChangeTextSizeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置文字大小");
        String[] items = {"超大字体", "大字体", "正常字体", "小字体", "超小字体"};
        builder.setSingleChoiceItems(items, tempSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempSize = which;
            }
        });
        builder.setNegativeButton("取消", null);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realSize = tempSize;
                changeTextSize(realSize);
            }
        });
        builder.show();

    }

    private void changeTextSize(int realSize) {
        switch (realSize) {
            case 0:   // 超大字体
                webSettings.setTextZoom(200);
                break;
            case 1:   // 大字体
                webSettings.setTextZoom(150);
                break;
            case 2:   //  正常字体
                webSettings.setTextZoom(100);
                break;
            case 3:  // 小字体
                webSettings.setTextZoom(75);
                break;
            case 4:  // 超小字体
                webSettings.setTextZoom(50);
                break;
        }
    }


    private void getData() {
        String url = getIntent().getStringExtra("url");
        webSettings = webview.getSettings();
        // 设置webView 支持javaScript
        webSettings.setJavaScriptEnabled(true);
        //设置支持javaScript
        webSettings = webview.getSettings();
        //设置支持javaScript
        webSettings.setJavaScriptEnabled(true);
        //设置双击变大变小
        webSettings.setUseWideViewPort(true);
        //增加缩放按钮
        webSettings.setBuiltInZoomControls(true);

        //设置文字大小
//        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setTextZoom(100);
        //不让从当前网页跳转到系统的浏览器中
        webview.setWebViewClient(new WebViewClient() {
            //当加载页面完成的时候回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progerssBar.setVisibility(View.GONE);
            }
        });


        webview.loadUrl(url);

    }
}
