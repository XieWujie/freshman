package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.StrategyRcAdapter;
import com.mredrock.cyxbs.freshman.view.tool.RcDecoration;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.ArrayList;
import java.util.List;

public class StrategyActivity extends AppCompatActivity implements CampusView, View.OnClickListener{

    private List<Strategy> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StrategyRcAdapter adapter;
    private CampusStrategyPresenter presenter;
    private TextView labelText;
    private ImageView backImag;
    private RelativeLayout bedroomChooseLayout;
    private RelativeLayout top5Layout;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stategy);
        recyclerView = (RecyclerView)findViewById(R.id.strategy_recycler_view);
        recyclerView.addItemDecoration(new RcDecoration());
        labelText = (TextView)findViewById(R.id.strategy_name);
        backImag = (ImageView)findViewById(R.id.strategy_back);
        backImag.setOnClickListener(this);
        init();
    }
    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
         name = getIntent().getStringExtra("name");
        labelText.setText(name);
        presenter = new CampusStrategyPresenter(this, this);
        presenter.addData(name, 1, 10);
    }

    @Override
    public void getData(Strategy strategy) {
        mList.add(strategy);
    }

    @Override
    public void onFinish() {
        if (adapter==null){
            adapter = new StrategyRcAdapter(mList);
            switch (name) {
                case "周边美食":
                    adapter.isFood = true;
                    top5Layout = (RelativeLayout) findViewById(R.id.food_top5_layout);
                    top5Layout.setVisibility(View.VISIBLE);
                    break;
                case "公交线路":
                case "快递收发":
                case "附近银行":
                    adapter.isAnotherLayout = true;
                    break;
            }
        }
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strategy_back:
                finish();
                break;

        }
    }
}
