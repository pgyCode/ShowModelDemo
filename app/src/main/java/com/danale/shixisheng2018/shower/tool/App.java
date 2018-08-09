package com.danale.shixisheng2018.shower.tool;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.danale.shixisheng2018.shower.bean.UserBean;
import com.danale.shixisheng2018.shower.model.AccountModel;
import com.danale.shixisheng2018.shower.model.AllModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App extends Application {

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HANDLE_ALL){
                AllModel.getInstance().actListeners();
            }
        }
    };

    public static final int HANDLE_ALL = 100000;
    public static final int HANDLE_MINE = 100001;

    public static void postHandle(int h){
        handler.sendEmptyMessage(h);
    }
    public static final int NET_SUCCEED = 2;
    public static final int NET_FAILURE = -1;

    public static Context context;

    public static ExecutorService postService;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        postService = Executors.newSingleThreadExecutor();
        AccountModel.getInstance().userBean = (UserBean) MyLocalObject.getObject("account");
    }



    public static void postRunnable(Runnable runnable){
        postService.execute(runnable);
    }
}
