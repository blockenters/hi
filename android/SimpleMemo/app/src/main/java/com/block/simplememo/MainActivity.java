package com.block.simplememo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
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

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if( o.getResultCode() == 100 ){
                        Memo memo = (Memo) o.getData().getSerializableExtra("memo");
                        memoArrayList.add(0,memo);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 상태바 투명 설정 (Android 11 이상에서 권장되는 방식)
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
        
        // 상태바 아이콘 색상 설정 (어두운 배경에 밝은 아이콘)
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        controller.setAppearanceLightStatusBars(false);
        
        // 액션바 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SimpleMemo");
        }
        
        setContentView(R.layout.activity_main);

        // 리사이클러뷰 작업.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        fab = findViewById(R.id.fab);

        adapter = new MemoAdapter(MainActivity.this, memoArrayList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                launcher.launch(intent);
            }
        });

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


    // menu 폴더에 있는, 액션바의 메뉴를, 화면에 표시하는 함수다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}



