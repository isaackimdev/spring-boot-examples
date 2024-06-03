package com.isaac.basic.example1;

public class MessageBeanKrImpl implements MessageBean {
    @Override
    public void tellMe() {
        System.out.println("안녕, 스프링!");
    }
}
