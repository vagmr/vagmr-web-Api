package spt.vagmr.webdev;

import spt.vagmr.webdev.util.Md5Util;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/13-18:36
 * springBootProject
 * @Description
 */

public class CommonTest {
    public void ThreadLocalTest(){
        //创建
        ThreadLocal<String> tl = new ThreadLocal<>();
        new Thread(() -> {
            tl.set("笨蛋");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"笨蛋").start();
        new Thread(() -> {
            tl.set("陌猪");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"陌猪").start();
    }

    public void test01(){
        System.out.println(Md5Util.getMD5String("1234567v"));
    }
}

