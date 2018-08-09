package com.danale.shixisheng2018.shower.parent;

import java.util.LinkedList;
import java.util.List;

public class Model {

    public List<OnModelChangeListener> listeners;

    public Model() {
        listeners = new LinkedList<>();
    }

    public void actListeners() {
        for (int i = 0; i < listeners.size(); i++) {
            try {
                listeners.get(i).onChange();
            } catch (Exception e) {
                listeners.remove(listeners.get(i));
            }
        }
    }
}

