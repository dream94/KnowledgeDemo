package com.example.java.dynamicproxy;

/**
 * Created by cc on 2019/3/17.
 */

public class Main {
    public static void main(String[] args) {
//        ASubject aSubject = new ASubject();
//        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
//                new Class[]{Subject.class},
//                new ProxyHandler(aSubject));
//        subject.doSth();
//        System.out.println(subject.getClass().getName());
        test("aaa", 12);
    }

    private static void test(String message, long rowId) {

    }
}
