package com.example.theultimatedex;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.data.SavedPokemonRepository;

import java.util.List;

public class SavedReposViewModel extends AndroidViewModel {

    private SavedPokemonRepository mRepository;


    public SavedReposViewModel(Application application) {
        super(application);
        mRepository = new SavedPokemonRepository(application);
    }

    public void insertSavedRepo(PokemonRepo repo) {
        mRepository.insertSavedRepo(repo);
    }

    public void deleteSavedRepo(PokemonRepo repo) {
        mRepository.deleteSavedRepo(repo);
    }

    public LiveData<List<PokemonRepo>> getAllRepos() {
        return mRepository.getAllRepos();
    }

    public LiveData<PokemonRepo> getRepoByID(String fullName) {
        return mRepository.getRepoByID(fullName);
    }
}
