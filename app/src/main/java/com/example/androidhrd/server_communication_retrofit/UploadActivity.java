package com.example.androidhrd.server_communication_retrofit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidhrd.server_communication_retrofit.data.remote.RetrofitRequestHelper;
import com.example.androidhrd.server_communication_retrofit.data.remote.ServiceGenerator;
import com.example.androidhrd.server_communication_retrofit.data.remote.callback.DataResponseCallback;
import com.example.androidhrd.server_communication_retrofit.data.remote.service.UploadFileService;
import com.example.androidhrd.server_communication_retrofit.entity.UploadFileResponse;
import com.example.androidhrd.server_communication_retrofit.util.CheckRuntimePermission;
import com.example.androidhrd.server_communication_retrofit.util.MultipartHelper;

import okhttp3.MultipartBody;

public class UploadActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnUpload;
    int requestCode=1;
    String filePath;

    UploadFileService uploadFileService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        new CheckRuntimePermission().grantWriteExternalStorage(this);
        uploadFileService= ServiceGenerator.createService(UploadFileService.class);
        imageView=findViewById(R.id.image);
        btnUpload=findViewById(R.id.upload);
        imageView.setOnClickListener(v->{
            Intent intent =new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    );
            intent.setType("image/*");
            startActivityForResult(intent,requestCode);
        });

        btnUpload.setOnClickListener(v->{
            uploadFile(filePath);

        });
    }

    private void uploadFile(String file){
        MultipartBody.Part part=new MultipartHelper().convert(this,file,"file");
        new RetrofitRequestHelper<UploadFileResponse>().execute(
                uploadFileService.upload(part),
                new DataResponseCallback<UploadFileResponse>() {
                    @Override
                    public void onSuccess(UploadFileResponse uploadFileResponse) {
                        Log.e(TAG, "onSuccess: "+uploadFileResponse.getData() );
                        Toast.makeText(UploadActivity.this, uploadFileResponse.getData(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "onFailure: "+ t.toString() );
                    }
                }
        );
    }

    private static final String TAG = "UploadActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==this.requestCode && resultCode==RESULT_OK){
            Uri uri=data.getData();
            String[] columnIndex={MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(
                    uri,columnIndex,null, null, null
            );

            cursor.moveToFirst();
            int index =cursor.getColumnIndex(columnIndex[0]);
            filePath=cursor.getString(index);

            //image to image view
            imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));

        }
    }
}
