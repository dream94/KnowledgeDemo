package com.example.cc.knowldegedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.java.dynamicproxy.ASubject;
import com.example.java.dynamicproxy.ProxyHandler;
import com.example.java.dynamicproxy.Subject;

import java.lang.reflect.Proxy;

/**
 * Created by cc on 2019/3/4.
 */

public class DynamicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ASubject aSubject = new ASubject();
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(aSubject));
        System.out.println(subject.getClass().getName());
    }
}
