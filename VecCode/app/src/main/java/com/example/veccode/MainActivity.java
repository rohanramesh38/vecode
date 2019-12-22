package com.example.veccode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.example.veccode.Login.LoginActivity;
import com.example.veccode.design.MainWorkActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        //CodeProcessor.init(this);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (connectedToNetwork()) {

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    if(currentUser!=null)
                    {
                        Intent i = (new Intent(MainActivity.this, MainWorkActivity.class));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }else{
                        Intent i = (new Intent(MainActivity.this, LoginActivity.class));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }



                } else {
                    NoInternetAlertDialog();
                }
            }
        }, SPLASH_TIME_OUT);

    }




    @SuppressWarnings("MissingPermission")
    public boolean connectedToNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }

        return false;

    }
    public void NoInternetAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You are not connected to the internet. ");
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(connectedToNetwork()){

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    if(currentUser!=null)
                    {
                        Intent i = (new Intent(MainActivity.this, MainWorkActivity.class));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }else{
                        Intent i = (new Intent(MainActivity.this, LoginActivity.class));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }


                }else{ NoInternetAlertDialog(); }
            }
        });
        builder.setNegativeButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent openSettings = new Intent();
                openSettings.setAction(Settings.ACTION_WIRELESS_SETTINGS);
                openSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openSettings);
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }



}
