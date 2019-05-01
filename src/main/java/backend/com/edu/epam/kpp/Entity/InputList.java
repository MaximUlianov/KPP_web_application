package backend.com.edu.epam.kpp.Entity;

import backend.com.edu.epam.kpp.cache.InputParam;


import java.util.ArrayList;

public class InputList {
    private ArrayList<InputParam> params;

    public InputList() {
        this.params = new ArrayList<>();

    }

    public ArrayList<InputParam> getParams() {
        return params;
    }

    public void setParams(ArrayList<InputParam> params) {
        this.params = params;
    }
}
