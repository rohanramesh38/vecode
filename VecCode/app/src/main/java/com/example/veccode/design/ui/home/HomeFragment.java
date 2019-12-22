package com.example.veccode.design.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.veccode.Adapter.MyCustomPagerAdapter;
import com.example.veccode.Adapter.RecyclerViewAdapter;
import com.example.veccode.Adapter.recRoundAdapter;
import com.example.veccode.Adapter.scoreVecAdapter;
import com.example.veccode.R;
import com.example.veccode.design.MainWorkActivity;
import com.example.veccode.utils.certModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageView draw;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    String User="";
    private ViewPager viewPager;
    private Spinner spvec,sphack;
    private ArrayList<Integer> totalscore;
    private TabLayout indicator;
    private  MyCustomPagerAdapter myCustomPagerAdapter;
    private com.cooltechworks.views.shimmer.ShimmerRecyclerView  recyclerView1,recyclerView2,recyclerView3;
  private   com.cooltechworks.views.shimmer.ShimmerRecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter,recyclerViewAdapter1;

    private recRoundAdapter roundRecAdapter;
    private scoreVecAdapter adapterscorevec;
    private ArrayList<certModel> rec, rec1,images,recRound;

    private ArrayList<String> recvec,recvecuser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager=root.findViewById(R.id.viewPager);
        indicator = root.findViewById(R.id.indicator);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView1 = root.findViewById(R.id.recyclerView1);
        recyclerView2 = root.findViewById(R.id.recyclerView3);
        recyclerView3=root.findViewById(R.id.recyclerView2);

        spvec=root.findViewById(R.id.spinnervec);

        ShimmerFrameLayout am=root.findViewById(R.id.shimmer_view_container);

      //  am.startShimmerAnimation();

        recvec=new ArrayList<>();
        recvecuser=new ArrayList<>();
        totalscore=new ArrayList<>();

        User=  FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("student","");
        User=User.replace("@vecode.com","");

        final ArrayList<String> aspvec=new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        aspvec.add(0,"20"+User.substring(4,6));

        if(!("20"+User.substring(4,6)).equals(""+(mYear)))
        aspvec.add(""+(mYear));
        if(!("20"+User.substring(4,6)).equals(""+(mYear-1)))
            aspvec.add(""+(mYear-1));
        if(!("20"+User.substring(4,6)).equals(""+(mYear-2)))
            aspvec.add(""+(mYear-2));
        if(!("20"+User.substring(4,6)).equals(""+(mYear-3)))
            aspvec.add(""+(mYear-3));


        String s[]={
                "auto",
                "else",
                "long",
                "switch",
                "break",
                "enum",
                "register",
                "typedef",
                "case",
                "extern",
                "return",
                "union",
                "char",
                "float",
                "short",
                "unsigned",
                "const",
                "for",
                "signed",
                "void",
                "continue",
                "goto",
                "sizeof",
                "volatile",
                "default",
                "if",
                "static",
                "while",
                "do",
                "int",
                "struct",
                "double",
                "COMPUTER",
                "GADGETS",
                "RESEARCH",
                "ELECTRONICS",
                "SOFTWARE",
                "COMMUNICATION",
                "TOOLS",
                "DOWNLOAD",
                "INTERNET",
                "MACHINE",
                "regression",
                "network",
                "terminal",
                "algorithm",
                "object",
                "pattern",
                "python",
                "architecture",
                "binary",
                "octal",
                "decimal",
                "hexa",
                "iot",
                "hacking",
                "virus",
                "linux",
                "main",
                "Daemon",
                "Epoch",
                "tensor",
                "random",
                "sklearn",
                "audrino",
                "raspberry",
                "cloud",
                "firebase",
                "nginx",
                "node",
                "link",
                "https",
                "smtp",
                "boot",
                "android",
                "thread",
                "root",
                "tree",
                "data",
                "csharp",
                "method",
                "function",
                "prototype",
                "intel",
                "analysis",
                "design",
                "problem",
                "anagram",
                "array",
                "runtime",
                "core",
                "java",
                "util",
                "date",
                "sequence",
                "bundle",
                "scanner",
                "system",
                "hashmap",
                "schema",
                "relational",
                "hierarchy",
                "kernel",
                "process",
                "boolean",
                "dictionary",
                "enumeration",
                "compile",
                "linear",
                "stack",
                "queue",
                "enqueue",
                "dequeue",
                "adt",
                "graph",
                "dfs",
                "bfs",
                "address",
                "pointer",
                "sql",
                "query",
                "boyce",
                "agi,e",
                "component",
                "testing",
                "implement",
                "coding",
                "whitebox",
                "blackbox",
                "casetools",
                "cocomo",
                "database",
                "xml",
                "brute",
                "dynamic",
                "greedy",
                "iterartive",
                "critical",
                "thrashing",
                "paging",
                "demand",
                "supply",
                "acid",
                "deadlock",
                "concurrent",
                "Raid",
                "select",
                "join",
                "merge",
                "cost",
                "encapsulation",
                "inheritance",
                "polymorphism",
                "comments",
                "cloning",
                "stream",
                "byte",
                "processor",
                "generic",
                "sync",
                "reference",
                "slices",
                "swaping",
                "file",
                "sorting",
                "sort",
                "exception",
                "copy",
                "set",
                "Debian",
                "tuple",
                "numpy",
                "argument",
                "fifo",
                "lifo",
                "index",
                "indexed",
                "constructor",
                "string",
                "model",
                "semaphore",
                "gof",
                "factory",
                "bridge",
                "grasp",
                "uml",
                "datagram",
                "automata",
                "usecase",
                "association",
                "activity",
                "deployment",
                "closure",
                "nfa",
                "turing",
                "tcp",
                "lemma",
                "push",
                "pop",
                "switching",
                "domain",
                "lan",
                "man",
                "wan",
                "service",
                "interpreter",
                "beta",
                "optimal",
                "php",
                "ajax",
                "dns",
                "email",
                "DOS"
        };


      /*  for(int i=0;i<s.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();

            DatabaseReference db=  FirebaseDatabase.getInstance().getReference().child("Game").child("Jumble").child("id_"+i);

            String qes[]= s[i].split("");
            String question=scramble(qes,s[i]);

            hashMap.put("ans",s[i]);
            hashMap.put("ques",question);
            hashMap.put("tag","");
            hashMap.put("expl","");

            db.setValue(hashMap);

        }*/





        adapterscorevec = new scoreVecAdapter(getContext(), recvec,recvecuser,recvec);


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,aspvec);

        spvec.setAdapter(arrayAdapter);
