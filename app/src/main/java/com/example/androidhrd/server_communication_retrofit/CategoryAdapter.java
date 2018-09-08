package com.example.androidhrd.server_communication_retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidhrd.server_communication_retrofit.data.remote.ServiceGenerator;
import com.example.androidhrd.server_communication_retrofit.entity.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    List<Category> categories;
    private  Context context;
    private CategoryCallback callback;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context=context;
        this.callback= (CategoryCallback) context;
    }
    public void setCategories(List<Category> categories){
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category= categories.get(position);
        holder.title.setText(category.getCate_name());
        //download image with http request
        Glide.with(context)
                .load(category.getIcon_name() ==null ?
                        R.drawable.camera : ServiceGenerator.BASE_URL+category.getIcon_name() )
                .into(holder.icon);

        holder.btnDelete.setOnClickListener(v->{
            callback.onItemClickWithObject(category,holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        ImageView btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            title=itemView.findViewById(R.id.title);
            btnDelete=itemView.findViewById(R.id.delete);
        }
    }

    public interface CategoryCallback{
        void onItemClickWithObject(Category category ,int position);
    }
}
