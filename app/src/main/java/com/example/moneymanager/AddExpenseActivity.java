package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneymanager.data.Categories;
import com.example.moneymanager.data.Expenses;
import com.example.moneymanager.database.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class AddExpenseActivity extends AppCompatActivity {
    Spinner category;
    EditText date, sum;
    Button save;
    DataBaseManager dbManager;
    Expenses newExpense = new Expenses();
    String nameCategory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        category = findViewById(R.id.spinnerExpenseCategory);
        date = findViewById(R.id.editDateExpense);
        save = findViewById(R.id.buttonSaveExpense);
        sum = findViewById(R.id.addSumExpense);
        List<String> categories = dbManager.getNameCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameCategory = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        category.setOnItemSelectedListener(itemSelectedListener);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newExpense.setName_category(nameCategory);
                newExpense.setDate(date.getText().toString());
                newExpense.setSum(Integer.parseInt(sum.getText().toString()));
                dbManager.addExpense(newExpense);
                Toast.makeText(AddExpenseActivity.this,
                        "Запись сохранена!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}