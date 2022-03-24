package com.example.back.common;


public class ToJson {
    private int total;
    private Object list;

    public ToJson(int total, Object list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
