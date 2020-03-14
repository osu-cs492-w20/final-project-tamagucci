package com.example.theultimatedex;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.theultimatedex.data.PokeSearchRepository;
import com.example.theultimatedex.data.PokemonRepo;

import java.util.List;

public class PokeSearchViewModel extends ViewModel {

    private PokeSearchRepository mRepository;
    private LiveData<List<PokemonRepo>> mResults;

    // If we want to implement a loading status
    //private LiveData<Status> mLoadingStatus;

    public PokeSearchViewModel() {
        mRepository = new PokeSearchRepository();
        mResults = mRepository.getSearchResults();
    }

    public void loadResults(String query) {
        Log.d("UltimateDex/PSViewModel", "Passing" + query + " to loadSearchResults in PokeSearchRepository");
        mRepository.loadSearchResults("pokemon/" + query);
    }

    public LiveData<List<PokemonRepo>> getResults() {
        return mResults;
    }




}
