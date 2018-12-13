package com.example.dell.myapplication1212.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication1212.R;
import com.example.dell.myapplication1212.bean.Bean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyStagged extends RecyclerView.Adapter<MyStagged.ViewHolder> {
    private List<Bean.DataBean> list;
    private Context context;
    public MyStagged(Context context){
        this.context=context;
        list=new ArrayList<>();
    }
    public void setList(List<Bean.DataBean> lista) {
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_stagged,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Bean.DataBean bean=list.get(i);
        viewHolder.textView.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_stagged);
        }
    }
}
