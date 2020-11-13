package com.example.rally;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rally.models.Purchase;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder> {

    List<Purchase> purchases;


    OnItemClickListener clickListener;
    public interface OnItemClickListener  {
        void onItemClick(int position);
    }
    public PurchasesAdapter(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PurchasesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View purchaseLayout = inflater.inflate(R.layout.expense_card,parent,false);

        ViewHolder viewHolder = new ViewHolder(purchaseLayout,clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesAdapter.ViewHolder viewHolder, int position) {
        Purchase purchase = purchases.get(position);
        viewHolder.bind(purchase);


    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTransactionName;
        public TextView tvCost;
        public TextView tvDate;
        public ImageButton btRemove;

        public ViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            tvTransactionName = (TextView) itemView.findViewById(R.id.tvTransactionName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            btRemove = (ImageButton) itemView.findViewById(R.id.btRemove);
            btRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Purchase purchase)
        {
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            tvDate.setText(sdf.format(purchase.getDate().getTime()));
            tvTransactionName.setText(purchase.getItem());
            tvCost.setText(String.valueOf(purchase.getPrice()));


        }
    }

}
