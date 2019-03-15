package com.example.java.dynamicproxy;

/**
 * Created by cc on 2019/3/17.
 */

public class ASubject implements Subject {
    @Override
    public void doSth() {
        System.out.println("ASubject doSth");
    }
}
