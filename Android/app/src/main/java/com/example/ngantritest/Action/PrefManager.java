package com.example.ngantritest.Action;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    public static final String PREF_NAME = "PREF_PARTIME";
    private static final String CEK_SUDAH_LOGIN = "sessionLogin";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    // context
    public PrefManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    // session login
    public boolean sessionLogin() {
        return pref.getBoolean(CEK_SUDAH_LOGIN, false);
    }

    public void setSudahLogin(boolean sudahLogin, String data) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);

        //Data User Lokal
        editor.putString("namauser",data);

        editor.commit();
    }

    // ini method khusus pemanggilan button logout
    // karena logout tidak memerlukan data user;
    public void setSudahLogin(boolean sudahLogin) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);
        editor.commit();
    }
    public String getNamaUser() {
        return pref.getString("namauser", "");
    }

    // -------------------------------------------------------- //


    //------banking

    public void setBankingUsername(String username, int index){
        editor.putString("username"+index,username);

        editor.commit();
    }
    public String getBankingUsername(int index) {
        return pref.getString("username"+index, "bankbri");
    }

    public void setIndexBanking(int index){
        editor.putInt("uindex",index);
        editor.commit();
    }
    public Integer getIndexBanking() {
        return pref.getInt("uindex", 0);
    }



    public void setTypeLoket(String type_loket, int index){
        editor.putString("type_loket"+index,type_loket);

        editor.commit();
    }
    public String getTypeLoket(int index) {
        return pref.getString("type_loket"+index, "");
    }



    public void setIndexTypeLoket(int index){
        editor.putInt("type_loketindex",index);

        editor.commit();
    }
    public Integer getIndexTypeLoket() {
        return pref.getInt("type_loketindex", '0');
    }




    public void clear() {
        editor.clear();
        editor.commit();
    }


}
