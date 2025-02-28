package com.block.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editAge;
    Button btnSave;

    TextView txtResult;

    // 양방향으로 데이터 전송하기 위해서 런처 필요
    ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if( o.getResultCode() == RESULT_CANCELED ){
                                // setResult(RESULT_CANCELED, intent);
                                // SecondActivity가 setResult() 에 보낸 코드 :  o.getResultCode()
                                // SecondActivity가 setResult() 에 보낸, 인텐트 :  o.getData()
                                String data = o.getData().getStringExtra("data");
                                txtResult.setText(data);
                            }
                        }
                    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        btnSave = findViewById(R.id.btnSave);
        txtResult = findViewById(R.id.txtResult);

        // 버튼 누르면, 다음 화면을 실행시킨다.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이름과 나이를 받아온다.
                String name = editName.getText().toString().trim();
                String strAge = editAge.getText().toString().trim();

                if(name.isEmpty() || strAge.isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "이름과 나이는 필수입니다.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                int age = Integer.parseInt(strAge);

                // 액티비티를 띄울때는 Intent 를 사용한다.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                // 단방향으로 보낼때만 사용한다.
//                startActivity(intent);
                launcher.launch(intent);
            }
        });

        Log.i("MyApp", "Main : onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MyApp", "Main : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MyApp", "Main : onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyApp", "Main : onDestroy");
    }
}