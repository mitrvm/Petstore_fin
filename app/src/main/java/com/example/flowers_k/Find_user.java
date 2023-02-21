package com.example.flowers_k;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class Find_user extends AppCompatActivity {
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        username = (EditText) findViewById(R.id.Log_Login);
        password = (EditText) findViewById(R.id.Log_Password);
    }
    public void Login(View view) {
        if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(Find_user.this, "Заполните все поля.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Find_user.this, "стать gay", Toast.LENGTH_SHORT).show();

//            UserForLogin user =  new UserForLogin(username.getText().toString(),password.getText().toString());
            LoginFunction(username.getText().toString(), password.getText().toString());
        }
    }

    public void LoginFunction(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<User> call = userAPI.createUser(username, password);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String success = ("boy успешно добавлен.");
                Toast.makeText(Find_user.this, success, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Find_user.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Forgot_password(View view) {
        Toast.makeText(Find_user.this, "Очень жаль.", Toast.LENGTH_SHORT).show();
    }
    public void OnClickToRegistration(View view) {
        Intent intent = new Intent(this, Create_user.class);
        startActivity(intent);
    }
}
