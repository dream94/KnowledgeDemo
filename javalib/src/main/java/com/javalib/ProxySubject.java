package com.javalib;

/**
 * Created by cc on 2019/3/15.
 */

public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void doSth() {
        System.out.println("before doSth");
        if (subject != null) {
            subject.doSth();
        }
    }
}
