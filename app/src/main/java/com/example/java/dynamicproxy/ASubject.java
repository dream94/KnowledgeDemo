package com.example.java.dynamicproxy;

/**
 * Created by cc on 2019/1/27.
 */

public class ASubject implements ISubject {
    @Override
    public void doSth() {
        System.out.println("ASubject doSth");
    }
}
