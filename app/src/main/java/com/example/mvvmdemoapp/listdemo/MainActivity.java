package com.example.mvvmdemoapp.listdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmdemoapp.R;
import com.example.mvvmdemoapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PlaceListAdapter adapter;
    PlaceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        viewModel.init();

        adapter = new PlaceListAdapter(this, viewModel.listMutableLiveData.getValue());
        binding.setAdapter(adapter);


        binding.fabAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addNewItem();
            }
        });

        viewModel.getListMutableLiveData().observe(this, new Observer<List<PlaceModel>>() {
            @Override
            public void onChanged(List<PlaceModel> placeModels) {
                adapter.notifyDataSetChanged();
                binding.rcList.smoothScrollToPosition(placeModels.size());
            }
        });

        viewModel.getIsShowProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}