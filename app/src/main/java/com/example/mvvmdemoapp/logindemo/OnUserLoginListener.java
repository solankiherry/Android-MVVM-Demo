package com.example.mvvmdemoapp.logindemo;

public interface OnUserLoginListener {
    void onSuccess();

    void onFail(String error);
}