package com.fstech.yzedutc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.DiscoverAdapter;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.view.JazzyViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-4-10.
 * 发现课的主界面
 */

public class DiscoverActivity extends AppCompatActivity {
    private JazzyViewPager vp;
    private DiscoverAdapter adapter;
    private String userid;
    private List<Map<String, Object>> listItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        initView();
        initData();
        setListContent();

    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        vp = (JazzyViewPager) findViewById(R.id.vp);
        setupJazziness(JazzyViewPager.TransitionEffect.RotateDown);
        listItems = new ArrayList<Map<String, Object>>();
    }

    private void initData() {
        userid = "120110040225";
    }

    private void setListContent() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        String url = Constant.TEMP_BASE_URL + "course/like";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(DiscoverActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONArray ja = jobj.getJSONArray("clist");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject course = ja.getJSONObject(i);
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("course_img", course.get("course_img"));
                        listItem.put("course_name", course.get("course_name"));
                        listItem.put("course_pnum", "已有" + course.get("learn_pnum") + "人学习");
                        listItem.put("course_id", course.get("course_id"));
                        listItems.add(listItem);
                    }
                    adapter = new DiscoverAdapter(DiscoverActivity.this, listItems, vp);
                    vp.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        vp.setTransitionEffect(effect);
        vp.setPageMargin(30);
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
