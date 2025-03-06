package com.block.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.block.contactapp.adapter.ContactAdapter;
import com.block.contactapp.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    // 리사이클러뷰는 같이 작성하는 멤버변수가 있다.
    RecyclerView recyclerView;
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    ContactAdapter adapter;


    public ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if( o.getResultCode() == 100 ){
                        String name = o.getData().getStringExtra("name");
                        String phone = o.getData().getStringExtra("phone");
                        Contact contact = new Contact(name, phone);
                        contactArrayList.add(0, contact);
                        Log.i("Contact Main", "이름은 : " + name + " , 폰번 : " + phone);
                        Log.i("Contact Main", "리스트의 갯수는 : " + contactArrayList.size());
                        adapter.notifyDataSetChanged();
                    } else if( o.getResultCode() == 101 ){
                        Contact contact = (Contact) o.getData().getSerializableExtra("contact");
                        int index = o.getData().getIntExtra("index", -1);
                        // 데이터를 바꾼다.
                        contactArrayList.set(index, contact);
                        // 데이터를 바꾼후에는 화면에 적용해라.
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        // 리사이클러뷰 관련 작업해준다.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // 어댑터를 만들고, 리사이클러뷰에 연결시킨다.
        adapter = new ContactAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                // 액티비티 실행을, launcher 로 실행해야, CreateActivity가 보내는 데이터를 받을 수 있다.
                launcher.launch(intent);

            }
        });
    }


}







