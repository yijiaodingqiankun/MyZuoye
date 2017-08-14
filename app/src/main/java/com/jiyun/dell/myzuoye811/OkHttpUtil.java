package com.jiyun.dell.myzuoye811;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by DELL zhanghuirong on 2017/8/11.
 */

public class OkHttpUtil {
     private static OkHttpUtil okHttpUtil;
         private OkHttpClient okHttpClient;
         private OkHttpUtil(){
             okHttpClient=new OkHttpClient.Builder().build();
         }
         public static OkHttpUtil getInstance(){
             if (okHttpUtil==null){
                 synchronized (OkHttpUtil.class){
                     if (okHttpUtil==null){
                         okHttpUtil=new OkHttpUtil();
                     }
                 }
             }
             return okHttpUtil;
         }
         public void get(String url, Callback callback){
             Request request=new Request.Builder().url(url).build();
             okHttpClient.newCall(request).enqueue(callback);
         }
         public void post(String url, Callback callback){
             RequestBody body=new FormBody.Builder().build();
             Request request=new Request.Builder().url(url).method("POST",body).build();
             okHttpClient.newCall(request).enqueue(callback);
         }
}
