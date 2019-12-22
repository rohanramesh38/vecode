package com.example.veccode.design.ui.mcq;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veccode.Adapter.MyCustomPagerAdapter;
import com.example.veccode.Adapter.RecyclerViewAdapter;
import com.example.veccode.R;
import com.example.veccode.utils.certModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class McqFragment extends Fragment {

    private McqViewModel mcqViewModel;
    private RecyclerView recyclerView;
    private ArrayList<certModel> recMain;
    private RecyclerViewAdapter recyclerViewAdapter;
String link,today;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mcqViewModel = ViewModelProviders.of(this).get(McqViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mcq, container, false);

      //  final TextView textView = root.findViewById(R.id.text_slideshow);

        recyclerView=root.findViewById(R.id.recycler);


        recMain=new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), recMain);




        setAdapter();



        mcqViewModel.getRecMain().observe(this, new Observer<ArrayList<certModel>>() {
            @Override
            public void onChanged(ArrayList<certModel> certModels) {
                recMain=certModels;
                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), recMain);
                setAdapter();
            }
        });


        return root;
    }


    private void setAdapter() {

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position, final certModel model) {

                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH)+1; // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                if(!model.getLink().equals("NO")) {
                    FirebaseDatabase.getInstance().getReference().child("DailyMcq").child(model.getLink()).child(mDay + "_" + mMonth + "_" + mYear).child("test").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {

                            SharedPreferences sp = getActivity().getSharedPreferences("com.veccode.PRIVATEDATA", Context.MODE_PRIVATE);
                            today = sp.getString("Today" + model.getLink(), "not");

                            if (!(mDay + "_" + mMonth + "_" + mYear).equals(today)) {
                                Bundle b = new Bundle();
                                String data = dataSnapshot.getValue().toString();
                                b.putString("test", data);
                                b.putString("link", recMain.get(position).getName());
                                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                                navController.navigate(R.id.to_mainMcqFragment, b);

                            } else {

                                Toast.makeText(getActivity(), "You have completed today's test", Toast.LENGTH_SHORT).show();
                            }
                            }
                            else {

                                Toast.makeText(getActivity(), "No test Scheduled today Sorry :-(", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                else {
                    Toast.makeText(getActivity(), "No Test Scheduled Today Sorry :-( ", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}