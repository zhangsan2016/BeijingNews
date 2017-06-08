package beijinnews.example.ldgd.beijingnews.menudetaipager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.URL;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.domain.TabDetailPagerBase;
import beijinnews.example.ldgd.beijingnews.menudetaipager.NewsMenuDetailPager;
import beijinnews.example.ldgd.beijingnews.utils.Constants;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;

/**
 * Created by ldgd on 2017/6/5.
 * 页签详情页面
 */

public class TabDetailPager extends MenuDetaiBasePager {

    private NewCenterPagerBase.DataBean.ChildrenBean childrenData;
    private String url;

    @ViewInject(R.id.listview)
    private ListView listview;

    public TabDetailPager(Context context, NewCenterPagerBase.DataBean.ChildrenBean childrenData) {
        super(context);
        this.childrenData = childrenData;
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tabdetail_pager, null);
        // xutils框架绑定数据
        x.view().inject(TabDetailPager.this, view);
        return view;
    }

    @Override
    public void initData() {
        url = Constants.BASE_URL + childrenData.getUrl();
        LogUtil.e("URL == " + url);
        getDataFromNet();
    }

    public void getDataFromNet() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("TabDetailPager 页面数据请求成功 == " + result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("TabDetailPager 页面数据请求失败 == " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("TabDetailPager 页面数据请求onCancelled == " + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("TabDetailPager 页面数据请求onFinished == " );
            }
        });
    }

    /**
     * 处理数据
     * @param json
     */
    private void processData(String json) {
       TabDetailPagerBase tabDetailPagerBase = parsedJson(json);
       LogUtil.e("TabDetailPager  解析json数据成功 === " + tabDetailPagerBase.getData().getNews().get(0).getTitle());


    }

    /**
     * 解析json数据
     * @param json
     */
    private TabDetailPagerBase parsedJson(String json) {
        return new Gson().fromJson(json,TabDetailPagerBase.class);
    }

}
