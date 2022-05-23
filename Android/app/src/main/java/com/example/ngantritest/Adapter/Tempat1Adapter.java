package com.example.ngantritest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ngantritest.Model.Tempat1;
import com.example.ngantritest.R;

import java.util.List;

public class Tempat1Adapter extends RecyclerView.Adapter<Tempat1Adapter.MyViewHolder>  {
    private List<Tempat1> tempatList1;

    public Tempat1Adapter(List<Tempat1> tempatlList1) {
        this.tempatList1 = tempatlList1;
    }

    @Override
    public Tempat1Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempat1_recycle_view, parent, false);
        return new Tempat1Adapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull Tempat1Adapter.MyViewHolder holder, int position) {
        Tempat1 tempat1 = tempatList1.get(position);
        holder.nama.setText(tempat1.getNama());
        holder.alamat.setText(tempat1.getAlamat());
    }

    @Override
    public int getItemCount() {
        return tempatList1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, alamat;

        public MyViewHolder(View view) {
            super(view);
            nama =  view.findViewById(R.id.nama);
            alamat =  view.findViewById(R.id.alamat);
        }
    }
}
