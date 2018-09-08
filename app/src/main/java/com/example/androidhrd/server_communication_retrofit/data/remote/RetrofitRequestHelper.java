package com.example.androidhrd.server_communication_retrofit.data.remote;

import com.example.androidhrd.server_communication_retrofit.data.remote.callback.DataResponseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRequestHelper <T> {

    public void execute(Call<T> call, final DataResponseCallback<T> callback){
        //call.execute();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(callback!=null)
                    callback.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if(callback!=null)
                    callback.onFailure(t);
            }
        });


    }
}
