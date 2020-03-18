package com.example.theultimatedex.data;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.theultimatedex.utils.PokeUtils;

import java.util.List;

public class PokeSearchRepository implements PokeSearchAsyncTask.Callback {

    private MutableLiveData<List<PokemonRepo>> mResults;

    // If we want to implement loading status
    private MutableLiveData<Status> mLoadingStatus;

    private String mCurrentQuery;

    public PokeSearchRepository() {
        mResults = new MutableLiveData<>();
        mResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);
    }

    public LiveData<List<PokemonRepo>> getSearchResults() {
        return mResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    @Override
    public void onSearchFinished(List<PokemonRepo> searchResults) {
        Log.d("UltimateDex/PSRepositor", "onSearchFinished - ");
        mResults.setValue(searchResults);
        if(searchResults != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        }
        else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, mCurrentQuery);
    }

    public void loadSearchResults(String query) {
        Log.d("UltimateDex/PSRepositor", "Loading " + query + " into PokeSearchAsyncTask.");
        if (shouldExecuteSearch(query)) {
            mCurrentQuery = query;

            String url = PokeUtils.buildPokeURL(query);

            mResults.setValue(null);
            Log.d("UltimateDex/PSRepositor", "Executing search with url: " + url);

            new PokeSearchAsyncTask(this).execute(url);
            Log.d("UltimateDex/PSRepositor", "***Executed.***");
        }
        else {
            Log.d("UltimateDex/Repository", "Using Cached Search Results");
        }


    }
}
