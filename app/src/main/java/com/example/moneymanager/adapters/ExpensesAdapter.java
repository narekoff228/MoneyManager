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
import com.example.moneymanager.data.Expenses;
import com.example.moneymanager.database.DataBaseManager;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Expenses> expenses;
    DataBaseManager dbManager;

    public ExpensesAdapter(List<Expenses> expenses, Context context, DataBaseManager dbManager){
        this.expenses = expenses;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.expense_list, parent, false);
        return new ExpensesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ViewHolder holder, int position) {
        Expenses expense = expenses.get(position);
        holder.id.setText(String.valueOf(expense.getId()));
        holder.date.setText(expense.getDate());
        holder.nameCategory.setText(expense.getName_category());
        holder.sum.setText(String.valueOf(expense.getSum()));
        holder.nameCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка удалить запись о расходе
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDB();
                        dbManager.deleteExpense(expense);
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
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nameCategory, date, sum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idExpenseCategory);
            date = itemView.findViewById(R.id.dateExpense);
            nameCategory = itemView.findViewById(R.id.nameExpenseCategory);
            sum = itemView.findViewById(R.id.sumExpense);
        }
    }
}
