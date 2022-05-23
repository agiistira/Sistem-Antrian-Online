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
import com.example.ngantritest.Adapter.Tempat1Adapter;
import com.example.ngantritest.Api.BaseApiService;
import com.example.ngantritest.Api.UtilsApi;
import com.example.ngantritest.Model.Tempat1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankingActivity extends AppCompatActivity {
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private Tempat1Adapter mAdapter;
    ArrayList<Tempat1> tempatArrayList1 = new ArrayList<>();
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);

        mApiService = UtilsApi.getApiService();
        prefManager=new PrefManager(this);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        mApiService.tampilTempatRequest().enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());

                        JSONArray jsonArray = jsonRESULTS.getJSONArray("list_tempat");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Tempat1 tempat1 = new Tempat1();
                            tempat1.setNama(object.getString("nama"));
                            tempat1.setAlamat(object.getString("alamat"));
                            tempat1.setUsername(object.getString("username"));

                            tempatArrayList1.add(tempat1);
                            prefManager.setBankingUsername(tempat1.getUsername(),i);
                            mAdapter = new Tempat1Adapter(tempatArrayList1);
                            mAdapter.notifyDataSetChanged();

                        }
                        recyclerView.setAdapter(mAdapter);recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(), recyclerView, new TouchListener.OnTouchActionListener() {
                            @Override
                            public void onLeftSwipe(View view, int position) {

                            }

                            @Override
                            public void onRightSwipe(View view, int position) {

                            }

                            @Override
                            public void onClick(View view, int position) {
                                prefManager.setIndexBanking(position);
                                Intent intent = new Intent(getApplicationContext(), TampilPelayanan1.class);
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