spvec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), ""+aspvec.get(position), Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference().child("ScoreBoardVec").child(aspvec.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    recvec.clear();
                    recvecuser.clear();
                    totalscore.clear();
                    for (DataSnapshot d1 : dataSnapshot.getChildren()) {
                        recvec.add(d1.getValue().toString());
                        recvecuser.add(d1.getKey());

                        String ds[] = d1.getValue().toString().split("_");
                        if (ds.length == 3) {
                            totalscore.add(Integer.parseInt(ds[0]) * 100 + Integer.parseInt(ds[1]) * 10 + Integer.parseInt(ds[2]));
                        }
                    }
                    if (sortData()) {
                        adapterscorevec = new scoreVecAdapter(getContext(), recvec, recvecuser, recvec);
                        recyclerView2.setAdapter(adapterscorevec);

                        adapterscorevec.SetOnItemClickListener(new scoreVecAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position, String model) {
                            }
                        });

                        //   System.out.println(recvecuser);
                        // System.out.println(recvec);
                        //System.out.println(totalscore);

                    }
                }
                else{
                    Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      //  Collections.reverse(recvec);
        //Collections.reverse(recvecuser);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

      /*  spvec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    */    homeViewModel.getViewPagerData().observe(this, new Observer<ArrayList<certModel>>() {
    @Override
    public void onChanged(ArrayList<certModel> models) {

        images=models;
        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), models,getActivity());
        viewPager.setAdapter(myCustomPagerAdapter);
        indicator.setupWithViewPager(viewPager, true);


      //

    }
});
        rec=new ArrayList<>();
        rec1=new ArrayList<>();
        recRound=new ArrayList<>();

        roundRecAdapter =new recRoundAdapter(getContext(),recRound);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), rec);
        recyclerViewAdapter1 = new RecyclerViewAdapter(getContext(), rec);



        homeViewModel.getRecRound().observe(this, new Observer<ArrayList<certModel>>() {
            @Override
            public void onChanged(ArrayList<certModel> models) {
                recRound=models;

                roundRecAdapter =new recRoundAdapter(getContext(),recRound);
                setAdapter();

            }
        });



        homeViewModel.getRec().observe(this, new Observer<ArrayList<certModel>>() {
            @Override
            public void onChanged(ArrayList<certModel> certModels) {
                rec=certModels;
                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), rec);
setAdapter();
            }
        });

        homeViewModel.getRec1().observe(this, new Observer<ArrayList<certModel>>() {
            @Override
            public void onChanged(ArrayList<certModel> certModels) {
                rec1=certModels;
                recyclerViewAdapter1 = new RecyclerViewAdapter(getContext(), rec1);
setAdapter();

            }
        });

