package com.example.moneymanager.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.data.Categories;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Categories> categories;
    DataBaseManager dbManager;

    public CategoriesAdapter(List<Categories> categories, Context context, DataBaseManager dbManager){
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        Categories category = categories.get(position);
        holder.id.setText(String.valueOf(category.getId()));
        holder.name.setText(category.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка удалить категорию
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDB();
                        dbManager.deleteCategory(category);
                        dbManager.closeDB();
                    }
                });
                // Кнопка отмена
                builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idExpenseCategory);
            name = itemView.findViewById(R.id.nameExpenseCategory);
        }
    }
}
