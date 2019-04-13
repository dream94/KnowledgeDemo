package com.javalib;

import com.javalib.utils.ProxyUtils;

import java.lang.reflect.Proxy;

/**
 * Created by cc on 2019/3/17.
 */

public class Main {
    public static void main(String[] args) {
//        testStaticProxy();
//        testDynamicProxy();

        String a = "aaa";
        change(a);
        System.out.println(a);
    }

    private static void change(String s) {
        s = s.replaceAll("a", "b");
        System.out.println("change:" + s);
    }

    private static void testStaticProxy() {
        ASubject aSubject = new ASubject();
        ProxySubject proxySubject = new ProxySubject(aSubject);
        proxySubject.doSth();
    }

    private static void testDynamicProxy() {
        ASubject aSubject = new ASubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new ProxyHandler(aSubject));
        proxySubject.doSth();

        ProxyUtils.generateClassFile(ASubject.class, proxySubject.getClass().getSimpleName());
    }
}
