package com.example.moneymanager.data;

import java.io.Serializable;

public class Categories implements Serializable {
    private int id;
    private String name;

    public Categories(String name) {
        this.name = name;
    }

    public Categories(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
