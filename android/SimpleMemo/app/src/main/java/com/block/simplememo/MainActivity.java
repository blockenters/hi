package com.block.simplememo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.block.simplememo.adapter.MemoAdapter;
import com.block.simplememo.api.MemoApi;
import com.block.simplememo.api.NetworkClient;
import com.block.simplememo.model.Memo;
import com.block.simplememo.model.MemoResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // 리사이클러뷰는 같이 사용하는 멤버변수가 있다.
    RecyclerView recyclerView;
    ArrayList<Memo> memoArrayList = new ArrayList<>();
    MemoAdapter adapter;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰 작업.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        fab = findViewById(R.id.fab);

        adapter = new MemoAdapter(MainActivity.this, memoArrayList);
        recyclerView.setAdapter(adapter);

        getNetworkData();
    }

    // 네트워크 통해서 데이터 가져오는 함수.
    void getNetworkData(){
        // 1. 레트로핏 클라이언트를 만든다.
        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);

        // 2. API를 만든다.
        MemoApi api = retrofit.create(MemoApi.class);

        // 3. api 호출한다.
        Call<MemoResponse> call = api.getMemoList();
        call.enqueue(new Callback<MemoResponse>() {
            @Override
            public void onResponse(Call<MemoResponse> call, Response<MemoResponse> response) {
                // 200 OK 일때랑 아닐때 처리를 한다.
                if( response.isSuccessful() ){

                    MemoResponse memoResponse = response.body();
                    memoArrayList.addAll( memoResponse.data );

                    adapter.notifyDataSetChanged();

                }else {

                    Toast.makeText(MainActivity.this,
                            "서버 처리가 실패했습니다.",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MemoResponse> call, Throwable throwable) {

            }
        });

    }

}



