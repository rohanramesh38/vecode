package com.example.veccode.design.Mcq;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.veccode.Adapter.ViewPagerAdapter;
import com.example.veccode.R;
import com.example.veccode.utils.testModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainMcqFragment extends Fragment {
    List<testModel> list = new ArrayList<>();

    private MainMcqViewModel mViewModel;
    private ViewPager2 viewPager2;

    public static MainMcqFragment newInstance() {
        return new MainMcqFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {


         String test=getArguments().getString("test");
        final String link=getArguments().getString("link");

        View root= inflater.inflate(R.layout.main_mcq_fragment, container, false);
        viewPager2 = root.findViewById(R.id.viewPager2);

final String testArray[]=test.split("_");

if(testArray.length==5)
for(int i=0;i<5;i++)
{
    final int finalI = i;
    FirebaseDatabase.getInstance().getReference().child(link).child(testArray[i]).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            testModel testModel= dataSnapshot.getValue(testModel.class);
            list.add(testModel);

            if(finalI ==4)
                viewPager2.setAdapter(new ViewPagerAdapter(getContext(), list, viewPager2,getActivity(),link,testArray));

            System.out.println(list.get(finalI)+" "+testArray[0]+" "+testArray[1]+" "+testArray[2]+" "+testArray[4]);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });




}







return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainMcqViewModel.class);




        // TODO: Use the ViewModel
    }

}
