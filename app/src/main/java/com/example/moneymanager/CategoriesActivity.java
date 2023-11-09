package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.adapters.CategoriesAdapter;
import com.example.moneymanager.data.Categories;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    DataBaseManager dbManager;
    List<Categories> categories;
    RecyclerView recyclerView;
    Button addCategory;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        addCategory = findViewById(R.id.buttonAddCategory);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
                startActivity(addIntent);
            }
        });
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        categories = dbManager.getCategories();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, this, dbManager);
        if (categories.size() > 0){
            recyclerView.setAdapter(categoriesAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        categories = dbManager.getCategories();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, this, dbManager);
        if (categories.size() > 0){
            recyclerView.setAdapter(categoriesAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}