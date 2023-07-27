package com.xx.spring.test2;

public class Apple {
    private int id;

    public Apple() {
        System.out.println("apple的构造");
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
