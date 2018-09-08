package com.example.androidhrd.server_communication_retrofit.data.remote.service;

import com.example.androidhrd.server_communication_retrofit.entity.BaseResponse;
import com.example.androidhrd.server_communication_retrofit.entity.Category;
import com.example.androidhrd.server_communication_retrofit.entity.CategoryResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("/api/v1/categories")
    Call<CategoryResponse> getAllCategories();

    @DELETE("/api/v1/categories/{id}/delete")
    Call<BaseResponse> delete(@Path("id") int id);
}
