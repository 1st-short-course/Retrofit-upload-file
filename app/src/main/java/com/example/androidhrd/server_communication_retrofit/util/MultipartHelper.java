package com.example.androidhrd.server_communication_retrofit.util;

import android.content.Context;
import android.net.Uri;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultipartHelper {

    public MultipartBody.Part convert(Context context, String fileName, String param){
        Uri uri=Uri.parse(fileName);
        File file= new File(uri.getPath());
       RequestBody requestBody=RequestBody.create(
                MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part part=MultipartBody.Part.createFormData(
                param,file.getName(),requestBody
        );

        return part;
    }
}
