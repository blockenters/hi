package com.block.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.block.contactapp.model.Contact;

public class UpdateActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        Contact contact = (Contact) getIntent().getSerializableExtra("contact");
        int index = getIntent().getIntExtra("index", -1);

        editName.setText(contact.name);
        editPhone.setText(contact.phone);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                Contact contact = new Contact(name, phone);
                Intent intent = new Intent();
                intent.putExtra("contact", contact);
                intent.putExtra("index", index);
                setResult(101, intent);
                finish();
            }
        });
    }
}


