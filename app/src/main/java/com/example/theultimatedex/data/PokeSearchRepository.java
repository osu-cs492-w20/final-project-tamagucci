package com.example.theultimatedex.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.theultimatedex.utils.PokeUtils;

import java.util.List;

public class PokeSearchRepository implements PokeSearchAsyncTask.Callback {

    private MutableLiveData<List<PokemonRepo>> mResults;

    // If we want to implement loading status
    //private MutableLiveData<Status> mLoadingStatus;

    public PokeSearchRepository() {
        mResults = new MutableLiveData<>();
        mResults.setValue(null);
    }

    public LiveData<List<PokemonRepo>> getSearchResults() {
        return mResults;
    }

    @Override
    public void onSearchFinished(List<PokemonRepo> searchResults) {
        mResults.setValue(searchResults);
    }

    public void loadSearchResults(String query) {

        String url = PokeUtils.buildPokeURL(query);
        Log.d("Kira Tag", "executing search with url: " + url);
        new PokeSearchAsyncTask(this).execute(url);

    }
}
