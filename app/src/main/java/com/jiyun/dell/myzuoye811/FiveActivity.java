package com.jiyun.dell.myzuoye811;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;
    List<String> list = new ArrayList<>();
    private FrameLayout frame;
    private android.app.FragmentManager fragment;
    private FragmentManager manager;
    private Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        MyAdapter adapter = new MyAdapter(this, list);
        listview.setAdapter(adapter);

        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, new GenDuoFragment());
        transaction.commit();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final FragmentTransaction transaction = manager.beginTransaction();
                switch (i) {
                    case 0:


                        transaction.replace(R.id.frame, new JianJieFragment()).commit();
                        break;
                    case 1:
                        transaction.replace(R.id.frame, new ImageFragment()).commit();
                        break;
                    case 2:
                        transaction.replace(R.id.frame,new GenDuoFragment()).commit();
                        break;
                }

            }
        });
    }

    private void initData() {
        list.add("个人简介");
        list.add("个人图片");
        list.add("更多内容");



    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("高晓松简介");
        Circle circle=new Circle(FiveActivity.this);
        circle.invalidate();
        toolbar.addView(circle);
        setSupportActionBar(toolbar);
        listview = (ListView) findViewById(R.id.listview);
        frame = (FrameLayout) findViewById(R.id.frame);
    }
}
