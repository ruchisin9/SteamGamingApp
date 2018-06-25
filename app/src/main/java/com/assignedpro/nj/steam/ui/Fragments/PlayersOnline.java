package com.assignedpro.nj.steam.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignedpro.nj.steam.R;

/**
 * Created by Ankur_ on 6/18/2018.
 */

public class PlayersOnline extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.detaildescription, container, false);
       return view;
    }
}
