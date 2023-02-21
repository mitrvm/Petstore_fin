package com.example.flowers_k;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        final MediaPlayer cse = MediaPlayer.create(this, R.raw.tootoo);
        Button playsound = (Button) this.findViewById(R.id.btn_sound);

        playsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cse.start();
                Toast.makeText(StartScreen.this, "*Клоунский гудок*", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickPet(View view) {
        Intent intent = new Intent(this, Choose_action_pet.class);
        startActivity(intent);
    }

    public void onClickStore(View view) {
        Intent intent = new Intent(this, Choose_action_store.class);
        startActivity(intent);
    }
    public void onClickUser(View view) {
        Intent intent = new Intent(this, Find_user.class);
        startActivity(intent);
    }
}