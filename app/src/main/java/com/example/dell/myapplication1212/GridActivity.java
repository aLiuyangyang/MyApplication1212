package com.example.dell.myapplication1212;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dell.myapplication1212.adapter.MyGrid;
import com.example.dell.myapplication1212.adapter.MyLinear;
import com.example.dell.myapplication1212.bean.Bean;
import com.example.dell.myapplication1212.bean.UserBean;
import com.example.dell.myapplication1212.presenter.Presenterimpl;
import com.example.dell.myapplication1212.util.Apis;
import com.example.dell.myapplication1212.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class GridActivity extends AppCompatActivity implements IView {
    private Presenterimpl presenterimpl;
    private int page;
    private String Userpath="http://www.xieast.com/api/news/news.php?page=%d";
    private XRecyclerView recyclerView;
    //  private final int mCount=3;
     private MyGrid adapter;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        inif();
     }

    private void inif() {
        recyclerView=findViewById(R.id.recy_grid);
        presenterimpl=new Presenterimpl(this);
        page=1;
      //设置布局管理器
       /* GridLayoutManager gridLayoutManager=new GridLayoutManager(this,mCount);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //动画
        adapter.setItemCilckLinear(new MyGrid.ItemCilckLinear() {
            @Override
            public void item(View view, int position) {
                adapter.animator(view,position);
            }
        });
        //删除
        adapter.setCilckLinear(new MyGrid.CilckLinear() {
            @Override
            public void cilck(int position) {
                adapter.removeData(position);
            }
        });
       //实例化
        adapter=new MyGrid(this);
        recyclerView.setAdapter(adapter);
        //支持刷新加载
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        presenterimpl.setRequestData(String.format(Userpath,page),UserBean.class);
    }

    @Override
    public void setData(Object data) {
        UserBean bean= (UserBean) data;
        if (page==1){
            adapter.setList(bean.getData());
        }else {
            adapter.addList(bean.getData());
        }

        page++;
        //停止
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
    }
   //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterimpl.del();
    }
}
