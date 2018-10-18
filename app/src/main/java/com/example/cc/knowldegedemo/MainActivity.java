package com.example.cc.knowldegedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cc.utils.AppUtils;
import com.example.cc.utils.TimeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private void test() {
        TimeUtils.testDemo();
        AppUtils.testDemo(this);
    }
}
