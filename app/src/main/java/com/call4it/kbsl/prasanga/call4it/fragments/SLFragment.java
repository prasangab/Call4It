package com.call4it.kbsl.prasanga.call4it.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.call4it.kbsl.prasanga.call4it.R;

/**
 * Created by root on 6/16/16.
 */
public class SLFragment extends Fragment{

    //private String title;
    //private int page;

    // newInstance constructor for creating fragment with arguments
    //public static NTCFragment newInstance(int page, String title){
    //    NTCFragment ntc = new NTCFragment();
     //   Bundle args = new Bundle();
     //   args.putInt("some int", page);
     //   args.putString("some tilte", title);
     //   ntc.setArguments(args);
     //   return ntc;
   // }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  page = getArguments().getInt("some int", page);
      //  title = getArguments().getString("some title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sl, container, false);
        //TextView tvLabel = view.findViewById(R.id.tvLabel);
        //tvLabel.setText(page + "--" + title);
        return view;
    }
}
