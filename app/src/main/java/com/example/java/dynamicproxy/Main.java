package com.example.java.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by cc on 2019/1/27.
 */

public class Main {
    public static void main(String[] args) {
        ASubject aSubject = new ASubject();
        ISubject subject = (ISubject) Proxy.newProxyInstance(ISubject.class.getClassLoader(),
                new Class[]{ISubject.class},
                new ProxyHandler(aSubject));
        subject.doSth();
    }
}
