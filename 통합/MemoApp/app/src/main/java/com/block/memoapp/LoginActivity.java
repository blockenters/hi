package com.block.memoapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.block.memoapp.api.NetworkClient;
import com.block.memoapp.api.UserApi;
import com.block.memoapp.config.Config;
import com.block.memoapp.model.LoginReq;
import com.block.memoapp.model.LoginRes;
import com.block.memoapp.util.EmailValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    Button btnLogin;
    TextView txtSignup;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                            "이메일과 비번을 필수로 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(  EmailValidator.isValidEmail(email) == false ){
                    Toast.makeText(LoginActivity.this,
                            "이메일을 올바르게 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 네트워크 실행중이라는 프로그레스바 나오게 한다.
                showProgress();

                Retrofit retrofit = NetworkClient.getRetrofitClient(LoginActivity.this);
                UserApi api = retrofit.create(UserApi.class);
                LoginReq loginReq = new LoginReq(email, password);
                Call<LoginRes> call = api.login(loginReq);
                call.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        // 네트워크 실행을 끝내도록, 프로그레스바를 없앤다.
                        dismissProgress();

                        if( response.isSuccessful() ){
                            // 응답으로 온 토큰을, 앱 내 저장소에 저장한다.
                            SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString(Config.TOKEN , response.body().token);
                            editor.apply();
                            // 메인 액티비티를 띄우고, 로그인 액티비티는 종료한다.
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable throwable) {
                        // 네트워크 실행을 끝내도록, 프로그레스바를 없앤다.
                        dismissProgress();
                    }
                });

            }
        });

    }


    void showProgress(){
        dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(new ProgressBar(this));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    void dismissProgress(){
        dialog.dismiss();
    }
}