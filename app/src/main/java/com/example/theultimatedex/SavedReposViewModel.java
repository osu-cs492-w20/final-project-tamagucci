package com.example.theultimatedex;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.data.SavedPokemonRepository;
import com.example.theultimatedex.data.savedPokemonNames;

import java.util.List;

public class SavedReposViewModel extends AndroidViewModel {

    private SavedPokemonRepository mRepository;


    public SavedReposViewModel(Application application) {
        super(application);
        mRepository = new SavedPokemonRepository(application);
    }

    public void insertSavedRepo(savedPokemonNames repo) {


        mRepository.insertSavedRepo(repo);
    }

    public void deleteSavedRepo(savedPokemonNames repo) {
        mRepository.deleteSavedRepo(repo);
    }

    public LiveData<List<savedPokemonNames>> getAllRepos() {
        return mRepository.getAllRepos();
    }

    public LiveData<savedPokemonNames> getRepoByID(String fullName) {
        return mRepository.getRepoByID(fullName);
    }
}
