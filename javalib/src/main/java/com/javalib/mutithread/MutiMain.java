package com.javalib.mutithread;

/**
 * 线程交替打印奇数偶数(1-20)
 */
public class MutiMain {
    private static int i = 0;

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println("\"\\u5168\\u4e16\\u754c\\u6700\\u53ef\\u7231\\u7684\\u7a0b\n" +
                "\\u5e8f\\u540c\\u5b66\\u8282\\u65e5\\u5feb\\u4e50\\uff01\"");
        new Thread(new EvenRunnable(object)).start();
        new Thread(new OddRunnable(object)).start();
    }

    //偶数
    public static class EvenRunnable implements Runnable {
        private Object obj;

        public EvenRunnable(Object obj) {
            this.obj = obj;
        }

        @Override

        public void run() {
            synchronized (obj) {
                while (i < 40) {
                    if (i % 2 == 0) {
                        System.out.println("偶数:" + i++);
                        obj.notify();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //奇数
    public static class OddRunnable implements Runnable {
        private Object obj;

        public OddRunnable(Object obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            synchronized (obj) {
                while (i < 40) {
                    if (i % 2 == 1) {
                        System.out.println("奇数:" + i++);
                        obj.notify();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
