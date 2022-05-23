package com.example.ngantritest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngantritest.Action.PrefManager;
import com.example.ngantritest.Api.BaseApiService;
import com.example.ngantritest.Api.UtilsApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    BaseApiService mApiService;
    PrefManager manager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        Button login =  findViewById(R.id.login);

        manager = new PrefManager(this);
        mApiService = UtilsApi.getApiService();

        if (manager.sessionLogin()==true){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }



        login.setOnClickListener(v -> {
            if (username.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                mApiService.loginRequest(username.getText().toString(), password.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                // jika sukses
                                if (jsonRESULTS.getString("error").equals("false")) {

                                    // parsing data
                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    manager.setSudahLogin(true, nama);

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);

                                } else {

                                    String msg = jsonRESULTS. getString("error_msg");
                                    // Jika login gagal
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

    }

    public static void redirectActivity(Activity activity, Class aClass) {
//        initiatialize intent
        Intent intent = new Intent(activity,aClass);
//        set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        StartActivity
        activity.startActivity(intent);
    }

    public void ClickDaftar(View view){
//        redirec activity to info pelayanan
        redirectActivity(this,Daftar.class);
    }
}