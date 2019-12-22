package com.example.veccode.design.BottomNav.Play;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.veccode.R;

public class PlayFragment extends Fragment {

    private PlayViewModel mViewModel;
    private   View root;
    private Button jumble,sequemce;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.play_fragment, container, false);


        jumble=root.findViewById(R.id.jumble);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        sequemce=root.findViewById(R.id.sequence);

        jumble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.action_playFragment_to_anagramFragment);
            }
        });


        sequemce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
        // TODO: Use the ViewModel
    }

}
