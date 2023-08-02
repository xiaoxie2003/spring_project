package com.yc.jdkproxy;

public class HelloImpl implements HelloI{
    @Override
    public void sayHello() {
        System.out.println("HelloImpl中的sayHello()");
    }

    @Override
    public void showBye() {
        System.out.println("HelloImpl中的showBye()");
    }
}
