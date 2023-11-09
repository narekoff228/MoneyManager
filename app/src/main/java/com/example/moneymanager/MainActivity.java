package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.data.Categories;
import com.example.moneymanager.database.DataBaseManager;

public class MainActivity extends AppCompatActivity {
    Button categories, expenses, incomes;
    DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = findViewById(R.id.buttonShowCategory);
        expenses = findViewById(R.id.buttonShowExpenses);
        incomes = findViewById(R.id.buttonShowIncome);
        dbManager = new DataBaseManager(this);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showCatehoriesIntent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(showCatehoriesIntent);
            }
        });
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showExpensesIntent = new Intent(MainActivity.this, ExpensesActivity.class);
                startActivity(showExpensesIntent);
            }
        });
        incomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showIncomesIntent = new Intent(MainActivity.this, IncomesActivity.class);
                startActivity(showIncomesIntent);
            }
        });
        addCategory();
    }


    public void addCategory(){
        DataBaseManager dbManager = new DataBaseManager(this);
        dbManager.openDB();
        dbManager.addCategory(new Categories("Супермаркет"));
        dbManager.addCategory(new Categories("Автомагазин"));
        dbManager.addCategory(new Categories("АЗС"));
        dbManager.addCategory(new Categories("Заработная плата"));
        dbManager.closeDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}