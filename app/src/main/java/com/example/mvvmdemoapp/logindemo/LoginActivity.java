package com.example.mvvmdemoapp.logindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmdemoapp.R;
import com.example.mvvmdemoapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements UserLoginClick, OnUserLoginListener {
    ActivityLoginBinding binding;
    UserViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.init(new UserModel("Please enter email", "Please enter password"), this);
        binding.setViewModel(viewModel);

        binding.setUserLoginEvent(this);

        viewModel.getIsShowProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    binding.progressBar.setVisibility(View.VISIBLE);
                else binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onButtonClick() {
        viewModel.doLoginWithServer();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }
}