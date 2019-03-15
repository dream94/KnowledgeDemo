package com.javalib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cc on 2019/3/17.
 */

public class ProxyHandler implements InvocationHandler {

    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before doSth");
        return method.invoke(proxied, args);
    }
}
