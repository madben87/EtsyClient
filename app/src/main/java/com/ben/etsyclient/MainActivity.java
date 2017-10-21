package com.ben.etsyclient;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.etsyclient.adapter.PagerAdapter;
import com.ben.etsyclient.custom_view.JazzBallTextView;
import com.ben.etsyclient.view.saved_view.SavedFragment;
import com.ben.etsyclient.view.search_view.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new SavedFragment(), "History");
        viewPager.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            LayoutInflater inflater = LayoutInflater.from(this);
            View v = inflater.inflate(R.layout.custom_title, null);
            JazzBallTextView title = v.findViewById(R.id.my_title);

            if (title.getParent() != null) {
                ((ViewGroup) title.getParent()).removeView(title);
            }
            actionBar.setCustomView(title);
        }
    }
}
