package com.block.cameraapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1000; // 카메라 권한 요청 코드
    private static final int REQUEST_STORAGE_PERMISSION = 500; // 저장소 권한 요청 코드
    private static final int REQUEST_CAMERA = 100; // 카메라 요청 코드
    private static final int REQUEST_ALBUM = 300; // 앨범 요청 코드

    private Button button; // 액션 버튼
    private ImageView imageView; // 이미지 미리보기
    private File photoFile; // 사진 파일

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        // 버튼 클릭 시 다이얼로그 표시
        button.setOnClickListener(view -> showDialog());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("사진 선택")
                .setItems(new String[]{"카메라로 찍기", "앨범에서 선택"}, (dialogInterface, i) -> {
                    if (i == 0) {
                        camera(); // 카메라 실행
                    } else if (i == 1) {
                        album(); // 앨범 실행
                    }
                });
        builder.show();
    }

    private void camera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            photoFile = getPhotoFile(fileName);

            if (photoFile != null) {
                Uri fileProvider = FileProvider.getUriForFile(this, "com.block.cameraapp.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        } else {
            showInstallCameraAppDialog();
        }
    }

    private void showInstallCameraAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("카메라 앱 설치")
                .setMessage("카메라 앱이 설치되어 있지 않습니다. 설치하시겠습니까?")
                .setPositiveButton("설치", (dialogInterface, i) -> {
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=카메라 앱"));
                    startActivity(marketIntent);
                })
                .setNegativeButton("취소", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void album() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_STORAGE_PERMISSION);
                Toast.makeText(this, "앨범 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (!checkPermission()) {
                requestPermission();
                return;
            }
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "이미지 선택"), REQUEST_ALBUM);
        } else {
            Toast.makeText(this, "앨범 앱을 설치해 주세요.", Toast.LENGTH_SHORT).show();
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=갤러리 앱"));
            startActivity(marketIntent);
        }
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    private File getPhotoFile(String fileName) {
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            return File.createTempFile(fileName, ".jpg", storageDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "카메라 권한 허가됨", Toast.LENGTH_SHORT).show();
            camera();
        } else if (requestCode == REQUEST_STORAGE_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "앨범 권한 허가됨", Toast.LENGTH_SHORT).show();
            album();
        } else {
            Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bitmap photo = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            photo = rotateBitmap(photo, getPhotoOrientation(photoFile.getAbsolutePath()));
            imageView.setImageBitmap(photo);

        } else if (requestCode == REQUEST_ALBUM && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri albumUri = data.getData();
            String fileName = getFileName(albumUri);

            try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(albumUri, "r");
                 FileInputStream inputStream = new FileInputStream(pfd.getFileDescriptor());
                 FileOutputStream outputStream = new FileOutputStream(new File(getCacheDir(), fileName))) {

                IOUtils.copy(inputStream, outputStream);
                Bitmap photo = BitmapFactory.decodeFile(new File(getCacheDir(), fileName).getAbsolutePath());
                imageView.setImageBitmap(photo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getPhotoOrientation(String path) {
        try {
            ExifInterface exif = new ExifInterface(path);
            return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            e.printStackTrace();
            return ExifInterface.ORIENTATION_NORMAL;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;

            default:
                return bitmap;
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            cursor.close();
            return fileName;
        }
        return null;
    }
}
