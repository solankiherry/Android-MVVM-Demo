package com.example.mvvmdemoapp.logindemo;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    public String email = "", password = "", emailHint = "", passwordHint = "";
    OnUserLoginListener loginListener;
    MutableLiveData<Boolean> isShowProgressBar = new MutableLiveData<>();

    public void init(UserModel userModel, OnUserLoginListener loginListener) {
        this.emailHint = userModel.emailHint;
        this.passwordHint = userModel.passwordHint;
        this.loginListener = loginListener;
        isShowProgressBar.setValue(false);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        if (TextUtils.isEmpty(email)) {
            loginListener.onFail("Please enter email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginListener.onFail("Enter valid email");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            loginListener.onFail("Please enter password");
            return false;
        } else if (password.length() < 5) {
            loginListener.onFail("Week password");
            return false;
        }
        return true;
    }


    public MutableLiveData<Boolean> getIsShowProgressBar() {
        return isShowProgressBar;
    }

    public void doLoginWithServer() {
        if (isValidate()) {
            isShowProgressBar.postValue(true);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    isShowProgressBar.postValue(false);
                    if (email.equals("admin@gmail.com") && password.equals("admin")) {
                        loginListener.onSuccess();
                    } else {
                        loginListener.onFail("Login was fail.");
                    }

                }

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
        } else isShowProgressBar.postValue(false);
    }
}