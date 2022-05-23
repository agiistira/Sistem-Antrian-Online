package com.example.ngantritest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Lokasi extends AppCompatActivity {

    DrawerLayout drawerLayout;
    EditText etSource;
    TextView etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        drawerLayout = findViewById(R.id.drawer_layout);
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack= findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Ambil data text
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

//                Cek kondisi
                if (sSource.equals("")){
                    Toast.makeText(getApplicationContext()
                    ,"Masukkan Lokasi Anda Sekarang !", Toast.LENGTH_SHORT).show();
                }else {
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
//        kalo belum ada google map maka ke playstore
        try {
//            kalo ada google map
//            dijalankan
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
//            Intent dengan action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
//            jika tidak ada google map maka
            Uri uri = Uri.parse("https://play.google.com/store/apss/details?id=com.google.android.apps.maps");
//            jalankan dengan action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void ClickMenu(View view){
//        Open Drawer
        MainActivity.openDrawer(drawerLayout);
    }


    public void ClickLogo (View view){
//        close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
//        redirect activity to home
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickInfo(View view){
        MainActivity.redirectActivity(this,InfoPelayanan.class);
    }

    public void ClickLokasi(View view){
//   //        recrecreate acitivty
        recreate();
    }

    public void ClickLogout(View view){
//        close app
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        close drawer
        MainActivity.closeDrawer((drawerLayout));
    }
}