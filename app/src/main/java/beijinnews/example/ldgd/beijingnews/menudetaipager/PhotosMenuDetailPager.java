package beijinnews.example.ldgd.beijingnews.menudetaipager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;

import beijinnews.example.ldgd.beijingnews.R;
import beijinnews.example.ldgd.beijingnews.base.MenuDetaiBasePager;
import beijinnews.example.ldgd.beijingnews.domain.NewCenterPagerBase;
import beijinnews.example.ldgd.beijingnews.domain.PhotosMenuDetailPagerBean;
import beijinnews.example.ldgd.beijingnews.utils.CacheUtils;
import beijinnews.example.ldgd.beijingnews.utils.Constants;
import beijinnews.example.ldgd.beijingnews.utils.LogUtil;
import beijinnews.example.ldgd.beijingnews.volley.VolleyManager;

import static beijinnews.example.ldgd.beijingnews.R.id.listview;

/**
 * Created by ldgd on 2017/6/3.
 * 互动页面
 */

public class PhotosMenuDetailPager extends MenuDetaiBasePager {

    private NewCenterPagerBase.DataBean detailPagerData;

    @ViewInject(listview)
    private ListView listView;

    @ViewInject(R.id.gridview)
    private GridView gridView;

    private String url;

    List<PhotosMenuDetailPagerBean.DataBean.NewsBean> news;
    private PhotosMenuDetailPagerAdapter adapter;

    public PhotosMenuDetailPager(Context context) {
        super(context);
    }

    public PhotosMenuDetailPager(Context context, NewCenterPagerBase.DataBean detailPagerData) {
        super(context);

        this.detailPagerData = detailPagerData;
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.photos_menudetail_pager, null);
        x.view().inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        url = Constants.BASE_URL + detailPagerData.getUrl();
        String saveJson = CacheUtils.getString(context, url);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        getDataFromNet();

    }


    public void getDataFromNet() {
        //String请求
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                LogUtil.e("使用Volley联网请求成功==" + result);
                //缓存数据
                CacheUtils.putString(context, url, result);

                processData(result);
                //设置适配器
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.e("使用Volley联网请求失败==" + volleyError.getMessage());
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String parsed = new String(response.data, "UTF-8");
                    return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };

        //添加到队列
        VolleyManager.getRequestQueue().add(request);
    }


    /**
     * 解析和显示数据
     *
     * @param json
     */
    private void processData(String json) {
        PhotosMenuDetailPagerBean bean = parsedJson(json);
        LogUtil.e("图组解析成功==" + bean.getData().getNews().get(0).getTitle());

        isShowListView = true;
        //设置适配器
        news = bean.getData().getNews();
        adapter = new PhotosMenuDetailPagerAdapter();
        listView.setAdapter(adapter);

    }

    /**
     * true,显示ListView，隐藏GridView
     * false,显示GridView,隐藏ListView
     */
    private boolean isShowListView = true;
    /**
     * List和Grid切换
     * @param ib_swich_list_grid
     */
    public void swichListAndGrid(ImageButton ib_swich_list_grid) {
        if(isShowListView){
            isShowListView = false;
            adapter = new PhotosMenuDetailPagerAdapter();
            gridView.setVisibility(View.VISIBLE);
            gridView.setAdapter(adapter);
            listView.setVisibility(View.GONE);
            // 按钮显示--ListView
            ib_swich_list_grid.setBackgroundResource(R.drawable.icon_pic_list_type);

        }else{
            isShowListView = true;
            adapter = new PhotosMenuDetailPagerAdapter();
            gridView.setVisibility(View.GONE);
            gridView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            //按钮显示--ListView
            ib_swich_list_grid.setBackgroundResource(R.drawable.icon_pic_list_type);
        }



    }

    private PhotosMenuDetailPagerBean parsedJson(String json) {
        return new Gson().fromJson(json, PhotosMenuDetailPagerBean.class);
    }


    private class PhotosMenuDetailPagerAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

         /*   ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_photos_menudetail_pager, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            PhotosMenuDetailPagerBean.DataBean.NewsBean newsEntity = news.get(position);
            viewHolder.tv_title.setText(newsEntity.getTitle());
            String imageUrl = Constants.BASE_URL + newsEntity.getSmallimage();

            //使用Volley请求图片-设置图片了
            loaderImager(viewHolder, imageUrl );*/

            ViewHolder viewHolder;
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item_photos_menudetail_pager,null);
                viewHolder = new ViewHolder();
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到对应的数据
            PhotosMenuDetailPagerBean.DataBean.NewsBean newsEntity = news.get(position);
            viewHolder.tv_title.setText(newsEntity.getTitle());
            String imageUrl = Constants.BASE_URL+newsEntity.getSmallimage();
            //使用Volley请求图片-设置图片了
            loaderImager(viewHolder, imageUrl );

            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }


    private void loaderImager(final ViewHolder viewHolder, String imageurl) {

        //设置tag
      //  viewHolder.iv_icon.setTag(imageurl);
        //直接在这里请求会乱位置
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer != null) {

                    if (viewHolder.iv_icon != null) {
                        if (imageContainer.getBitmap() != null) {
                            //设置图片
                            viewHolder.iv_icon.setImageBitmap(imageContainer.getBitmap());
                        } else {
                            //设置默认图片
                            viewHolder.iv_icon.setImageResource(R.drawable.home_scroll_default);
                        }
                    }
                }
            }
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //如果出错，则说明都不显示（简单处理），最好准备一张出错图片
                viewHolder.iv_icon.setImageResource(R.drawable.home_scroll_default);
            }
        };
        VolleyManager.getImageLoader().get(imageurl, listener);
    }
}
