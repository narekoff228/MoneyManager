package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.data.Categories;
import com.example.moneymanager.database.DataBaseManager;

public class AddCategoryActivity extends AppCompatActivity {
    EditText name;
    Button saveNew;
    DataBaseManager dbManager;
    Categories newCategory = new Categories();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        name = findViewById(R.id.nameOldCategory);
        saveNew = findViewById(R.id.buttonSaveChangeCategory);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCategory.setName(name.getText().toString());
                dbManager.addCategory(newCategory);
                Toast.makeText(AddCategoryActivity.this,
                        "Категория сохранена!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}