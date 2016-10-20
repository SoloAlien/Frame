package com.example.alienapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.appframe.common.utils.base.ActivityUtils;
import com.example.appframe.common.utils.base.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils utils=new ActivityUtils(this);
        int hei=utils.getStatusBarHeight();

        LogUtils.d("\t"+"hei:"+hei);
        LogUtils.e("\t"+"hei:"+hei);
        Log.e("TAG","\t"+"hei:"+hei);
    }
}
