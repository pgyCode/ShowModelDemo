package com.danale.shixisheng2018.shower.model;

import com.danale.shixisheng2018.shower.bean.RecordBean;
import com.danale.shixisheng2018.shower.bean.UserBean;
import com.danale.shixisheng2018.shower.message.UserInfoMsg;
import com.danale.shixisheng2018.shower.parent.Model;
import com.danale.shixisheng2018.shower.tool.App;
import com.danale.shixisheng2018.shower.tool.MyLocalObject;
import com.danale.shixisheng2018.shower.tool.NetVisitor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class AllModel extends Model {

    public List<RecordBean> recordBeans;

    private static AllModel instance;

    public static AllModel getInstance() {
        if (instance == null){
            instance = new AllModel();
        }
        return instance;
    }

    public int netFlush(){
        try {
            Thread.sleep(2000);
            recordBeans = new ArrayList<>();
            recordBeans.add(new RecordBean(0, "0号", "7:30", "8:00"));
            recordBeans.add(new RecordBean(1, "1号", "8:00", "8:30"));
            recordBeans.add(new RecordBean(2, "2号", "8:30", "9:00"));
            recordBeans.add(new RecordBean(3, "3号", "9:00", "9:30"));
            App.postHandle(App.HANDLE_ALL);
            return App.NET_SUCCEED;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return App.NET_FAILURE;
    }
}
