package com.example.flowers_k;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create_pet extends AppCompatActivity {

    private ProgressBar pBar;
    private TextView errors;
    private EditText editPetName;
    private EditText editPetID;
    private EditText editPetCategoryID;
    private EditText editPetTagID;
    private EditText editPetCategory;
    private EditText editPetTag;
    private EditText editPetStatus;
    private EditText editPetPhotoURLS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_create_pet);

        errors = (TextView) findViewById(R.id.errors2);
        pBar = (ProgressBar) findViewById(R.id.progressBar4);
        editPetName = (EditText) findViewById(R.id.petId);
        editPetID = (EditText) findViewById(R.id.editTextTextPersonName1);
        editPetCategoryID = (EditText) findViewById(R.id.editTextTextPersonName3);
        editPetTagID = (EditText) findViewById(R.id.editTextTextPersonName6);
        editPetCategory = (EditText) findViewById(R.id.editTextTextPersonName4);
        editPetTag = (EditText) findViewById(R.id.editTextTextPersonName7);
        editPetStatus = (EditText) findViewById(R.id.editTextTextPersonName8);
        editPetPhotoURLS = (EditText) findViewById(R.id.editTextTextPersonName5);

        pBar.setVisibility(View.INVISIBLE);
    }

    public void OnClickCreate(View view) {
        if (TextUtils.isEmpty(editPetName.getText().toString()) ||
                TextUtils.isEmpty(editPetID.getText().toString()) ||
                TextUtils.isEmpty(editPetCategoryID.getText().toString()) ||
                TextUtils.isEmpty(editPetTagID.getText().toString()) ||
                TextUtils.isEmpty(editPetCategory.getText().toString()) ||
                TextUtils.isEmpty(editPetTag.getText().toString()) ||
                TextUtils.isEmpty(editPetPhotoURLS.getText().toString()) ||
                TextUtils.isEmpty(editPetStatus.getText().toString())) {
            Toast.makeText(Create_pet.this, "Заполните все поля.", Toast.LENGTH_SHORT).show();
        } else {
            Category category = new Category((Integer.parseInt(editPetCategoryID.getText().toString())), (editPetCategory.getText().toString()));
            ArrayList<String> photoUrls = new ArrayList<String>();
            photoUrls.add(editPetPhotoURLS.getText().toString());

            ArrayList<Tag> tags = new ArrayList<Tag>();
            Tag tag = new Tag((Integer.parseInt(editPetTagID.getText().toString())), (editPetTag.getText().toString()));
            tags.add(tag);

            Pet pet = new Pet((Integer.parseInt(editPetID.getText().toString())), category, (editPetName.getText().toString()), photoUrls, tags, (editPetStatus.getText().toString()));

            createPet(pet);
        }
    }

    private void createPet(Pet pet) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PetsAPI petAPI = retrofit.create(PetsAPI.class);

        pBar.setVisibility(View.VISIBLE);

        Call<Pet> call = petAPI.createPet(pet);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                String success = ("Питомец "+pet+" успешно добавлен.");
                Toast.makeText(Create_pet.this, success, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(Create_pet.this, t.toString(), Toast.LENGTH_SHORT).show();
                errors.setText(t.getMessage());
                pBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void onClickBackToPet2(View view) {
        Intent intent = new Intent(this, Choose_action_pet.class);
        startActivity(intent);
    }
}
