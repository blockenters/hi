package com.block.postingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.block.postingapp.R;
import com.block.postingapp.api.NetworkClient;
import com.block.postingapp.api.PostingApi;
import com.block.postingapp.config.Config;
import com.block.postingapp.model.Posting;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<Posting> postingArrayList;

    public PostAdapter(Context context, ArrayList<Posting> postingArrayList) {
        this.context = context;
        this.postingArrayList = postingArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posting posting = postingArrayList.get(position);

        holder.txtContent.setText(posting.content);
        holder.txtEmail.setText(posting.email);
        holder.txtCreatedAt.setText(posting.createdAt);

        Glide.with(context).load(posting.imgUrl).into(holder.img);

        if(posting.isLike == 0){
            holder.imgLike.setImageResource(R.drawable.thumb_up_white);
        }else{
            holder.imgLike.setImageResource(R.drawable.thumb_up_blue);
        }

    }

    @Override
    public int getItemCount() {
        return postingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtContent;
        TextView txtEmail;
        TextView txtCreatedAt;
        ImageView imgLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
            imgLike = itemView.findViewById(R.id.imgLike);

            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    Posting posting = postingArrayList.get(index);

                    Retrofit retrofit = NetworkClient.getRetrofitClient(context);
                    PostingApi api = retrofit.create(PostingApi.class);

                    String token = context.getSharedPreferences(Config.PREFERENCE_NAME, context.MODE_PRIVATE).getString(Config.TOKEN, "");

                    String status = "";
                    if(posting.isLike == 0){
                        status = "like";
                    }else{
                        status = "unlike";
                    }
                    Call<Void> call = api.setLike("Bearer "+token, posting.id , status);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){

                                if(posting.isLike == 0){
                                    posting.isLike = 1;
                                }else{
                                    posting.isLike = 0;
                                }
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

        }
    }
}



