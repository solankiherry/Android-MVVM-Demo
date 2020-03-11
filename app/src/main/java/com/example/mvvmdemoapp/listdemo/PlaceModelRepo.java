package com.example.mvvmdemoapp.listdemo;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class PlaceModelRepo {
    private List<PlaceModel> list = new ArrayList<>();
    private PlaceModelRepo instance;

    public PlaceModelRepo getInstance() {
        if (instance == null) {
            instance = new PlaceModelRepo();
        }
        return instance;
    }

    public MutableLiveData<List<PlaceModel>> getData() {
        getListDataFromServer();

        MutableLiveData<List<PlaceModel>> data = new MutableLiveData<>();
        data.setValue(list);
        return data;
    }

    private List<PlaceModel> getListDataFromServer() {
        for (int i = 0; i < 20; i++) {
            list.add(new PlaceModel("PlaceName " + i));
        }
        return list;
    }
}