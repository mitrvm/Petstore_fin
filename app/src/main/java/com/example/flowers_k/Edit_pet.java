package com.example.flowers_k;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit_pet extends AppCompatActivity {

    private EditText editPetName;
    private EditText editPetID;
    private EditText editPetCategoryID;
    private EditText editPetTagID;
    private EditText editPetCategory;
    private EditText editPetTag;
    private EditText editPetStatus;
    private EditText editPetPhotoURLS;
    int status = 0; // 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_pet);

        editPetID = (EditText) findViewById(R.id.editTextTextPersonName);
        editPetName = (EditText) findViewById(R.id.petId);
        editPetCategoryID = (EditText) findViewById(R.id.editTextTextPersonName3);
        editPetCategory = (EditText) findViewById(R.id.editTextTextPersonName4);
        editPetTagID = (EditText) findViewById(R.id.editTextTextPersonName6);
        editPetTag = (EditText) findViewById(R.id.editTextTextPersonName7);
        editPetStatus = (EditText) findViewById(R.id.editTextTextPersonName8);
        editPetPhotoURLS = (EditText) findViewById(R.id.editTextTextPersonName5);
    }

    public void OnClickFind(View view){

        if (status == 0) {

            if (TextUtils.isEmpty(editPetID.getText().toString())){
                Toast.makeText(Edit_pet.this, "Заполните все поля.", Toast.LENGTH_SHORT).show();
            }
            else {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://petstore.swagger.io/v2/")
                        // as we are sending data in json format so
                        // we have to add Gson converter factory
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();

                PetsAPI petAPI = retrofit.create(PetsAPI.class);

                final Call<Pet> call = petAPI.getData(editPetID.getText().toString());

                call.enqueue(new Callback<Pet>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Pet> call, Response<Pet> response) {
                        if (response.isSuccessful()) {
                            Pet result = response.body();


                            editPetName.setText(result.getName());
                            editPetCategoryID.setText(result.getCategory().getId());
                            editPetCategory.setText(result.getCategory().getName());
                            editPetTagID.setText("0");
                            editPetTag.setText("string");
                            editPetStatus.setText(result.getStatus());
                            editPetPhotoURLS.setText("string");

                            Toast.makeText(Edit_pet.this, "Pet готов к изменению",
                                    Toast.LENGTH_SHORT).show();

                            status = 1;

                        } else {

                            ResponseBody errorBody = response.errorBody();
                            try {
                                Toast.makeText(Edit_pet.this, errorBody.string(),
                                        Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Pet> call, Throwable t) {
                    }
                });

            }
        } else {


        }


    }
    public void onClickBackToPet3(View view) {
        Intent intent = new Intent(this, Choose_action_pet.class);
        startActivity(intent);
    }
}
