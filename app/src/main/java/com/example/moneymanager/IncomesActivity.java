package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.adapters.ExpensesAdapter;
import com.example.moneymanager.adapters.IncomesAdapter;
import com.example.moneymanager.data.Expenses;
import com.example.moneymanager.data.Incomes;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class IncomesActivity extends AppCompatActivity {

    DataBaseManager dbManager;
    List<Incomes> incomes;
    RecyclerView recyclerView;
    Button addIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
        addIncome = findViewById(R.id.addIncome);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        incomes = dbManager.getIncomes();
        IncomesAdapter incomesAdapter = new IncomesAdapter(incomes, this, dbManager);
        if (incomes.size() > 0){
            recyclerView.setAdapter(incomesAdapter);
        }
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(IncomesActivity.this, AddIncomeActivity.class);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        incomes = dbManager.getIncomes();
        IncomesAdapter incomesAdapter = new IncomesAdapter(incomes, this, dbManager);
        if (incomes.size() > 0){
            recyclerView.setAdapter(incomesAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}