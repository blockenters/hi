package com.block.postingapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_GALLERY_IMAGE = 2;

    private ImageView postImageView;
    private ImageButton addImageButton;
    private TextInputEditText contentEditText;
    private MaterialButton postButton;
    private Uri selectedImageUri = null;

    // ActivityResultLauncher for gallery image selection
    private final ActivityResultLauncher<String> galleryLauncher = 
        registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedImageUri = uri;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    postImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "이미지 로드 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        // 액션바 타이틀 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("포스팅 앱");
        }
        
        // 뷰 초기화
        initViews();
        
        // 리스너 설정
        setupListeners();
    }
    
    private void initViews() {
        postImageView = findViewById(R.id.img);
        addImageButton = findViewById(R.id.addImageButton);
        contentEditText = findViewById(R.id.contentEditText);
        postButton = findViewById(R.id.postButton);
    }
    
    private void setupListeners() {
        // 이미지 추가 버튼 클릭시
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 갤러리에서 이미지 선택
                galleryLauncher.launch("image/*");
            }
        });
        
        // 포스트 생성 버튼 클릭시
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentEditText.getText().toString().trim();
                
                if (validateInput(content)) {
                    // 포스트 업로드 로직 실행
                    uploadPost(content, selectedImageUri);
                }
            }
        });
    }
    
    private boolean validateInput(String content) {
        boolean isValid = true;
        
        if (content.isEmpty()) {
            contentEditText.setError("내용을 입력해주세요");
            isValid = false;
        }
        
        if (selectedImageUri == null) {
            Toast.makeText(this, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        
        return isValid;
    }
    
    private void uploadPost(String content, Uri imageUri) {
        // 여기에 실제 포스트 업로드 API 호출 코드를 추가합니다
        // 예시로 Toast 메시지를 표시합니다
        Toast.makeText(this, "포스트 업로드 중...", Toast.LENGTH_SHORT).show();
        
        // 업로드 성공 후 메인 화면으로 이동
        // Intent intent = new Intent(AddActivity.this, MainActivity.class);
        // startActivity(intent);
        // finish();
    }
}