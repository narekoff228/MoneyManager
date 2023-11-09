package com.example.moneymanager.database;

public class DataBaseConstant {
    /**create database and table Categories */
    public static final String DATA_BASE_NAME = "money.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String CATEGORIES_TABLE_NAME = "Categories";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name";

    /** table expenses */
    public static final String EXPENSES_TABLE_NAME = "Expenses";
    public static final String EXPENSE_ID = "id";
    public static final String EXPENSE_NAME_CATEGORY = "name_category";
    public static final String EXPENSE_SUM = "sum_expense";
    public static final String DATE_EXPENSE = "date";

    /** table incomes */
    public static final String INCOMES_TABLE_NAME = "Incomes";
    public static final String INCOME_ID = "id";
    public static final String INCOME_NAME_CATEGORY = "name_category";
    public static final String INCOME_SUM = "sum_income";
    public static final String DATE_INCOME = "date";

    /** create and delete tables */
    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE IF NOT EXISTS " +
            "" + CATEGORIES_TABLE_NAME + " ( " + CATEGORY_ID + " INTEGER PRIMARY KEY, " +
            "" + CATEGORY_NAME + " TEXT UNIQUE);";
    public static final String DELETE_TABLE_CATEGORIES = "DROP TABLE IF EXISTS " + CATEGORIES_TABLE_NAME;

    public static final String CREATE_TABLE_EXPENSES = "CREATE TABLE IF NOT EXISTS " +
            "" + EXPENSES_TABLE_NAME + " ( " + EXPENSE_ID + " INTEGER PRIMARY KEY, " +
            "" +  DATE_EXPENSE + " TEXT, " + EXPENSE_NAME_CATEGORY + " TEXT, " + EXPENSE_SUM + " INTEGER);";
    public static final String DELETE_TABLE_EXPENSES = "DROP TABLE IF EXISTS " + EXPENSES_TABLE_NAME;

    public static final String CREATE_TABLE_INCOMES = "CREATE TABLE IF NOT EXISTS " +
            "" + INCOMES_TABLE_NAME + " ( " + INCOME_ID + " INTEGER PRIMARY KEY, " +
            "" +  DATE_INCOME + " TEXT, " + INCOME_NAME_CATEGORY + " TEXT, " + INCOME_SUM + " INTEGER);";
    public static final String DETELE_TABLE_INCOMES = "DROP TABLES IF EXISTS " + INCOMES_TABLE_NAME;
}