//NavController navcont= Navigation.findNavController(getActivity(),root.getId());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 1000, 5000);






        return root;
    }

    private boolean sortData() {
try {


    for (int i = 0; i < totalscore.size(); i++) {
        for (int j = i + 1; j < totalscore.size(); j++) {
            if (totalscore.get(i) < totalscore.get(j)) {

                int temp = totalscore.get(i);
                totalscore.set(i, totalscore.get(j));
                totalscore.set(j, temp);

                String st1 = recvec.get(i);
                recvec.set(i, recvec.get(j));
                recvec.set(j, st1);


                String st2 = recvecuser.get(i);
                recvecuser.set(i, recvecuser.get(j));
                recvecuser.set(j, st2);


            }


        }

    }
}catch (IndexOutOfBoundsException e){

}

        System.out.println(recvecuser);
        System.out.println(recvec);
        System.out.println(totalscore);
    return true;
    }

    private String scramble(String[] qes, String s) {

        List<String> lette= Arrays.asList(qes);
        Collections.shuffle(lette);
        StringBuilder sb=new StringBuilder( lette.size()-1);
        for(String c:lette)
        {
            sb.append(c);
        }

        if(s.equals(sb.toString()))
        {
            scramble(qes, s);
        }
        else
        {
            return sb.toString();


        }

return s;
    }

    private void setAdapter() {


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));



        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL,false));

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView3.setAdapter(roundRecAdapter);

        recyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, certModel model) {

            //    Toast.makeText(getActivity(), ""+rec.get(position).getLink(), Toast.LENGTH_SHORT).show();

Bundle args=new Bundle();
args.putString("data",rec.get(position).getLink());
                args.putString("name",rec.get(position).getName());

NavController navController=Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
navController.navigate(R.id.to_webFragment,args);
            }
        });
        recyclerViewAdapter1.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, certModel model) {

                Bundle args=new Bundle();
                args.putString("data",rec1.get(position).getLink());
                args.putString("name",rec1.get(position).getName());
                NavController navController=Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.to_webFragment,args);
              //  Toast.makeText(getActivity(), ""+rec1.get(position).getLink(), Toast.LENGTH_SHORT).show();


            }
        });

        roundRecAdapter.SetOnItemClickListener(new recRoundAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, certModel model) {
                Bundle args=new Bundle();
                args.putString("data",recRound.get(position).getLink());
                args.putString("name",recRound.get(position).getName());
                NavController navController=Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.to_webFragment,args);
            }
        });

