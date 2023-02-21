package com.example.flowers_k;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Choose_action_pet extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_choose_action_pet);
    }
    public void onClickToMain(View view) {
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
    }
    public void onClickToCreatePet(View view) {
        Intent intent = new Intent(this, Create_pet.class);
        startActivity(intent);
    }
    public void onClickToFindPet(View view) {
        Intent intent = new Intent(this, Find_pet.class);
        startActivity(intent);
    }
    public void onClickToEditPet(View view) {
        Intent intent = new Intent(this, Edit_pet.class);
        startActivity(intent);
    }
    public void onClickToDeletePet(View view) {
        Intent intent = new Intent(this, Delete_pet.class);
        startActivity(intent);
    }

}