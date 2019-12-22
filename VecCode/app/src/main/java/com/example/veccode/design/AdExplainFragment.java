package com.example.veccode.design;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.veccode.R;

import javax.xml.transform.Templates;

import okhttp3.internal.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdExplainFragment extends Fragment {

private ImageView imposter;
private TextView tvrules,tvlink,tvdesc,tvname;
    public AdExplainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root=inflater.inflate(R.layout.fragment_ad_explain, container, false);

      imposter=root.findViewById(R.id.imageView3);
      tvrules=root.findViewById(R.id.rules);
      tvname=root.findViewById(R.id.headad);
      tvdesc=root.findViewById(R.id.desc);
      tvlink=root.findViewById(R.id.link);
        final String rules=getArguments().getString("rules");
        final String image=getArguments().getString("image");
        final String desc=getArguments().getString("desc");
        final String link=getArguments().getString("link");
        final String name=getArguments().getString("name");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setTitle(name);


        Glide.with(getContext())
                .load(image).fitCenter().override(1000,1000)
                .into(imposter);

        tvname.setText(name);
        tvlink.setText(link);
        tvrules.setText(rules);
        tvdesc.setText(desc);


        tvlink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ClipboardManager cm= (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("text",tvlink.getText());
                cm.setPrimaryClip(clipData);
                Toast.makeText(getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();

                return true;
            }
        });



        return root;
    }

}
