package com.example.dell.myapplication1212.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.myapplication1212.R;
import com.example.dell.myapplication1212.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MyLinear extends RecyclerView.Adapter<MyLinear.ViewHoler> {
    private List<Bean.DataBean> list;
    private Context context;
    private static final int TYPE_di=0;
    private static final int TYPE_de=TYPE_di+1;
   /* public interface OnItemClickListener{
        void onItemClick(View itemView,int positon);
    }
    public interface OnLongItemClickListener{
        void onlongitemCilck(View itemView,int position);
    }
    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener onLongItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener){
        this.onLongItemClickListener=onLongItemClickListener;
    }*/

    public MyLinear(Context context){
        this.context=context;
        list=new ArrayList<>();
    }

    public void setList(List<Bean.DataBean> lista) {
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }

    public void addList(List<Bean.DataBean> lista) {
        list.addAll(lista);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHoler holder=null;
        if (i==TYPE_di){
            View view=LayoutInflater.from(context).inflate(R.layout.type11,viewGroup,false);
            holder=new ViewHoler(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.type22, viewGroup, false);
             holder=new ViewHoler(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
       Bean.DataBean bean=list.get(i);
        ViewHoler holer=viewHoler;
        viewHoler.textView.setText(bean.getName());
        Glide.with(context).load(bean.getIcon()).into(holer.imageView);
       if (holer.imageView1!=null){
           Glide.with(context).load(bean.getIcon()).into(holer.imageView1);
           Glide.with(context).load(bean.getIcon()).into(holer.imageView2);
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Bean.DataBean bean=list.get(position);
        if (position%2==0){
            return TYPE_di;
        }else{
            return TYPE_de;
        }

    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private final TextView textView;
        private ImageView imageView,imageView1,imageView2;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);
            imageView=itemView.findViewById(R.id.imageview);
            imageView1=itemView.findViewById(R.id.imageview1);
            imageView2=itemView.findViewById(R.id.imageview2);
        }
    }
}
