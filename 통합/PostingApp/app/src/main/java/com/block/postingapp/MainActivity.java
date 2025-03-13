package com.block.postingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.block.postingapp.adapter.PostAdapter;
import com.block.postingapp.api.NetworkClient;
import com.block.postingapp.api.PostingApi;
import com.block.postingapp.config.Config;
import com.block.postingapp.model.Posting;
import com.block.postingapp.model.PostingListRes;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Posting> postingArrayList = new ArrayList<>();
    PostAdapter adapter;

    FloatingActionButton btnAdd;


    int page = 1;
    int size = 8;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String token = sp.getString(Config.TOKEN, "");
        if(token.isEmpty()){
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        adapter = new PostAdapter(MainActivity.this, postingArrayList);
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        getNetworkData();
    }

    private void getNetworkData() {

        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);
        PostingApi api = retrofit.create(PostingApi.class);

        String token = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE).getString(Config.TOKEN, "");

        page = 1;

        Call<PostingListRes> call = api.getPostingList("Bearer "+token, page, size );
        call.enqueue(new Callback<PostingListRes>() {
            @Override
            public void onResponse(Call<PostingListRes> call, Response<PostingListRes> response) {
                if(response.isSuccessful()){

                    postingArrayList.clear();
                    postingArrayList.addAll(  response.body().items  );

                    count = response.body().count;

                    adapter.notifyDataSetChanged();

                }else{

                }
            }

            @Override
            public void onFailure(Call<PostingListRes> call, Throwable throwable) {

            }
        });

    }


}