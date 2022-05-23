package com.example.ngantritest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class InfoPelayanan extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private TextView cs;
    private TextView teller;
    Button bukarekening, atm, deposito,mbanking,pengaduan,cetakrek,transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pelayanan);
        bukarekening = findViewById(R.id.buka_rekening);
        atm = findViewById(R.id.atm);
        deposito= findViewById(R.id.info_deposito);
        mbanking=findViewById(R.id.info_mbanking);
        pengaduan=findViewById(R.id.info_pengaduan);
        cetakrek=findViewById(R.id.info_cetakrek);
        transaksi=findViewById(R.id.info_transaksi);

        bukarekening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2();
            }
        });

        deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog3();
            }
        });

        mbanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog4();
            }
        });

        pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog5();
            }
        });

        cetakrek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog6();
            }
        });

        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog7();
            }
        });
//        cs = findViewById(R.id.cs);
//        teller =findViewById(R.id.teller);
//
//        cs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(getApplicationContext(),Detail_Customer_Service.class);
//                startActivity(a);
//                finish();
//            }
//        });
//
//        teller.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(getApplicationContext(),Detail_Teller.class);
//                startActivity(a);
//                finish();
//            }
//        });

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void showDialog7() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_transaksi);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialog6() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_cetakrek);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialog5() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_pengaduannasabah);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialog4() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_mbanking);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showdialog3() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_bukadeposit);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialog2() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_atm);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
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
//        recrecreate acitivty
        recreate();
    }

    public void ClickLokasi(View view){
//        redirect activity to about us
        MainActivity.redirectActivity(this,Lokasi.class);
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