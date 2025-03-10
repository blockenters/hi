package com.block.imagelist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.block.imagelist.adapter.ItemAdapter;
import com.block.imagelist.api.ItemApi;
import com.block.imagelist.api.NetworkClient;
import com.block.imagelist.model.Item;
import com.block.imagelist.model.ItemListRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> itemArrayList = new ArrayList<>();
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new ItemAdapter(MainActivity.this, itemArrayList);
        recyclerView.setAdapter(adapter);

        getNetworkData();
    }

    void getNetworkData(){
        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);
        ItemApi api = retrofit.create(ItemApi.class);
        Call<ItemListRes> call = api.getItemList();
        call.enqueue(new Callback<ItemListRes>() {
            @Override
            public void onResponse(Call<ItemListRes> call, Response<ItemListRes> response) {
                if(response.isSuccessful()){
                    itemArrayList.addAll( response.body().items );
                    adapter.notifyDataSetChanged();
                }else{

                }
            }

            @Override
            public void onFailure(Call<ItemListRes> call, Throwable throwable) {

            }
        });
    }
}









