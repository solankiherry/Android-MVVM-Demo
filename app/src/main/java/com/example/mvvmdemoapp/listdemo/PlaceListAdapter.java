package com.example.mvvmdemoapp.listdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemoapp.BR;
import com.example.mvvmdemoapp.R;
import com.example.mvvmdemoapp.databinding.ItemPlaceBinding;

import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {
    private Context context;
    private List<PlaceModel> list;

    public PlaceListAdapter(Context context, List<PlaceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_place, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlaceModel model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemPlaceBinding binding;

        MyViewHolder(ItemPlaceBinding layoutBinding) {
            super(layoutBinding.getRoot());
            this.binding = layoutBinding;
        }

        public void bind(PlaceModel obj) {
            binding.setVariable(BR.model, obj);
            binding.executePendingBindings();
        }
    }
}