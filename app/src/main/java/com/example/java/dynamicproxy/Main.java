package com.example.java.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by cc on 2019/1/27.
 */

public class Main {
    public static void main(String[] args) {
        ASubject aSubject = new ASubject();
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(aSubject));
        subject.doSth();
        System.out.println(subject.getClass().getName());

    }
}
