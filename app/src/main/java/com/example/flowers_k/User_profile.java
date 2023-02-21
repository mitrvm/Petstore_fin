package com.example.flowers_k;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_profile);

    }
    public void OnClickReturnToStart(View view) {
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
    }
    public void OnClickLogout(View view) {
        // выход
    }
}
