package com.example.ngantritest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngantritest.Api.BaseApiService;
import com.example.ngantritest.Api.UtilsApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daftar extends AppCompatActivity {

    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        final EditText nama = findViewById(R.id.nama);
        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button regbtn =  findViewById(R.id.btn_daftar);

        mApiService = UtilsApi.getApiService();


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    mApiService.registerRequest(nama.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString()).enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                    // jika sukses
                                    if (jsonRESULTS.getString("msg").equals("Berhasil")) {

                                        Toast.makeText(getApplicationContext(), "Register Sukses", Toast.LENGTH_SHORT).show();
                                        Intent a = new Intent(getApplicationContext(), Login.class );
                                        startActivity(a);
                                        finish();


                                    } else {

                                        Toast.makeText(getApplicationContext(), "Username Sudah Terdaftar", Toast.LENGTH_SHORT).show();

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

    public void ClickLogin(View view){
//        redirec activity to info pelayanan
        redirectActivity(this,Login.class);
    }
}