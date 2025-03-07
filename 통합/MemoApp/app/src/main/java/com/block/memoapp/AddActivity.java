package com.block.memoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.block.memoapp.api.MemoApi;
import com.block.memoapp.api.NetworkClient;
import com.block.memoapp.config.Config;
import com.block.memoapp.model.Memo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    Button btnDate;
    Button btnTime;
    EditText editContent;
    Button btnSave;

    // 유저가 선택한 년월일 ( 2025-03-05 )
    String date = "";
    // 유저가 선택한 시분 ( 04:15 )
    String time = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTitle = findViewById(R.id.editTitle);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this);
                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.i("AddActivity", year + "-" + month + "-" + dayOfMonth);

                        String strMonth;
                        if( month + 1 < 10 ){
                            strMonth = "0" + (month+1);
                        }else{
                            strMonth = "" + (month+1);
                        }

                        String strDay;
                        if( dayOfMonth < 10 ){
                            strDay = "0" + dayOfMonth;
                        }else{
                            strDay = "" + dayOfMonth;
                        }

                        date = year + "-" + strMonth + "-" + strDay;

                        btnDate.setText(date);

                    }
                });
                dialog.show();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Log.i("AddActivity", hourOfDay + ":" + minute);

                                String strHour;
                                if(hourOfDay < 10){
                                    strHour = "0" + hourOfDay;
                                }else{
                                    strHour = ""+hourOfDay;
                                }

                                String strMin;
                                if(minute < 10){
                                    strMin = "0" + minute;
                                }else{
                                    strMin = "" + minute;
                                }

                                time = strHour + ":" + strMin;

                                btnTime.setText(time);

                            }
                        },
                        13,
                        0,
                        false
                );
                dialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();

                if(title.isEmpty() || content.isEmpty() || date.isEmpty() || time.isEmpty()  ){
                    Toast.makeText(AddActivity.this,
                            "항목을 모두 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // API 호출 한다.
                showProgress();

                Retrofit retrofit = NetworkClient.getRetrofitClient(AddActivity.this);
                MemoApi api = retrofit.create(MemoApi.class);

                SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
                String token = sp.getString(Config.TOKEN, "");
                Memo memo = new Memo(title, content, date+" "+time);
                Call<Void> call = api.createMemo("Bearer " +token, memo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        dismissProgress();

                        if(response.isSuccessful()){

                            finish();

                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        dismissProgress();
                    }
                });

            }
        });

    }


    Dialog dialog;
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




