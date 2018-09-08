package com.example.androidhrd.server_communication_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.androidhrd.server_communication_retrofit.data.remote.RetrofitRequestHelper;
import com.example.androidhrd.server_communication_retrofit.data.remote.ServiceGenerator;
import com.example.androidhrd.server_communication_retrofit.data.remote.callback.DataResponseCallback;
import com.example.androidhrd.server_communication_retrofit.data.remote.service.CategoryService;
import com.example.androidhrd.server_communication_retrofit.entity.BaseResponse;
import com.example.androidhrd.server_communication_retrofit.entity.Category;
import com.example.androidhrd.server_communication_retrofit.entity.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity
implements CategoryAdapter.CategoryCallback{

    private CategoryAdapter adapter;
    private RecyclerView rvCategory;
    private List<Category> categories =new ArrayList<>();
    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryService= ServiceGenerator.createService(CategoryService.class);

        rvCategory=findViewById(R.id.rvCategory);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CategoryAdapter(this,categories);
        rvCategory.setAdapter(adapter);

        //get data
        loadCategories();

    }

    private void loadCategories() {
        Call<CategoryResponse> call=categoryService.getAllCategories();
        new RetrofitRequestHelper<CategoryResponse>().execute(call, new DataResponseCallback<CategoryResponse>() {
            @Override
            public void onSuccess(CategoryResponse categoryResponse) {
                List<Category> categories=categoryResponse.getData();
                //bind data with adapter
                adapter.setCategories(categories);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onItemClickWithObject(Category category,int pos) {
        categories.remove(category);
        adapter.notifyItemRemoved(pos);
        new RetrofitRequestHelper<BaseResponse>().execute(
                categoryService.delete(category.getId()),
                new DataResponseCallback<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        Toast.makeText(MainActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                }
        );
    }
}
