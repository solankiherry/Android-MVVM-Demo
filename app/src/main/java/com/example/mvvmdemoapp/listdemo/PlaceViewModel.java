package com.example.mvvmdemoapp.listdemo;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PlaceViewModel extends ViewModel {
    MutableLiveData<List<PlaceModel>> listMutableLiveData;
    MutableLiveData<Boolean> isShowProgress = new MutableLiveData<>();

    public void init() {
        if (listMutableLiveData != null) {
            return;
        }

        PlaceModelRepo modelRepo = new PlaceModelRepo().getInstance();
        listMutableLiveData = modelRepo.getData();
        isShowProgress.setValue(false);
    }

    public LiveData<Boolean> getIsShowProgress() {
        return isShowProgress;
    }

    public MutableLiveData<List<PlaceModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void addNewItem() {
        isShowProgress.setValue(true);

        new AsyncTask<Void, Void, Void>() {
            
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                List<PlaceModel> list = listMutableLiveData.getValue();
                list.add(new PlaceModel("PlaceName " + list.size()));
                listMutableLiveData.postValue(list);
                isShowProgress.postValue(false);
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
    }
}