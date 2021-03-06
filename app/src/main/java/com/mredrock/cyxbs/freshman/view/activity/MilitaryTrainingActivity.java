package com.mredrock.cyxbs.freshman.view.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.adapter.MilitaryViewPagerAdapter;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
import com.mredrock.cyxbs.freshman.view.tool.ReflexChangeTab;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

public class MilitaryTrainingActivity extends AppCompatActivity  {
    private ReflexChangeTab reflexChangeTab=new ReflexChangeTab();
    private String TAG="MilitaryTrainingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military_training);
        MyService.setStatusBar(this);
        initview();
    }

    private void initview()
    {
        final Context mcontext=this;
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_mi);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.military_tab);
        tabLayout.addTab(tabLayout.newTab().setText("军训风采"));
        tabLayout.addTab(tabLayout.newTab().setText("小贴士"));
        reflexChangeTab.reflex(tabLayout);
        ViewPager viewPager= (ViewPager) findViewById(R.id.military_viewpager);
        viewPager.setAdapter(new MilitaryViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
