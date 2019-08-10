package com.example.codeplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.codeplus.login.GoogleLoginActivity;
import com.example.codeplus.ui.WorkActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    ImageView imv;
    Animation fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv=findViewById(R.id.spk);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromtop.setDuration(1500);
        imv.setAnimation(fromtop);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GoogleSignInAccount alr= GoogleSignIn.getLastSignedInAccount(MainActivity.this);
                SharedPreferences sp = getApplicationContext().getSharedPreferences("com.doitApp.PRIVATEDATA", Context.MODE_PRIVATE);



                  String   Name=sp.getString("name",  "oo");

                if(Name.equals("h"))
                {
                    finish();
                    startActivity(new Intent(MainActivity.this, WorkActivity.class));

                    System.out.println(alr);

                }
                else
                {
                    finish();
                    startActivity(new Intent(MainActivity.this, GoogleLoginActivity.class));

                    //  finish();
                    //    startActivity(new Intent(GoogleLoginActivity.this, GoogleLoginActivity.class));

                }


            }
        }, SPLASH_TIME_OUT);
    }

}




