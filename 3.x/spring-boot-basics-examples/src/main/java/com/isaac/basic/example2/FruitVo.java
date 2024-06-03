package com.isaac.basic.example2;

public class FruitVo {
    private String name;
    private int cost;

    public FruitVo() {}
    public FruitVo(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
