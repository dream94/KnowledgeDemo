package com.javalib;

import com.javalib.utils.ProxyUtils;

import java.lang.reflect.Proxy;

/**
 * Created by cc on 2019/3/17.
 */

public class Main {
    public static void main(String[] args) {
        testStaticProxy();
        testDynamicProxy();
    }

    private static void testStaticProxy() {
        ASubject aSubject = new ASubject();
        ProxySubject proxySubject = new ProxySubject(aSubject);
        proxySubject.doSth();
    }

    private static void testDynamicProxy() {
        ASubject aSubject = new ASubject();
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(aSubject));
        subject.doSth();

        ProxyUtils.generateClassFile(ASubject.class, subject.getClass().getSimpleName());
    }
}
