package com.isaac.basic.example1;

public class MessageBeanEnImpl implements MessageBean {
    @Override
    public void tellMe() {
        System.out.println("Hello, Spring!");
    }
}
