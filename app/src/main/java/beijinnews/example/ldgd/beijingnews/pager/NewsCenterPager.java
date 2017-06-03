package beijinnews.example.ldgd.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.activity.MainActivity;
import beijinnews.example.ldgd.beijingnews.base.BasePager;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.fragment.LeftmenuFragment;
import beijinnews.example.ldgd.beijingnews.utils.Constants;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/5/23.
 */

public class NewsCenterPager extends BasePager {

     private List<NewCenterPagerBase.DataBean> data;
    private ArrayList<MenuDetaiBasePager> detaiBasePagers;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("新闻中心内容数据被初始化了..");
        // 设置菜单图标为可见状态
        ib_menu.setVisibility(View.VISIBLE);
        //1.设置标题
        tv_title.setText("新闻中心内容");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("新闻中心内容");

        // 联网请求
        getDataFromNet();


    }

    private void getDataFromNet() {
        RequestParams requestParams = new RequestParams(Constants.NEWCENTER_PAGER_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("使用XUtil3 联网请求成功==" + result);
                // 解析Json
                processData(result);

            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("使用XUtil3 联网请求失败 ==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("使用XUtil3 联网请求 Cancelledon ==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("使用XUtil3 联网请求 onFinished");
            }
        });
    }

    /**
     * 解析Json数据
     */
    private NewCenterPagerBase parsedJson(String json) {

        return new Gson().fromJson(json, NewCenterPagerBase.class);
    }

    private void processData(String json) {

        // 解析json
        NewCenterPagerBase bean = parsedJson(json);

        //给左侧菜单传递数据
        List<NewCenterPagerBase.DataBean> data = bean.getData();

        //得到左侧菜单
        MainActivity mainActivity = (MainActivity) context;
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        // 添加详情页面
        detaiBasePagers = new ArrayList<>();
        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);

    }



}
