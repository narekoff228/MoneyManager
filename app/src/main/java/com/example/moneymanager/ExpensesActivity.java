package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.adapters.CategoriesAdapter;
import com.example.moneymanager.adapters.ExpensesAdapter;
import com.example.moneymanager.data.Categories;
import com.example.moneymanager.data.Expenses;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class ExpensesActivity extends AppCompatActivity {

    DataBaseManager dbManager;
    List<Expenses> expenses;
    RecyclerView recyclerView;
    Button addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        addExpense = findViewById(R.id.buttonAddExpense);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        expenses = dbManager.getExpenses();
        ExpensesAdapter expensesAdapter = new ExpensesAdapter(expenses, this, dbManager);
        if (expenses.size() > 0){
            recyclerView.setAdapter(expensesAdapter);
        }
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(ExpensesActivity.this, AddExpenseActivity.class);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        expenses = dbManager.getExpenses();
        ExpensesAdapter expensesAdapter = new ExpensesAdapter(expenses, this, dbManager);
        if (expenses.size() > 0){
            recyclerView.setAdapter(expensesAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}