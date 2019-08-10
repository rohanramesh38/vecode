package com.example.codeplus.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeplus.R;
import com.example.codeplus.ui.WorkActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class GoogleLoginActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
public static final int RC_SIGN_IN=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);



        TextView t=findViewById(R.id.textView6);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

           mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoogleLoginActivity.this, WorkActivity.class));

            }
        });




        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.doitApp.PRIVATEDATA", Context.MODE_PRIVATE);



        String   Name=sp.getString("name",  "oo");

        if(Name.equals("h"))
        {
            finish();
            startActivity(new Intent(GoogleLoginActivity.this, WorkActivity.class));


        }
        else
        {

            //  finish();
            //    startActivity(new Intent(GoogleLoginActivity.this, GoogleLoginActivity.class));

        }


    }


    @Override
    protected void onStart() {
        super.onStart();




    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            SharedPreferences sp = getApplicationContext().getSharedPreferences("com.doitApp.PRIVATEDATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", "h");


            startActivity(new Intent(GoogleLoginActivity.this, WorkActivity.class));
            finish();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);


        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            GoogleSignInAccount alr=GoogleSignIn.getLastSignedInAccount(this);
            SharedPreferences sp = getApplicationContext().getSharedPreferences("com.doitApp.PRIVATEDATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", "h");


                startActivity(new Intent(GoogleLoginActivity.this, WorkActivity.class));
                finish();

            // Signed in successfully, show authenticated UI.
            //updateUI(account);


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
           // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
         //   updateUI(null);
        }
    }



}
