package com.refugeye;

import java.util.Arrays;
import java.util.List;

public class Picto {

    public int resId;
    public List<String> names;

    public Picto(int resId) {
        this.resId = resId;
    }

    public Picto(int resId, String[] namesArray) {
        this.resId = resId;
        this.names = Arrays.asList(namesArray);
    }
    public Picto(int resId, List<String> names) {
        this.resId = resId;
        this.names = names;
    }
}
