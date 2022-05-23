package com.example.ngantritest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ngantritest.Action.PrefManager;
import com.example.ngantritest.Action.TouchListener;
import com.example.ngantritest.Adapter.Pelayan1Adapter;
import com.example.ngantritest.Api.BaseApiService;
import com.example.ngantritest.Api.UtilsApi;
import com.example.ngantritest.Model.Pelayan1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilPelayanan1 extends AppCompatActivity {

    PrefManager prefManager;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private Pelayan1Adapter mAdapter;
    ArrayList<Pelayan1> pelayan1ArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pelayanan1);

        mApiService = UtilsApi.getApiService();
        prefManager=new PrefManager(this);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        mApiService.ambilPelayanRequest1(prefManager.getBankingUsername(prefManager.getIndexBanking())).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());

                        JSONArray jsonArray = jsonRESULTS.getJSONArray(prefManager.getBankingUsername(prefManager.getIndexBanking()));

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



    }
}