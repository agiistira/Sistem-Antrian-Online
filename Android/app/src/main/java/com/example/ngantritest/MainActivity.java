package com.example.ngantritest;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngantritest.Action.PrefManager;
import com.example.ngantritest.Action.TouchListener;
import com.example.ngantritest.Adapter.Pelayan1Adapter;
import com.example.ngantritest.Adapter.Tempat1Adapter;
import com.example.ngantritest.Api.BaseApiService;
import com.example.ngantritest.Api.UtilsApi;
import com.example.ngantritest.Model.Pelayan1;
import com.example.ngantritest.Model.Tempat1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private TextView textName;
    PrefManager prefManager;
    BaseApiService mApiService;
    private RecyclerView recyclerView;

    private Pelayan1Adapter mAdapter;

    ArrayList<Pelayan1> pelayan1ArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = UtilsApi.getApiService();
        prefManager = new PrefManager(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        textName = findViewById(R.id.nama);
        TextView logout = findViewById(R.id.logout);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        mApiService.ambilPelayanRequest1(prefManager.getBankingUsername(prefManager.getIndexBanking())).enqueue(new Callback<ResponseBody>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());


                        JSONArray jsonArray = jsonRESULTS.getJSONArray("bankbri");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Pelayan1 pelayan1 = new Pelayan1();
                            pelayan1.setTypeLoket(object.getString("type_loket"));
                            pelayan1.setTanggal(object.getString("tanggal"));
                            pelayan1.setBuka(object.getString("buka"));
                            pelayan1.setTutup(object.getString("tutup"));


                            pelayan1ArrayList.add(pelayan1);
                            prefManager.setTypeLoket(pelayan1.getTypeLoket(),i);
                            mAdapter = new Pelayan1Adapter(pelayan1ArrayList);
                            mAdapter.notifyDataSetChanged();

                        }
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.OnTouchActionListener() {
                            @Override
                            public void onLeftSwipe(View view, int position) {

                            }

                            @Override
                            public void onRightSwipe(View view, int position) {

                            }

                            @Override
                            public void onClick(View view, int position) {
                                prefManager.setIndexTypeLoket(position);
                                Intent intent = new Intent(getApplicationContext(), DetailPelayan1.class);
                                startActivity(intent);
                            }
                        }));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }


        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setSudahLogin(false);
                prefManager.clear();
                Intent a = new Intent(getApplicationContext(),Login.class );
                startActivity(a);
                finish();
            }
        });

    }

    public void ClickMenu(View view){
//        Open Drawer
        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
//        Open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
//        Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
//        close drawer layout
//        check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            when drawer is open
//            close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
//        Recreate activity
        recreate();
    }

    public void ClickInfo(View view){
//        redirec activity to info pelayanan
        redirectActivity(this,InfoPelayanan.class);
    }

    public void ClickLokasi(View view){
//        redirect activity to lokasi
        redirectActivity(this,Lokasi.class);
    }




    public static void redirectActivity(Activity activity, Class aClass) {
//        initiatialize intent
        Intent intent = new Intent(activity,aClass);
//        set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        StartActivity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        close drawer
        closeDrawer(drawerLayout);
    }

}