package com.block.simplememo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.block.simplememo.model.Memo;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editBody;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTitle = findViewById(R.id.editTitle);
        editBody = findViewById(R.id.editBody);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String body = editBody.getText().toString().trim();

                if(title.isEmpty() || body.isEmpty()){
                    Toast.makeText(AddActivity.this,
                            "모두 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();

                Memo memo = new Memo();
                memo.title = title;
                memo.body = body;

                intent.putExtra("memo", memo);

                setResult(100, intent);

                finish();

            }
        });

    }
}




