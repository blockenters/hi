package com.block.alarm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView imgClock;
    TextView txtTime;
    EditText editTime;
    Button btnCancel;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgClock = findViewById(R.id.imgClock);
        txtTime = findViewById(R.id.txtTime);
        editTime = findViewById(R.id.editTime);
        btnCancel = findViewById(R.id.btnCancel);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 유저가 입력한 초를 가져온다.
                String strTime = editTime.getText().toString().trim();
                if(strTime.isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "초를 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int time = Integer.parseInt(strTime);
                long countTime = time * 1000;

                // 2. 타이머를 동작시킨다.
                CountDownTimer countDownTimer= new CountDownTimer(countTime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // 3. 초가 바뀔때마다 화면에, 남은 초를 표시
                        txtTime.setText( "" + (millisUntilFinished / 1000) );
                    }

                    @Override
                    public void onFinish() {
                        // 4. 초가 0이 되면, 알람 소리도 나고, 시계도 흔들린다.
                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alarm);
                        mediaPlayer.start();

                        YoYo.with(Techniques.Shake).duration(400).repeat(4).playOn(imgClock);

                    }
                };

                countDownTimer.start();

            }
        });

    }
}








