package com.example.theultimatedex;

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

        mRepository.loadSearchResults(query);
    }


}
