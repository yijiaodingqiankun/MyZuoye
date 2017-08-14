package com.jiyun.dell.myzuoye811;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenDuoFragment extends Fragment {

    private String path = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=%E9%AB%98%E6%99%93%E6%9D%BE&bk_length=600";
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gen_duo, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        webView = (WebView) view.findViewById(R.id.web);
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

                final String wapUrl = jianJie.getWapUrl();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl(wapUrl);
                        //设置支持JavaScript
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                webView.loadUrl(url);

                                return true;
                            }
                        });

                    }
                });

            }

        });
    }
}