package com.block.memoapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.block.memoapp.R;
import com.block.memoapp.UpdateActivity;
import com.block.memoapp.api.MemoApi;
import com.block.memoapp.api.NetworkClient;
import com.block.memoapp.config.Config;
import com.block.memoapp.model.Memo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        String[] strMemoDate = memo.memoDate.substring(0, 15+1).split(" ");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy년 mm월 dd일");
        try {
            Date date = sf.parse(strMemoDate[0]);
            String strDate = df.format(date);
            holder.txtMemoDate.setText(strDate + " " + strMemoDate[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제");
                    builder.setMessage("정말 삭제하시겠습니까?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Retrofit retrofit = NetworkClient.getRetrofitClient(context);
                            MemoApi api = retrofit.create(MemoApi.class);

                            int index = getAdapterPosition();
                            Memo memo = memoArrayList.get(index);

                            SharedPreferences sp = context.getSharedPreferences(Config.PREFERENCE_NAME, context.MODE_PRIVATE);
                            String token = sp.getString(Config.TOKEN, "");

                            Call<Void> call = api.deleteMemo(memo.id, "Bearer " + token );
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        memoArrayList.remove(index);
                                        notifyDataSetChanged();
                                    }else{

                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable throwable) {

                                }
                            });
                        }
                    });
                    builder.setNegativeButton("NO", null);
                    builder.show();
                }
            });
        }
    }
}