/*
String s[]={"https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Feaf8b822ae238a31a7831d6198511f30.jpg?alt=media&token=a7c2dda6-84e6-43c4-9cac-ec29df71546b","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fea552d3cb33ee5d3ba369ed7f54b4de6.jpg?alt=media&token=6e99c75f-94a0-4953-b266-af3925a94296","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fe8d693f75f1039a54ba44dba1af7f324.jpg?alt=media&token=d61fb6fc-2cc7-4fbd-9a91-799566ac61cf","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fe377a0cc46a29ed9bfb714a869bd3ede.jpg?alt=media&token=50715d03-1d81-4afb-ad08-b0a10c2dcc30","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fe207ad541a9ed2aee724b8fee0ae5254.jpg?alt=media&token=e0129361-fcb6-40e3-ba14-369a64baeddc","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fe1ff37308cd720b3f3edc21fb438424f.jpg?alt=media&token=c2a737fd-98f7-4c14-a03f-8c8d13315506","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fddfcbc6a150f273bf6e7a17e4fc3a202.jpg?alt=media&token=ab7417e8-8fea-4ca7-ab7e-15da2926d64c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fdb483a832558e582ef7342bce9826690.jpg?alt=media&token=e304a3e5-c4e7-491a-809e-aaee9a240d23","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fce63f664f95757b340669de4efff728e.jpg?alt=media&token=ba405486-0c32-4df8-a0b7-c52657360143","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fcbdf0c952cd226bda005b7d7de746a5d.jpg?alt=media&token=985947cb-6454-4b70-b2f0-fb1c8cc4c2a8","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fcb9eaf84ebac4fed533034817372ee28.jpg?alt=media&token=ace93de7-ec99-45b6-af0c-91133b3128b2","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fcad4207e77ecc0bb82fceb75b1441d8b.jpg?alt=media&token=d41a8c0e-9fe2-4130-944c-9bbd195ca6dc","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fc95f3df0580d89f187b2cefdec3d9e02.jpg?alt=media&token=de6a227d-e4e3-4c2f-869e-5c2d6cabe9cd","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fc7e87a1af01442936d28f2faee9dd241.jpg?alt=media&token=3caea43e-852e-49b3-8f84-635d3b4c317c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fc46de93bec9c8576b50b01ce90fdfb1f.jpg?alt=media&token=9c63f784-0306-486c-a34e-bf20963ec5cd","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fc3b10bc8605727ab3149324fd174209d.jpg?alt=media&token=90b3ac2d-4764-4719-8408-ae1914219272","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fbfa1f678fbbe6b11071c8422c5167af9.jpg?alt=media&token=5d9e04bb-c634-420f-b961-f4cdd85cdcb5","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fb564b7085c0e245db05e50cb64d5cdbf.jpg?alt=media&token=a2feb827-902b-4e11-b12c-421cf2cf3516","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fb47d50e02e76cc18925d7e4e320a5a6b.jpg?alt=media&token=6c2e1573-a5f6-4879-b201-5fefdd920bd7","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fb25ccf16fd4dc1369a0527c681d99cd7.jpg?alt=media&token=7117f657-eb1e-420f-b932-0f126f594452","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fb16a92dae37f68aae6fc3bc4b005aa4e.jpg?alt=media&token=6c4db28a-1b4f-4bd3-af1b-6e00ccfc8ed3","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Faf931a42d6f8d78af811a6c82bd38e45.jpg?alt=media&token=c8b66b2a-f849-4d88-b285-da52aa82f54c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fa7bf65f4003a7d77a0164f2689706f4c.jpg?alt=media&token=e8392ea3-2e2d-4d18-bb1f-8974a222824b","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fa63a1668bf5225c1109662b82528aede.jpg?alt=media&token=1b820abf-7f4d-4c6b-8b90-f02cc1283954","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2Fa10f1e9bfbd62c0a9553b39c8d407bc0.jpg?alt=media&token=385c1f2a-ce30-44ea-8bb9-515d085bc835","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F9ee41e04ba0e242401090d7c71f8acb6.jpg?alt=media&token=8bd80467-0098-4353-9914-1713d08f027a","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F9e3ea0723bd7cb8a8fea3ddb3813af32.jpg?alt=media&token=81bb424a-d305-49c8-b975-5811bb9c76b5","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F9c19391eef8a8b8cde1cd31fb6021ea8.jpg?alt=media&token=b688c2dd-c07a-43b5-bcbd-27bfcf260503","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F923338566bad011465a0554e962b72d2.jpg?alt=media&token=fcf5dce1-0eea-4724-934c-959414ec9055","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F9084033f35f18d4bcd94fc2da8b05ac9.jpg?alt=media&token=1279bd2b-d752-42f3-96b6-3bd624fe7855","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F8e1cee881ce62ec0afa26cefb7e984f4.jpg?alt=media&token=60eecef0-1d09-4893-8eed-250aa9f5ec6d","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F8662760ca2a556becfcd23359b44d789.jpg?alt=media&token=47ad8806-efb1-471e-a2fa-69c985580b52","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F7fcf22d31b904eb62e753f83073a85c3.jpg?alt=media&token=6af502af-84aa-4253-b65d-60a012f9e95e","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F777473869585f1cc01b11cfcf3145f63.jpg?alt=media&token=8e7c1f39-4b7f-4ef9-8869-b35fe8320b62","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F76fd53a41ff95c50fc57502ee02cd231.jpg?alt=media&token=928de4c4-531c-47ef-b790-ee682e5fd522","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F768eaa1f37b299a76be8aa28614634c7.jpg?alt=media&token=39bba447-6136-49af-b694-29de81a76314","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F6a3a1eb6cedf0211831ee9cfcc58805f.jpg?alt=media&token=76e638fe-65d9-4d87-95cc-3cd5ef24d65d","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F69b2313dc283f2b4d3338ea3b37bec56.jpg?alt=media&token=67c639dd-2220-48fa-8916-2e55cd4b5f43","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F696fb6a908cd1b7d7023851a97655990.jpg?alt=media&token=b64a7f10-bb66-47cd-927f-76379bcecc17","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F6967e25ac47fdf5c85dde387af486f28.jpg?alt=media&token=260b5e32-c06c-4ea6-b063-c47eb76187c0","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F688d5e109f4f78a678b8375dcfce56a9.jpg?alt=media&token=c2d6291b-e386-4740-bcb9-88048e6ec512","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F67cdfec7b637a4b8f29537fa79ee1725.jpg?alt=media&token=8383fd01-c6b8-46ac-9d1b-98ee3880fb36","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F60a595b9526cc93cd04923be7764d03b.jpg?alt=media&token=c87d2f1a-b0cd-4497-b6ef-38727218f045","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F5b9ba15d17dd3cabf14af744300ef253.jpg?alt=media&token=a16d5e35-e9cc-4c85-93af-e67425f5831e","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F59540e3a1f987b3f948610717efb1dba.jpg?alt=media&token=470fc9c2-515e-4332-9815-db81aa58d72b","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F594a23fa988e619dd9d0e8220560c6b1.jpg?alt=media&token=94046320-8fec-48cd-902d-1376e6d1ddc1","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F539aceebca86df9c161ae9ec119b3803.jpg?alt=media&token=cd7d71a9-6a90-4609-9f67-797aaf7e8153","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F53632f2bd26abf625d2c1c2594772540.jpg?alt=media&token=bd2e5851-5a5e-4307-ba3d-e1e0bdf1a56d","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F4d01b03ae7092c63236082aa51a6b62d.jpg?alt=media&token=ab2c6b7f-6675-4c8b-acbc-b1c33583772c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F4c828ad8fd72aa89175d7f3eea71bd87.jpg?alt=media&token=731018aa-d58f-431f-9cc9-f61a7300710b","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F46b197134d5193d09ebdf6638800a245.jpg?alt=media&token=a62d63b8-7d8a-4c5d-8176-f00cb7b8b957","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F468de9849d5c6bea1bcd8e3030e08866.jpg?alt=media&token=481a73fb-b1e7-46ed-a429-5fe3abda0e60","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F45cdd1d2df438adf39241ae19565b870.jpg?alt=media&token=0ee4ec2b-8205-4a03-8881-3d8a446c3a85","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F4578b03e30be0e8c1e1dada5b7b4104b.jpg?alt=media&token=e9f47a65-0b24-48f8-a9cf-3290da8980c8","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F42a561a00a77fec4d0e8f7cbeac39d1c.jpg?alt=media&token=8ddb930e-ff7f-4119-9a02-4470d0c0a363","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3fc714aceeb0dfd584e2519269dc1a4a.jpg?alt=media&token=91446cc1-baea-4596-b7f2-667ee4e3ac99","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3ed34ec79756ba85ead5fbe9e9b9a45a.jpg?alt=media&token=200d7c74-cf54-40c9-97dc-438a60471915","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3e8c4d5aaa967f0f40da6541dc25c4bb.jpg?alt=media&token=e879ba4e-34da-43b3-b68c-d08b2a8d2b9a","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3e83aa16b5a59888e2b1e78cdd6cd26d.jpg?alt=media&token=4ccef260-d087-4b09-9a7b-f44e6384255a","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3cf2226836601accef8c9e5286f13a42.jpg?alt=media&token=7308b61d-09e6-4a45-9a4c-85790e481992","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3ca87f90be1beaec0e671dc834a78fe4.jpg?alt=media&token=4086ac4e-8194-4e1d-87de-cea470861757","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3ab622b397586e3b40f3abb7b34a68ed.jpg?alt=media&token=11bf7ee0-e416-4603-bb21-1323f27a8e61","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F3a51669e8b71c882254684f5b4c52a13.jpg?alt=media&token=95ac71c9-54f8-4b49-98b8-21446f4eddf1","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F34ab9948eb022de2bca6ce6a28e7e6a4.jpg?alt=media&token=41f6a7be-f4b1-4795-b3fc-cb99375459c2","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F2e58ea581aeb8d7a5fb88f545a2fe8d1.jpg?alt=media&token=20768307-6e81-46c9-966d-9857f2de83ff","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F2d5f57a71ee347fcd45610fb81e3f89b.jpg?alt=media&token=261cb1c6-a7f7-48e0-a06b-c4ceb7f34cdf","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F2af42add0ab59e68454296978443fbf8.jpg?alt=media&token=b34df21d-f360-4547-b542-5483c888b27c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F2a053ed89b0ddbea1a51641b2c28e445.jpg?alt=media&token=59a2fd40-de99-44dd-a23c-0c3f4e516d3f","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F29e9b6887951a0eb2abc362637c3bece.jpg?alt=media&token=41c5ecef-1547-4d5e-8358-92aca600f530","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F29b60b1a288b58d0a9c1de1fda8ef1a9.jpg?alt=media&token=1d09fb8a-fd6f-41dd-822a-53fc3348b5a1","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F268af9d883ce39062c45d4e5893f45cc.jpg?alt=media&token=4df7b14f-73af-4ef2-9420-759275b49402","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F1cbf68a1fdc97a4de02de9828b380213.jpg?alt=media&token=0df1eff7-c5a7-4944-9a2f-5048c54bde42","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F19632d6011aadd89a7c2a91802fb5eb5.jpg?alt=media&token=cc0f75e2-02cc-4675-a453-c49c74a78c9d","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F171f9bdb3948b2a3a7b32bd88953d479.jpg?alt=media&token=0e0d7da3-78b7-4439-92be-8bb482e6767c","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F1378a7f2460341ec3c777533924f0d74.jpg?alt=media&token=277fe7ad-6c97-416f-89a8-1a076ec384e2","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F111b52f6191290edea3f97e9bcd46ee9.jpg?alt=media&token=d6670784-9a9a-4ac5-a622-ce6d10b6f5b5","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F10acdf9b53b54cb31ab8474a8f8dd9ed.jpg?alt=media&token=f31b2406-d65f-4edc-8c17-3bd4c3fb5061","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F0700b6397a00a5fe49d4864ad3c240ad.jpg?alt=media&token=5d0c955f-5e2b-4436-9a8a-78c8e7ab090a","https://firebasestorage.googleapis.com/v0/b/codeplus-88e44.appspot.com/o/MemeFeed%2F05d6719e5c665ef0d491dbd8a59efd23.jpg?alt=media&token=190176b1-e7cb-48a9-b2f7-016b5fd9b411"};
for(int i=0;i<s.length;i++)
{
    HashMap<String,String> has=new HashMap<>();
    has.put("date","");
    has.put("desc","");
    has.put("image",s[i]);
    has.put("like","");
    has.put("link","");

    FirebaseDatabase.getInstance().getReference().child("InstaFeed").child("id_"+(991-i)).setValue(has);




}*/

        /*      final TextView textView = root.findViewById(R.id.text_gallery);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
  */



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       // System.out.println(item.getItemId());


        return false;
    }
    private class SliderTimer extends TimerTask {

        @Override
        @Nullable
        public void run() {
            try {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(images!=null)
                        {

                            if (viewPager.getCurrentItem() < images.size() -1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                            else
                                {
                            viewPager.setCurrentItem(0);
                            }

                        }
                    }
                });
            }catch (NullPointerException e)
            {

            }


        }
    }


}
