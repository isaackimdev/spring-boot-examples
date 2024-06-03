package com.isaac.basic.example1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Coupling {
    public Coupling() {
        // 의존성이 높아, 수정할 때 여러 코드를 수정해야 함.
        MessageBeanKr messageBeanKr = new MessageBeanKr();
        messageBeanKr.tellMe();
        MessageBeanEn messageBeanEn = new MessageBeanEn();
        messageBeanEn.tellMe();

        // 다형성을 이용하여 결합도를 낮춤
        MessageBean bean2 = new MessageBeanKrImpl();
        bean2.tellMe();

        // Factory Pattern
        MessageBeanFactory factory = new MessageBeanFactory();
        MessageBean bean3 = factory.getBean("en");
        bean3.tellMe();

        // xml object
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("./bean.xml");
        MessageBean bean4 = (MessageBean) context.getBean("messageBean");
        bean4.tellMe();
        context.close();
    }
}
