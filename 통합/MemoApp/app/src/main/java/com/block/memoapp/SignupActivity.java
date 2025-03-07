package com.block.memoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.block.memoapp.api.NetworkClient;
import com.block.memoapp.api.UserApi;
import com.block.memoapp.model.SignupReq;
import com.block.memoapp.util.EmailValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    EditText editNickname;
    Button btnSave;
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editNickname = findViewById(R.id.editNickname);
        btnSave = findViewById(R.id.btnSave);
        txtLogin = findViewById(R.id.txtLogin);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String nickname = editNickname.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty() || nickname.isEmpty()){
                    Toast.makeText(SignupActivity.this,
                            "항목을 모두 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 이메일 형식 체크
                if(EmailValidator.isValidEmail(email) == false ){
                    Toast.makeText(SignupActivity.this,
                            "이메일 형식이 올바르지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // API 를 호출한다.
                Retrofit retrofit = NetworkClient.getRetrofitClient(SignupActivity.this);
                UserApi api = retrofit.create(UserApi.class);

                SignupReq signupReq = new SignupReq(email, password, nickname);

                Call<Void> call = api.signup(signupReq);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if( response.isSuccessful() ){
                            // 로그인 액티비티 띄우고, 회원가입 액티비티는 종료한다.
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // 유저한테 알려준다.
                            if(response.code() == 400){
                                Toast.makeText(SignupActivity.this,
                                        "이미 회원가입 하셨습니다.",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                Toast.makeText(SignupActivity.this,
                                        "서버에 이상이 있으니 잠시후 다시 시도하세요.",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        // 데이터 접속이 원할하지 않습니다. 확인하세요.
                    }
                });
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            }
        });

    }
}




