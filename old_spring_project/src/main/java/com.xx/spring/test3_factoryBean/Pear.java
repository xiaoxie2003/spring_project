package com.xx.spring.test3_factoryBean;

public class Pear {
    private int id;

    public Pear() {
        System.out.println("Pear的构造");
    }

    @Override
    public String toString() {
        return "Pear{" +
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
