package com.example.rally;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rally.models.Purchase;

import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder> {

    List<Purchase> purchases;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTransactionName;
        public TextView tvCost;
        public TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransactionName = (TextView) itemView.findViewById(R.id.tvTransactionName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);

        }
    }
    @NonNull
    @Override
    public PurchasesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View purchaseLayout = inflater.inflate(R.layout.expense_card,parent);

        ViewHolder viewHolder = new ViewHolder(purchaseLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesAdapter.ViewHolder viewHolder, int position) {
        Purchase purchase = purchases.get(position);

        viewHolder.tvTransactionName.setText(purchase.getItem());
        viewHolder.tvDate.setText(purchase.getDate().toString());
        viewHolder.tvCost.setText(String.valueOf(purchase.getPrice()));



    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }
}
