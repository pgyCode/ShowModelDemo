package com.danale.shixisheng2018.shower.model;

import com.danale.shixisheng2018.shower.bean.UserBean;
import com.danale.shixisheng2018.shower.message.UserInfoMsg;
import com.danale.shixisheng2018.shower.parent.Model;
import com.danale.shixisheng2018.shower.tool.App;
import com.danale.shixisheng2018.shower.tool.MyLocalObject;
import com.danale.shixisheng2018.shower.tool.NetVisitor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MineModel extends Model {

    public UserBean userBean;

    private static MineModel instance;

    public static MineModel getInstance() {
        if (instance == null){
            instance = new MineModel();
        }
        return instance;
    }

    public int netLogin(String id, String pass){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", id);
        jsonObject.addProperty("passWord", pass);
        System.out.println(jsonObject.toString());
        String temp = NetVisitor.postNormal("http://192.168.0.143:8080/myDemo/servlet/Login", jsonObject.toString());
        if (temp != null){
            UserInfoMsg msg = new Gson().fromJson(temp, UserInfoMsg.class);
            if (msg.code == App.NET_SUCCEED){
                userBean = msg.data;
                localSave();
            }
            return msg.code;
        } else {
            return App.NET_FAILURE;
        }
    }


    public void localSave(){
        MyLocalObject.saveObject("account", userBean);
    }
}
