package com.isaac.basic.example1;

public class MessageBeanFactory {
    public MessageBean getBean(String beanName) {
        if(beanName.equals("kr")) {
            return new MessageBeanKrImpl();
        } else if (beanName.equals("en")) {
            return new MessageBeanEnImpl();
        }
        return null;
    }
}
