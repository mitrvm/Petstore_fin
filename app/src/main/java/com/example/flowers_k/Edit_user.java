package com.example.flowers_k;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Edit_user extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_delete_pet);
    }
    public void onClickBackToMain(View view) {
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
    }
}