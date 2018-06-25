package com.assignedpro.nj.steam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignedpro.nj.steam.R;
import com.assignedpro.nj.steam.network.Network;
import com.assignedpro.nj.steam.sharedprefrences.SharedPrefrences;
import com.assignedpro.nj.steam.ui.Fragments.DetailedDescription;

/**
 * Created by Ankur_ on 6/18/2018.
 */

public class SupportActivity extends AppCompatActivity {

    private SharedPrefrences sharedPrefrences;
    private Network network;
    private FragmentManager fragmentManager;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentManager fm = getSupportFragmentManager();//getLastFragmentManagerWithBack(getSupportFragmentManager());
        int fragCount=fm.getBackStackEntryCount();
        if(fragCount==0) {
            Intent i=new Intent(SupportActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        else
            fm.popBackStack();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_support);
        network = Network.getInstance(this);
        sharedPrefrences = SharedPrefrences.getsharedprefInstance(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsupport);
        setSupportActionBar(toolbar);
        ImageView  backbutton = (ImageView) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SupportActivity.this,MainActivity.class);
                startActivity(i);
                finish();            }
        });

        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        fragmentInit(DetailedDescription.class);


    }





    public void fragmentInit(Class fragClass) {

        Fragment fragment = null;
        try {
            fragment = (Fragment) fragClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.newcontentFrame, fragment).addToBackStack(null).commit();

    }

}
