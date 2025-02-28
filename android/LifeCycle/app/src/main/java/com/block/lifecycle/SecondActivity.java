package com.block.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtAge;

    String data;

    OnBackPressedCallback onBack = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Intent intent = new Intent();
            intent.putExtra("data", data);
            setResult(RESULT_CANCELED, intent);
            // 액티비티를 종료하라.
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("MyApp", "SecondActivity : onCreate");

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);

        // 메인 액티비티가 보낸 데이터를 받아준다.
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", -1);

        // 화면에 표시한다.
        txtName.setText("이름은 "+name + "입니다.");
        txtAge.setText("나이는 "+age + "세 입니다.");

        data = "이름은 "+name + " , 나이는 " + age;

        // back 버튼 누를때, 아래 코드가 동작한다. => onBack 에 작성한 코드가 동작.
        getOnBackPressedDispatcher().addCallback(onBack);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MyApp", "SecondActivity : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MyApp", "SecondActivity : onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyApp", "SecondActivity : onDestroy");
    }
}