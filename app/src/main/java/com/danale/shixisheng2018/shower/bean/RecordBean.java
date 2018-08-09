package com.danale.shixisheng2018.shower.bean;

import com.danale.shixisheng2018.shower.parent.Bean;

public class RecordBean extends Bean {

    public int id;
    public String name;

    public String startTime;
    public String endTime;


    public RecordBean(int id, String name, String startTime, String endTime){
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
