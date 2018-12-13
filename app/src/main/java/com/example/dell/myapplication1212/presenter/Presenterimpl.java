package com.example.dell.myapplication1212.presenter;

import com.example.dell.myapplication1212.callback.Mycallback;
import com.example.dell.myapplication1212.model.Modelmpl;
import com.example.dell.myapplication1212.view.IView;

public class Presenterimpl implements Presenter{
    private IView iView;
    private Modelmpl modelmpl;

    public Presenterimpl(IView iView) {
        this.iView = iView;
        modelmpl=new Modelmpl();
    }

    @Override
    public void setRequestData(String path, Class clazz) {
        modelmpl.setRequestData(path, clazz, new Mycallback() {
            @Override
            public void setData(Object data) {
                iView.setData(data);
            }
        });
    }
    //销毁
       public  void del(){
        if (iView!=null){
            iView=null;
        }
        if (modelmpl!=null){
            modelmpl=null;
        }
       }

}
