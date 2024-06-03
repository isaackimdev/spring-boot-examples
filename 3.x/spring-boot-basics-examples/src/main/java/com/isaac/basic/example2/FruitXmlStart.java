package com.isaac.basic.example2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class FruitXmlStart {
    public FruitXmlStart() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("./bean1.xml");
        FruitVo fruitVo = (FruitVo) context.getBean("fruitBean");
        System.out.println(fruitVo.getName());
        System.out.println(fruitVo.getCost());
        context.close();
    }

}
