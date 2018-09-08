package com.example.androidhrd.server_communication_retrofit.data.remote.callback;

public interface DataResponseCallback <T> {
    void onSuccess( T t );
    void onFailure(Throwable t);
}
