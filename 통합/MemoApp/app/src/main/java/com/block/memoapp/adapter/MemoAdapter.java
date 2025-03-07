package com.block.memoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.block.memoapp.R;
import com.block.memoapp.UpdateActivity;
import com.block.memoapp.model.Memo;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    Context context;
    ArrayList<Memo> memoArrayList;

    public MemoAdapter(Context context, ArrayList<Memo> memoArrayList) {
        this.context = context;
        this.memoArrayList = memoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_row, parent, false);
        return new MemoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memo memo = memoArrayList.get(position);

        holder.txtTitle.setText(memo.title);
        holder.txtMemoDate.setText(memo.memoDate);
        holder.txtContent.setText(memo.content);
    }

    @Override
    public int getItemCount() {
        return memoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtMemoDate;
        TextView txtContent;
        ImageView imgDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtMemoDate = itemView.findViewById(R.id.txtMemoDate);
            txtContent = itemView.findViewById(R.id.txtContent);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    Memo memo = memoArrayList.get(index);
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("memo", memo);
                    context.startActivity(intent);
                }
            });
        }
    }
}
