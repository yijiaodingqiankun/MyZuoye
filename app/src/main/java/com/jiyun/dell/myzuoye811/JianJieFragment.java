package com.jiyun.dell.myzuoye811;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class JianJieFragment extends Fragment {

    private String path = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=%E9%AB%98%E6%99%93%E6%9D%BE&bk_length=600";
    private RecyclerView recyc;
    private List<JianJie.CardBean> list=new ArrayList<>();
    private MyReAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jian_jie, container, false);
        initView(view);
        initData();

        return view;
    }

    private void initAdapter() {
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyc.setLayoutManager(manager);
        adapter = new MyReAdapter(getContext(),list);
        recyc.setAdapter(adapter);
    }

    private void initData() {
        OkHttpUtil.getInstance().get(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                JianJie jianJie = gson.fromJson(string, new TypeToken<JianJie>() {
                }.getType());

                List<JianJie.CardBean> card = jianJie.getCard();
                list.addAll(card);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        recyc = (RecyclerView) view.findViewById(R.id.recyc);
        initAdapter();
    }
}
