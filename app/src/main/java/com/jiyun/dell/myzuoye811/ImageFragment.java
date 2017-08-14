package com.jiyun.dell.myzuoye811;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import uk.co.senab.photoview.PhotoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    private String path = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=%E9%AB%98%E6%99%93%E6%9D%BE&bk_length=600";
    private PhotoView phont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        initView(view);

        initData();
        return view;
    }

    private void initData() {
        OkHttpUtil.getInstance().get(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ",e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                JianJie jianJie = gson.fromJson(string, new TypeToken<JianJie>() {
                }.getType());

                final String card = jianJie.getImage();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getContext()).load(card).into(phont);
                    }
                });
            }
        });
    }

    private void initView(View view) {
        phont = (PhotoView) view.findViewById(R.id.phont);
    }
}
