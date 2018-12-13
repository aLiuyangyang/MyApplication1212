package com.example.dell.myapplication1212.model;

import com.example.dell.myapplication1212.callback.Mycallback;

public interface Model {
    void setRequestData(String path, Class clazz, Mycallback mycallback);
}
