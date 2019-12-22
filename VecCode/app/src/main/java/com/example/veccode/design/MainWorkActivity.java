package com.example.veccode.design;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.veccode.Login.LoginActivity;
import com.example.veccode.MainActivity;
import com.example.veccode.R;
import com.example.veccode.utils.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


public class MainWorkActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    private TextView HName ,Hdept,HG,HS,HB;

  private BottomNavigationView bottomNavigationView;
    private String Sreg="",Sname="",Sbatch="",Sdept="",Smedal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_work);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpNavigation();

        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            Intent i = (new Intent(MainWorkActivity.this, LoginActivity.class));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        if(connectedToNetwork()){

            dbFetch();
        }else{ NoInternetAlertDialog(); }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_mcq,
                R.id.nav_bookmark, R.id.nav_share, R.id.nav_send, R.id.nav_codezone)
                .setDrawerLayout(drawer)
                .build();

        View header = navigationView.getHeaderView(0);
        HName=header.findViewById(R.id.hname);
        Hdept=header.findViewById(R.id.hdept);
        HG=header.findViewById(R.id.gold);
        HS=header.findViewById(R.id.silver);
        HB=header.findViewById(R.id.bronze);



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.nav_profile)

                {
               //     FirebaseAuth.getInstance().signOut();
                    Toast.makeText(MainWorkActivity.this, "hchs", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void setUpNavigation(){
       bottomNavigationView =findViewById(R.id.bttm_nav);
        final NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {


        if(     destination.getId()==R.id.nav_profile)
        {
            bottomNavigationView.setVisibility(View.GONE);

        } else
        if(     destination.getId()==R.id.anagramFragment)
        {
            bottomNavigationView.setVisibility(View.GONE);
            MainWorkActivity.this.getSupportActionBar().hide();

        }  else   if( destination.getId()==R.id.webFragment)
        {
            bottomNavigationView.setVisibility(View.GONE);
        }else
            if( destination.getId()==R.id.nav_mcq)
        {
            bottomNavigationView.setVisibility(View.GONE);
        }
            else  if( destination.getId()==R.id.mainMcqFragment)
            {
                bottomNavigationView.setVisibility(View.GONE);
            }
            else if(destination.getId()==R.id.nav_bookmark)
            {

                bottomNavigationView.setVisibility(View.GONE);

            }
            else if(destination.getId()==R.id.nav_send)
            {

                bottomNavigationView.setVisibility(View.GONE);

            }
            else if(destination.getId()==R.id.nav_share)
            {

                bottomNavigationView.setVisibility(View.GONE);

            }
            else
            {
                MainWorkActivity.this.getSupportActionBar().show();
                bottomNavigationView.setVisibility(View.VISIBLE);
            }




    }
});
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                Intent i = (new Intent(MainWorkActivity.this, LoginActivity.class));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);

                FirebaseAuth.getInstance().signOut();
                SharedPreferences sp =getSharedPreferences("com.veccode.PRIVATEDATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();

                editor.apply();

                startActivity(i);


        }
        return super.onOptionsItemSelected(item);
    }

    private void dbFetch() {

        Sreg=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("student","");
        Sreg=Sreg.replace("@vecode.com","");

            System.out.println(Sreg);
     final String year="20"+Sreg.substring(4,6);
        FirebaseDatabase.getInstance().getReference().child("Profile").child(year).child(Sreg).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                profile p=dataSnapshot.getValue(profile.class);
if(p!=null) {
    Sname = p.getName();
    Sbatch = p.getBatch();
    Sdept = p.getDept();
    Smedal = p.getMedal();
    Toast.makeText(MainWorkActivity.this, Smedal, Toast.LENGTH_SHORT).show();
    setdata();
}    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void setdata() {

        //nav header
        HName.setText(Sname);
        Hdept.setText(Sdept+"  "+Sbatch);

        String medal[]=Smedal.split("_");
        HG.setText(medal[0]);
        HS.setText(medal[1]);
        HB.setText(medal[2]);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_work, menu);


        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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

dbFetch();
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
