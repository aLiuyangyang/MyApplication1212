package com.example.dell.myapplication1212.util;

import android.os.Handler;
import android.os.Looper;

import com.example.dell.myapplication1212.callback.ICallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttputils {

    private Handler handler=new Handler(Looper.getMainLooper());
    private OkHttpClient mCilent;
    private static volatile OkHttputils mInstance;
    public static OkHttputils getmInstance(){
        if (mInstance==null){
            synchronized (OkHttputils.class){
               if (null==mInstance){
                   mInstance=new OkHttputils();
               }
            }
        }
        return mInstance;
    }
    public OkHttputils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mCilent=new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public void getEnQueue(String path, final Class clazz, final ICallback callback){
        Request request=new Request.Builder()
                .get()
                .url(path)
                .build();

        Call call=mCilent.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     callback.setFill(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(json, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.setSuccess(o);
                    }
                });
            }
        });
    }
}
