package com.example.dell.myapplication1212.adapter;

import android.animation.ObjectAnimator;
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
import com.example.dell.myapplication1212.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MyGrid extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserBean.DataBean> list;
    private Context context;
    private final int TYPE_TY1=0;
    private final int TYPE_TY2=TYPE_TY1+1;
  //删除接口
    public interface CilckLinear{
        void cilck(int position);
    }
    private CilckLinear cilckLinear;

    public void setCilckLinear(CilckLinear cilckLinear) {
        this.cilckLinear = cilckLinear;
    }
//属性动画接口
    public interface  ItemCilckLinear{
        void item(View view,int position);
}
    private ItemCilckLinear itemCilckLinear;

    public void setItemCilckLinear(ItemCilckLinear itemCilckLinear){
        this.itemCilckLinear=itemCilckLinear;
    }



    public MyGrid(Context context){
        this.context=context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.DataBean> lists) {
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }
    public void addList(List<UserBean.DataBean> lists) {
        list.addAll(lists);
        notifyDataSetChanged();
    }
 public UserBean.DataBean getitem(int position){
        return list.get(position);
 }
    @Override
    public int getItemViewType(int position) {
        if (getitem(position).idPan()){
            return TYPE_TY1;
        }else {
            return TYPE_TY2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==TYPE_TY1){
            View view=LayoutInflater.from(context).inflate(R.layout.type1,viewGroup,false);
            return new ViewHolder1(view);
        }else {
            View view=LayoutInflater.from(context).inflate(R.layout.type2,viewGroup,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        switch (type){
            case TYPE_TY1:
                ViewHolder1 holder1= (ViewHolder1) viewHolder;
                holder1.text.setText(getitem(i).getTitle());
                Glide.with(context).load(getitem(i).getThumbnail_pic_s()).into(holder1.imageView1);
                Glide.with(context).load(getitem(i).getThumbnail_pic_s02()).into(holder1.imageView2);
                Glide.with(context).load(getitem(i).getThumbnail_pic_s03()).into(holder1.imageView3);
                holder1.imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCilckLinear.item(v,i);
                    }
                });
                holder1.imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCilckLinear.item(v,i);
                    }
                });
                holder1.imageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCilckLinear.item(v,i);
                    }
                });
                break;
            case TYPE_TY2:
                 ViewHolder2 holder2= (ViewHolder2) viewHolder;
                 holder2.text1.setText(getitem(i).getTitle());
                 Glide.with(context).load(getitem(i).getThumbnail_pic_s()).into(holder2.typeimageView1);
                Glide.with(context).load(getitem(i).getThumbnail_pic_s02()).into(holder2.typeimageView2);
                holder2.typeimageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCilckLinear.item(v,i);
                    }
                });
                holder2.typeimageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCilckLinear.item(v,i);
                    }
                });
                 break;
                 default:
                    break;
        }


        //删除
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cilckLinear!=null){
                    cilckLinear.cilck(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageView imageView1,imageView2,imageView3;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.type1_text);
            imageView1=itemView.findViewById(R.id.type1_image1);
            imageView2=itemView.findViewById(R.id.type1_image2);
            imageView3=itemView.findViewById(R.id.type1_image3);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView text1;
        private ImageView typeimageView1,typeimageView2;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.type2_text);
            typeimageView1=itemView.findViewById(R.id.type2_image1);
            typeimageView2=itemView.findViewById(R.id.type2_image2);
        }
    }
  //删除方法
    public void  removeData(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
    //动画方法
    public void animator(View view,int position){
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,"alpha",1f,0f);
        alpha.setDuration(3000);
        alpha.setRepeatCount(0);
        alpha.start();
        notifyItemChanged(position);
    }
}
