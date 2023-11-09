package com.example.moneymanager.data;

import java.io.Serializable;

public class Incomes implements Serializable {
    private int id;
    private String name_category;
    private String date;
    private int sum;

    public Incomes(String name_category, String date, int sum) {
        this.name_category = name_category;
        this.date = date;
        this.sum = sum;
    }

    public Incomes(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

