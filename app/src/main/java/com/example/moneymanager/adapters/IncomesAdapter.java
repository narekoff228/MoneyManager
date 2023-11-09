package com.example.moneymanager.adapters;

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
import com.example.moneymanager.data.Incomes;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class IncomesAdapter extends RecyclerView.Adapter<IncomesAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Incomes> incomes;
    DataBaseManager dbManager;

    public IncomesAdapter(List<Incomes> incomes, Context context, DataBaseManager dbManager){
        this.incomes = incomes;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public IncomesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.income_list, parent, false);
        return new IncomesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomesAdapter.ViewHolder holder, int position) {
        Incomes income = incomes.get(position);
        holder.id.setText(String.valueOf(income.getId()));
        holder.date.setText(income.getDate());
        holder.nameCategory.setText(income.getName_category());
        holder.sum.setText(String.valueOf(income.getSum()));
        holder.nameCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка удалить запись о доходе
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDB();
                        dbManager.deleteIncome(income);
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
        return incomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nameCategory, date, sum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idIncome);
            date = itemView.findViewById(R.id.dateIncome);
            nameCategory = itemView.findViewById(R.id.nameCategory);
            sum = itemView.findViewById(R.id.sumIncome);
        }
    }
}
