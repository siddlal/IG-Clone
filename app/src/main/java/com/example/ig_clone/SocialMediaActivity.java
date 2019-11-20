package com.example.ig_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

 public class SocialMediaActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("mY Social");

        toolBar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolBar); // setting toolbar at the top.

        viewPager = findViewById(R.id.viewPager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);



    }
}
