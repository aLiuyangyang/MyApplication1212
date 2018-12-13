package com.example.dell.myapplication1212;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dell.myapplication1212.adapter.MyLinear;
import com.example.dell.myapplication1212.adapter.MyStagged;
import com.example.dell.myapplication1212.bean.Bean;
import com.example.dell.myapplication1212.presenter.Presenterimpl;
import com.example.dell.myapplication1212.util.Apis;
import com.example.dell.myapplication1212.view.IView;

public class StaggedActivity extends AppCompatActivity implements IView {
    private Presenterimpl presenterimpl;
    private RecyclerView recyclerView;
    private MyStagged adapter;
    private final int mCounts=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagged);
        inif();
    }

    private void inif() {
         presenterimpl=new Presenterimpl(StaggedActivity.this);
        presenterimpl.setRequestData(Apis.URL_BEAN,Bean.class);
        recyclerView=findViewById(R.id.recy_stagged);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(mCounts,OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter=new MyStagged(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(Object data) {
        Bean bean= (Bean) data;
        if (bean.getCode().equals("0")){
            adapter.setList(bean.getData());
        }
    }
}
