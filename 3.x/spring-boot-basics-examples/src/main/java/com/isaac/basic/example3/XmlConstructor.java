package com.isaac.basic.example3;

public class XmlConstructor {

    private void print() {
        System.out.println("----- ----- ----- ----- -----");
        System.out.println(this.getClass().getName());
        System.out.println(this);
    }

    public XmlConstructor() {
        print();
        System.out.println("noArgsConstructor...");
    }
    public XmlConstructor(String str) {
        print();
        System.out.println("str : " + str);
    }

    public XmlConstructor(int num, String str) {
        print();
        System.out.println("num : " + num);
        System.out.println("num : " + str);
    }

    public XmlConstructor(int num, String str, boolean bool) {
        print();
        System.out.println("num : " + num);
        System.out.println("num : " + str);
        System.out.println("bool : " + bool);
    }

    public XmlConstructor(TestConstructor testConstructor) {
        print();
        System.out.println(testConstructor.getClass().getName());
    }



}
