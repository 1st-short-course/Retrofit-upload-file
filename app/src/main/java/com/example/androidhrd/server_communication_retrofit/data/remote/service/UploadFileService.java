package com.example.androidhrd.server_communication_retrofit.data.remote.service;

import com.example.androidhrd.server_communication_retrofit.entity.UploadFileResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadFileService {

    @Multipart
    @POST("/api/v1/urls/upload/web-icon")
    Call<UploadFileResponse> upload(@Part MultipartBody.Part part);

}
