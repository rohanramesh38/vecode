package com.example.veccode.Login;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veccode.MainActivity;
import com.example.veccode.R;
import com.example.veccode.design.MainWorkActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText Ereg;
    private LinearLayout lin;
    private String Sreg="",Sdob="",name="Anonymous";
    private Button b;
    private FirebaseAuth mAuth;
    private LinearLayout l;
    private TextView ne,si,cre,Edob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI(findViewById(R.id.parent1));
        findviews();
        lin=findViewById(R.id.lin);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

            }
        });

        Edob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog  datePickerDialog = new DatePickerDialog(LoginActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Edob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {



              //  ObjectAnimator.ofFloat(lin, "rotation", 0, -40).setDuration(500).start();
                ObjectAnimator.ofFloat(lin, "rotation", 0, 360).setDuration(900).start();


                if(si.getText().equals("Sign-up "))
                {

                    ne.setText("Existing user? ");
                    si.setText("Sign-in ");
                    cre.setText("");
                    b.setText("SIGN UP");
              //      b.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation) );
//cancelJob();
                   startService();
                }
                else
                {
                   // scheduleJob();
                   stopService();
                    ne.setText("New? ");
                    si.setText("Sign-up ");
                    cre.setText("for a new account.");
                    b.setText("SIGN IN");
                 //   b.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation) );


                }


            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser!=null)
        {
            Intent i = (new Intent(LoginActivity.this, MainWorkActivity.class));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    private void getData() {

        Sreg=Ereg.getText().toString().trim();
        Sdob=Edob.getText().toString().trim();

        if(TextUtils.isEmpty(Sreg) || Sreg.length()!=12 || !Sreg.contains("1132")){
            Ereg.setError("Enter Valid Reg number");


        }else if(TextUtils.isEmpty(Sdob) ){
Edob.setError("Enter Dob");
        }
        else
        {

            login();
        }


    }

    private void login() {
        if(si.getText().equals("Sign-up ")){

            FirebaseAuth.getInstance().signInWithEmailAndPassword("student"+Sreg+"@vecode.com", ""+Sdob)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent i = (new Intent(LoginActivity.this, MainWorkActivity.class));
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                             //   FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        else {

                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword("student" + Sreg + "@vecode.com", "" + Sdob)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("Name", name);
                                    hashMap.put("Dob", Sdob);
                                    hashMap.put("Reg", Sreg);

                                    hashMap.put("Image","");
                                    hashMap.put("Lang","");

                                    hashMap.put("Batch", "20" + Sreg.substring(4, 6) + "-" + "20" + (Integer.parseInt(Sreg.substring(4, 6)) + 4));
                                    String s = Sreg.substring(6, 9), Dept = "";
                                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();


                                    if (s.equals("114"))
                                        Dept = "Mech";
                                    else
                                    if (s.equals("104"))
                                        Dept = "CSE";
                                    else
                                    if (s.equals("205"))
                                        Dept = "IT";
                                    else
                                    if (s.equals("105"))
                                        Dept = "EEE";
                                    else
                                    if (s.equals("107"))
                                        Dept = "E&I";
                                    else
                                    if (s.equals("106"))
                                        Dept = "ECE";
                                    else
                                    if (s.equals("103"))
                                        Dept = "Civil";
                                    else
                                    if (s.equals("102"))
                                        Dept = "Auto";
                                    else
                                    {
                                        Dept="";
                                    }
                                    hashMap.put("Dept", Dept);

                                    hashMap.put("Medal", "0_0_0");
                                    hashMap.put("Book", "ALLC-NO<br>ALLCPP-NO<br>ALLJAVA-NO");
                                    hashMap.put("Piechart", "C-1_Python-1_C++-1_Java-1");


                                    hashMap.put("Section", "");
                                    if(!TextUtils.isEmpty(Dept)) {
                                        FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + Sreg.substring(4, 6)).child(Sreg).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {


                                                if (task.isSuccessful()) {
                                                    FirebaseDatabase.getInstance().getReference().child("ScoreBoardVec").child("20" + Sreg.substring(4, 6)).child(Sreg).setValue("0_0_0");


                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                                    alertDialog.setTitle("Enter Your Name");

                                                    final EditText input = new EditText(LoginActivity.this);
                                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                            LinearLayout.LayoutParams.MATCH_PARENT,
                                                            LinearLayout.LayoutParams.MATCH_PARENT);
                                                    lp.setMargins(20, 10, 20, 10);
                                                    input.setLayoutParams(lp);

                                                    alertDialog.setView(input);

                                                    alertDialog.setPositiveButton("Done",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    name = input.getText().toString();
                                                                    if (!TextUtils.isEmpty(name))
                                                                        FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + Sreg.substring(4, 6)).child(Sreg).child("Name").setValue(name);
                                                                    else
                                                                        FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + Sreg.substring(4, 6)).child(Sreg).child("Name").setValue("Anonymous");
                                                                    dialog.cancel();
                                                                    Intent i = (new Intent(LoginActivity.this, MainWorkActivity.class));
                                                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                    startActivity(i);

                                                                }
                                                            });

                                                    Dialog dialog = alertDialog.create();
                                                    dialog.setCanceledOnTouchOutside(false);
                                                    dialog.show();


                                                }

                                            }
                                        });


                                        // Sign in success, update UI with the signed-in user's information
                                        //       Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, "Enter a valid Reg number",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }

        }




    private void findviews() {

        Ereg=findViewById(R.id.editTextReg);
        Edob=findViewById(R.id.editTextDob);
        b=findViewById(R.id.button2);
        l=findViewById(R.id.linsig);
        ne=findViewById(R.id.ne);
        si=findViewById(R.id.si);
        cre=findViewById(R.id.cre);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d("bla", "Job scheduled");
        } else {
            Log.d("bla", "Job scheduling failed");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d("bla", "Job cancelled");
    }



    public void startService() {

        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", "hello");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}
