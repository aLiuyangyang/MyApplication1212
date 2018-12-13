package com.example.dell.myapplication1212.model;

import com.example.dell.myapplication1212.callback.ICallback;
import com.example.dell.myapplication1212.callback.Mycallback;
import com.example.dell.myapplication1212.util.OkHttputils;

public class Modelmpl implements Model{

    @Override
    public void setRequestData(String path, Class clazz, final Mycallback mycallback) {
        OkHttputils.getmInstance().getEnQueue(path, clazz, new ICallback() {
            @Override
            public void setSuccess(Object obj) {
                mycallback.setData(obj);
            }

            @Override
            public void setFill(Exception ex) {
                mycallback.setData(ex);
            }
        });
    }
}
