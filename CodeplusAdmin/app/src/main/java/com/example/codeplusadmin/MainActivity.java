package com.example.codeplusadmin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    EditText tdata,tc1,tc2,tc3,tc4,tans,texpl;
    Button bs;
    TextView tv;
    String[] country = { "ALLC", "ALLCPP"};

    String slink="",sdata="",sc1="",sc2="",sc3="",sc4="",sans="",fsend="",fans="",sexpl="";
    int sl=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
spin.setSelection(0);

        tv=findViewById(R.id.textView2);


        tdata=findViewById(R.id.editText);
        tc1=findViewById(R.id.editText2);
        tc2=findViewById(R.id.editText3);
        tc3=findViewById(R.id.editText4);
        tc4=findViewById(R.id.editText5);
        tans=findViewById(R.id.editText7);
        texpl=findViewById(R.id.editText8);



bs=findViewById(R.id.button);

bs.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        getdata();
        final int[] count = {0};
if(notempyty())
        {
            DatabaseReference databaseReferenceCount= FirebaseDatabase.getInstance().getReference().child("Count").child(slink);
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(slink);
            databaseReferenceCount.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String S=dataSnapshot.getValue().toString().trim();


                    sl=Integer.parseInt(S);
                    sl++;
                System.out.println(sl+" count");
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReferenceCount.setValue(""+sl);



            String s[]=sdata.split("\n");

            System.out.println(slink);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                System.out.println(String.join("____",s));

            }

            System.out.println(sc1);
            System.out.println(sc2);
            System.out.println(sc3);
            System.out.println(sc4);
            System.out.println(sans);
            String  set=String.join("<br>",s);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv.setText(Html.fromHtml(set, Html.FROM_HTML_MODE_COMPACT));
            } else {
                tv.setText(Html.fromHtml(set));
            }

String allc="";
            allc=sc1+"___"+sc2+"___"+sc3+"___"+sc4;
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("choice",allc);
            hashMap.put("expl",sexpl);
            hashMap.put("ques",set);
            hashMap.put("sol",sans);


databaseReference.child("testid"+sl).setValue(hashMap);
        }
        else {
            Toast.makeText(MainActivity.this, "enter", Toast.LENGTH_SHORT).show();
        }
    }
});

    }

    private void getdata() {

    sdata=tdata.getText().toString();
    sc1=tc1.getText().toString();
    sc2=tc2.getText().toString();
    sc3=tc3.getText().toString();
    sc4=tc4.getText().toString();
    sans=tans.getText().toString();
    sexpl=texpl.getText().toString();

    }

    private boolean notempyty() {


        if(TextUtils.isEmpty(slink))
            return  false;
        if(TextUtils.isEmpty(sexpl))
            return  false;
        if(TextUtils.isEmpty(sdata))
            return  false;
        if(TextUtils.isEmpty(sc1))
            return  false;
        if(TextUtils.isEmpty(sc2))
            return  false;
        if(TextUtils.isEmpty(sc3))
            return  false;
        if(TextUtils.isEmpty(sc4))
            return  false;
        if(TextUtils.isEmpty(sans))
            return  false;


        return true;

    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        slink=country[position];
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



}
