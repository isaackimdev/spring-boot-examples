package com.isaac.basic.example3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlConstructorStart {
    public XmlConstructorStart() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("bean2.xml");
        context.close();
    }
}
