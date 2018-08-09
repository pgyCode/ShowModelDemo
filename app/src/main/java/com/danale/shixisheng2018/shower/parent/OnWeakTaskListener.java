package com.danale.shixisheng2018.shower.parent;

public interface OnWeakTaskListener<T> {

    void before();

    T middle();

    void after(T t);
}
