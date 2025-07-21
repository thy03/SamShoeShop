package com.example.samshoeshop.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import com.example.samshoeshop.repository.ShoeRepository;

import com.example.samshoeshop.model.Shoe;

public class ShoeViewModel extends ViewModel {
    private MutableLiveData<List<Shoe>> shoeList = new MutableLiveData<>();
    private ShoeRepository repository = new ShoeRepository();

    public LiveData<List<Shoe>> getShoeList() {
        return shoeList;
    }

    public void loadAvailableShoes() {
        repository.getAvailableShoes(shoes -> shoeList.setValue(shoes));
    }
}

