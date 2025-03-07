package com.block.memoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.block.memoapp.adapter.MemoAdapter;
import com.block.memoapp.api.MemoApi;
import com.block.memoapp.api.NetworkClient;
import com.block.memoapp.config.Config;
import com.block.memoapp.model.Memo;
import com.block.memoapp.model.MemoListRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // 같이 적어주는 멤버변수 있다.
    RecyclerView recyclerView;
    ArrayList<Memo> memoArrayList = new ArrayList<>();
    MemoAdapter adapter;


    Button btnAdd;
    String token;

    // api 관련 멤버 변수
    int page;
    int size = 8;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 로그인 한 유저인지, 안한 유저인지 확인해서
        //    로그인 안했으면, 회원가입 액티비티를 실행한다.
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        token = sp.getString(Config.TOKEN, "" );
        if (token.isEmpty()){
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 처리할거 있다.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        btnAdd = findViewById(R.id.btnAdd);

        adapter = new MemoAdapter(MainActivity.this, memoArrayList);
        recyclerView.setAdapter(adapter);
        // 리사이클러뷰를 유저가 스크롤해서, 맨 마지막 데이터가 화면에 보일때!
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 맨 마지막 데이터가 화면에 보일때, api 를 호출한다.
                int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();
                if(lastPosition + 1  == totalCount ){
                    // api 호출한다.
                    if(count < size) {
                        return;
                    }
                    addNetworkData();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    // 메인 액티비티가 다시 화면에 보일때마다 실행되는 함수 onResume 함수.
    @Override
    protected void onResume() {
        super.onResume();

        getNetworkData();

    }

    void addNetworkData(){
        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);
        MemoApi api = retrofit.create(MemoApi.class);

        page = page + 1;

        Call<MemoListRes> call = api.getMemoList("Bearer "+token, page, size);
        call.enqueue(new Callback<MemoListRes>() {
            @Override
            public void onResponse(Call<MemoListRes> call, Response<MemoListRes> response) {
                if(response.isSuccessful()){

                    memoArrayList.addAll( response.body().items );
                    adapter.notifyDataSetChanged();

                    count = response.body().count;

                }else{

                }
            }

            @Override
            public void onFailure(Call<MemoListRes> call, Throwable throwable) {

            }
        });
    }


    // 맨 처음 데이터를 가져올때만 이 함수를 호출한다.
    void getNetworkData(){

        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);
        MemoApi api = retrofit.create(MemoApi.class);

        page = 1;

        Call<MemoListRes> call = api.getMemoList("Bearer "+token, page, size);
        call.enqueue(new Callback<MemoListRes>() {
            @Override
            public void onResponse(Call<MemoListRes> call, Response<MemoListRes> response) {
                if(response.isSuccessful()){

                    memoArrayList.clear();
                    memoArrayList.addAll( response.body().items );
                    adapter.notifyDataSetChanged();

                    count = response.body().count;

                }else{

                }
            }

            @Override
            public void onFailure(Call<MemoListRes> call, Throwable throwable) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}







