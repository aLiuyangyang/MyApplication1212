package com.example.dell.myapplication1212;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.dell.myapplication1212.adapter.MyLinear;
import com.example.dell.myapplication1212.bean.Bean;
import com.example.dell.myapplication1212.presenter.Presenterimpl;
import com.example.dell.myapplication1212.util.Apis;
import com.example.dell.myapplication1212.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class LinearActivity extends AppCompatActivity implements IView {
    private Presenterimpl presenterimpl;
    public static String path="http://www.zhaoapi.cn/product/getCatagory?page=1";
    private int page;
    int mIndex=0;
    private XRecyclerView xRecyclerView;
    private MyLinear adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        inif();
    }

    private void inif() {
        presenterimpl=new Presenterimpl(this);
        page=1;
        xRecyclerView=findViewById(R.id.xrecy_linear);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);

        adapter=new MyLinear(this);
        xRecyclerView.setAdapter(adapter);

        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                 page=1;

 load();
            }

            @Override
            public void onLoadMore() {
load();
            }


        });
        load();
    }

    private void load() {
        presenterimpl.setRequestData(path,Bean.class);
    }


    @Override
    public void setData(Object data) {
        Bean bean= (Bean) data;

          if (page==1){
              adapter.setList(bean.getData());
          }else{
              adapter.addList(bean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
        }

}
