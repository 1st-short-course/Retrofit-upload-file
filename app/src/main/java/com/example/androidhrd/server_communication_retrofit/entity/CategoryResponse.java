package com.example.androidhrd.server_communication_retrofit.entity;

import java.util.List;

public class CategoryResponse extends BaseResponse {

    private List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "data=" + data +
                '}';
    }
}
