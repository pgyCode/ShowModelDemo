package com.danale.shixisheng2018.shower.parent;

import com.google.gson.Gson;

public abstract class Bean {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
