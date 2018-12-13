package com.example.dell.myapplication1212;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     private TextView text_name;
     private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inif();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    private void inif() {
        findViewById(R.id.but_liear).setOnClickListener(this);
        findViewById(R.id.but_grid).setOnClickListener(this);
        findViewById(R.id.but_stagged).setOnClickListener(this);
        findViewById(R.id.but_ds).setOnClickListener(this);
        text_name=findViewById(R.id.text_name);
        imageView=findViewById(R.id.image_d3);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();

         switch (v.getId()){
            /* case R.id.but_liear:
                 intent.setClass(this,LinearActivity.class);
                 break;*/
             case R.id.but_grid:
                 //多条目展示 删除 属性动画 都写在GridActivity页面
                 intent.setClass(this,GridActivity.class);
                 break;
        /*     case R.id.but_stagged:
                 intent.setClass(this,StaggedActivity.class);
                 break;*/
             case R.id.but_ds:
                 UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                 umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                     @Override
                     public void onStart(SHARE_MEDIA share_media) {

                     }

                     @Override
                     public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                         Log.i("TAG",map+"");
                         //获取名字
                         String name  = map.get("screen_name");
                         text_name.setText(name);
                         //获取头像
                         String imageUrl = map.get("profile_image_url");
                       //  imageView.setImageBitmap(imageUrl);


                     }

                     @Override
                     public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                     }

                     @Override
                     public void onCancel(SHARE_MEDIA share_media, int i) {

                     }
                 });


                 break;
                 default: break;
         }
        startActivity(intent);
    }
}
