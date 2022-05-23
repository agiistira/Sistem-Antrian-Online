package com.example.ngantritest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Toolbar extends AppCompatActivity {

    private TextView textName;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        textName = findViewById(R.id.nama);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null) {
            textName.setText(firebaseUser.getDisplayName());
        }else{
            textName.setText("Login Gagal!");
        }
    }
}