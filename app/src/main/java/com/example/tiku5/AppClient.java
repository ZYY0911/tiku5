package com.example.tiku5;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiku5.bean.Ssjt;
import com.example.tiku5.bean.SSHJ;
import com.example.tiku5.bean.Wdyj;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/1 0001.
 */

public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;
    private List<SSHJ> sshjs = new ArrayList<>();

    public List<Wdyj> getMwdyj() {
        return mwdyj;
    }

    private List<Wdyj> mwdyj = new ArrayList<>();
    public List<Ssjt> getMssjt() {
        return mssjt;
    }

    private List<Ssjt> mssjt = new ArrayList<>();
    public List<SSHJ> getSshjs() {
        return sshjs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

        requestQueue= Volley.newRequestQueue(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }

    public static void setUserName(String userName){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName",userName);
        editor.commit();
    }

    public static String getUserName()
    {
        return preferences.getString("UserName","user1");
    }
    public static void setIp(String ip){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("ip",ip);
        editor.commit();
    }

    public static String getDk(){
        return preferences.getString("Dk","8080");
    }

    public static void setDk(String Dk){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Dk",Dk);
        editor.commit();
    }

    public static String getIp(){
        return preferences.getString("ip","10.172.176.53");
    }



    public static void setYz(String Yz){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Yz",Yz);
        editor.commit();
    }

    public static String getYz(){
        return preferences.getString("Yz","");
    }

    public static void setSex(String Sex){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Sex",Sex);
        editor.commit();
    }

    public static String getSex(){
        return preferences.getString("Sex","");
    }

    public static void setTel(String Tel){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Tel",Tel);
        editor.commit();
    }

    public static String getTel(){
        return preferences.getString("Tel","");
    }


    public static void setName(String Name){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Name",Name);
        editor.commit();
    }

    public static String getName(){
        return preferences.getString("Name","");
    }


    public static void setPassWord(String PassWord){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("PassWord",PassWord);
        editor.commit();
    }

    public static String getPassWord(){
        return preferences.getString("PassWord","");
    }


    public boolean login()
    {
        return preferences.getBoolean("login",false);
    }
    public void setlogin(boolean login)
    {
        preferences.edit().putBoolean("login",login).apply();
    }

    public boolean getXz()
    {
        return preferences.getBoolean("Xz",false);
    }
    public void setXz(boolean Xz)
    {
        preferences.edit().putBoolean("Xz",Xz).apply();
    }

    public static void setUserName1(String UserName1){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName1",UserName1);
        editor.commit();
    }

    public static String getUserName1(){
        return preferences.getString("UserName1","");
    }


}
