package com.example.moneymanager.database;

import static com.example.moneymanager.database.DataBaseConstant.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moneymanager.data.Categories;
import com.example.moneymanager.data.Expenses;
import com.example.moneymanager.data.Incomes;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    @SuppressLint("Range")
    public List<Categories> getCategories(){
        List<Categories> categories = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + CATEGORIES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Categories category = new Categories();
            category.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CATEGORY_ID))));
            category.setName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));
            categories.add(category);
        }
        cursor.close();
        return categories;
    }

    @SuppressLint("Range")
    public List<String> getNameCategories(){
        List<String> allCategories = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + CATEGORIES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(CATEGORY_NAME));
            allCategories.add(name);
        }
        cursor.close();
        return allCategories;
    }

    public void deleteCategory(Categories category){
        db.delete(CATEGORIES_TABLE_NAME, CATEGORY_ID
                + " = " + category.getId(), null);
    }

    public void addCategory(Categories category){
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME, category.getName());
        db.insert(CATEGORIES_TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Expenses> getExpenses(){
        List<Expenses> expenses = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + EXPENSES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Expenses expense = new Expenses();
            expense.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EXPENSE_ID))));
            expense.setName_category(cursor.getString(cursor.getColumnIndex(EXPENSE_NAME_CATEGORY)));
            expense.setDate(cursor.getString(cursor.getColumnIndex(DATE_EXPENSE)));
            expense.setSum(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EXPENSE_SUM))));
            expenses.add(expense);
        }
        cursor.close();
        return expenses;
    }

    public void deleteExpense(Expenses expense){
        db.delete(EXPENSES_TABLE_NAME, EXPENSE_ID
                + " = " + expense.getId(), null);
    }

    public void addExpense(Expenses expense){
        ContentValues cv = new ContentValues();
        cv.put(EXPENSE_NAME_CATEGORY, expense.getName_category());
        cv.put(DATE_EXPENSE, expense.getDate());
        cv.put(EXPENSE_SUM, expense.getSum());
        db.insert(EXPENSES_TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Incomes> getIncomes(){
        List<Incomes> incomes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + INCOMES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Incomes income = new Incomes();
            income.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(INCOME_ID))));
            income.setDate(cursor.getString(cursor.getColumnIndex(DATE_INCOME)));
            income.setName_category(cursor.getString(cursor.getColumnIndex(INCOME_NAME_CATEGORY)));
            income.setSum(Integer.parseInt(cursor.getString(cursor.getColumnIndex(INCOME_SUM))));
            incomes.add(income);
        }
        cursor.close();
        return incomes;
    }

    public void deleteIncome(Incomes income){
        db.delete(INCOMES_TABLE_NAME, INCOME_ID
                + " = " + income.getId(), null);
    }

    public void addIncome(Incomes income){
        ContentValues cv = new ContentValues();
        cv.put(INCOME_NAME_CATEGORY, income.getName_category());
        cv.put(DATE_INCOME, income.getDate());
        cv.put(INCOME_SUM, income.getSum());
        db.insert(INCOMES_TABLE_NAME, null, cv);
    }
}
