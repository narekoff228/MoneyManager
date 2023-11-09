package com.example.moneymanager.database;

import static com.example.moneymanager.database.DataBaseConstant.CATEGORIES_TABLE_NAME;
import static com.example.moneymanager.database.DataBaseConstant.CATEGORY_ID;
import static com.example.moneymanager.database.DataBaseConstant.CATEGORY_NAME;
import static com.example.moneymanager.database.DataBaseConstant.DATE_EXPENSE;
import static com.example.moneymanager.database.DataBaseConstant.EXPENSES_TABLE_NAME;
import static com.example.moneymanager.database.DataBaseConstant.EXPENSE_ID;
import static com.example.moneymanager.database.DataBaseConstant.EXPENSE_NAME_CATEGORY;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moneymanager.data.Categories;
import com.example.moneymanager.data.Expenses;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseConstant.DATA_BASE_NAME, null, DataBaseConstant.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_CATEGORIES);
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_EXPENSES);
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_INCOMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseConstant.DELETE_TABLE_CATEGORIES);
        sqLiteDatabase.execSQL(DataBaseConstant.DELETE_TABLE_EXPENSES);
        sqLiteDatabase.execSQL(DataBaseConstant.DETELE_TABLE_INCOMES);
    }
}
