package com.example.veccode.Login;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.veccode.R;
import com.example.veccode.design.MainWorkActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {



    LinearLayout linlog;
    Spinner spinner;
    Button Register;
    EditText Ename,EReg,Email,Epass;
    String Sreg="",Sname="",Sdob="",Smail="",Spass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner=findViewById(R.id.sectionid);
        Ename=findViewById(R.id.nameET);
        EReg=findViewById(R.id.regNumEt);
        Epass=findViewById(R.id.newpass);
        linlog=findViewById(R.id.linlog);
        Email=findViewById(R.id.emailET);
        Register=findViewById(R.id.RegisterBT);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(getData())
                {

                }

            }
        });



        final String Sections[]={"-","A","B","C"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(SignupActivity.this,R.layout.support_simple_spinner_dropdown_item,Sections);
        spinner.setAdapter(arrayAdapter);


        Signup();


    }

    private boolean getData() {

    return true;
    }

    private void Signup() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword("student" + Sreg + "@vecode.com", "" + Sdob).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("Name", Sname);
                    hashMap.put("Dob", Sdob);
                    hashMap.put("Reg", Sreg);
                    hashMap.put("Email", Smail);
                    hashMap.put("Image","");
                    hashMap.put("Lang","");

                    hashMap.put("Batch", "20" + Sreg.substring(4, 6) + "-" + "20" + (Integer.parseInt(Sreg.substring(4, 6)) + 4));
                    String s = Sreg.substring(6, 9), Dept = "";

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
                        Dept="Unknown";
                    }
                    hashMap.put("Dept", Dept);

                    hashMap.put("Medal", "0_0_0");
                    hashMap.put("Book", "ALLC-NO<br>ALLCPP-NO<br>ALLJAVA-NO");
                    hashMap.put("Piechart", "C-1_Python-1_C++-1_Java-1");
                    hashMap.put("Section", Sdob);
                    FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + Sreg.substring(4, 6)).child(Sreg).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {
                                Intent i = (new Intent(SignupActivity.this, MainWorkActivity.class));
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });





                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Signup failed.", Toast.LENGTH_SHORT).show();

                }                                Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();


            }
        });
    }



}
