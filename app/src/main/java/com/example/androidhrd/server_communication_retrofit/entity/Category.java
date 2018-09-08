package com.example.androidhrd.server_communication_retrofit.entity;

import com.google.gson.annotations.SerializedName;

public class Category {
    private String icon_name;
    private String des;
    private String cate_name;
    private boolean status;
    private int id;

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "icon_name='" + icon_name + '\'' +
                ", des='" + des + '\'' +
                ", cate_name='" + cate_name + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
