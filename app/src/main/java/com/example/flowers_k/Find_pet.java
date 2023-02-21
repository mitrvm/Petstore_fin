package com.example.flowers_k;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Find_pet extends AppCompatActivity {

    private TextView errors;
    private ProgressBar pBar;
    private ImageView petImage;

    private TextView petName;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_find_pet);

        errors = (TextView) findViewById(R.id.errors);
        pBar = (ProgressBar) findViewById(R.id.progressBar2);
        petImage = (ImageView) findViewById(R.id.petImage2);
        petName = (TextView) findViewById(R.id.petName2);
        edit = (EditText) findViewById(R.id.editTextTextPersonName);
        pBar.setVisibility(View.INVISIBLE);
        petName.setVisibility(View.INVISIBLE);
        errors.setVisibility(View.INVISIBLE);
    }

    public void OnClickFind(View view){

        String PetIDInput = edit.getText().toString();
        if (TextUtils.isEmpty(edit.getText().toString())){
            Toast.makeText(Find_pet.this, "Заполните все поля.", Toast.LENGTH_SHORT).show();
        }
        else {
            petName.setVisibility(View.VISIBLE);
            pBar.setVisibility(View.VISIBLE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://petstore.swagger.io/v2/")
                    // as we are sending data in json format so
                    // we have to add Gson converter factory
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();

            PetsAPI petAPI = retrofit.create(PetsAPI.class);

            final Call<Pet> call = petAPI.getData(PetIDInput);

            call.enqueue(new Callback<Pet>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<Pet> call, Response<Pet> response) {
                    if (response.isSuccessful()) {
                        Pet result = response.body();

                        String tags = "";
                        for (Tag tag : result.getTags()) {
                            tags += " " + tag.getId() + ". " + tag.getName() + "\n";
                        }


                        petName.setText(" Id: " + result.getId().toString() +
                                "\n Name: " + result.getName() +
                                "\n Category: " + result.getCategory().getName() +
                                "\n Category ID: " + result.getCategory().getId() +
                                "\n Status: " + result.getStatus() +
                                "\n Tags:" +
                                "\n" + tags);

                        Picasso.get().load(result.getPhotoUrls().get(0)).into(petImage);
                        pBar.setVisibility(View.INVISIBLE);
                    } else {

                        ResponseBody errorBody = response.errorBody();
                        try {
                            Toast.makeText(Find_pet.this, errorBody.string(),
                                    Toast.LENGTH_SHORT).show();
                            pBar.setVisibility(View.INVISIBLE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Pet> call, Throwable t) {
                    errors.setText("Ошибка.");
                    pBar.setVisibility(View.INVISIBLE);
                }
            });

        }
        }
    public void onClickBackToPet(View view) {
        Intent intent = new Intent(this, Choose_action_pet.class);
        startActivity(intent);
    }

}

