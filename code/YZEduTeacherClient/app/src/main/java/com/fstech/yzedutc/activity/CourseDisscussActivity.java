package com.fstech.yzedutc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fstech.yzedutc.R;

/**
 * Created by shaoxin on 18-4-17.
 * 课程交流主页
 */

public class CourseDisscussActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_disscuss);
        initView();
        initData();
    }

    private void initView(){

    }

    private void initData(){

    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
