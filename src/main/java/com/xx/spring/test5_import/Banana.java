package com.xx.spring.test5_import;

public class Banana {
    private int id;

    public Banana() {
        System.out.println("Banana的构造");
    }

    @Override
    public String toString() {
        return "Banana{" +
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
