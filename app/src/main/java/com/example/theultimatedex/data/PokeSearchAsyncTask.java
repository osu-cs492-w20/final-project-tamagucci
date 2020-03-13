package com.example.theultimatedex.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.theultimatedex.utils.NetworkUtils;
import com.example.theultimatedex.utils.PokeUtils;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

public class PokeSearchAsyncTask extends AsyncTask<String, Void, String> {

    private Callback mCallback;

    public interface Callback {
        void onSearchFinished(List<PokemonRepo> searchResults);
    }

    public PokeSearchAsyncTask(Callback callback) {
        Log.d("UltimateDex/PSAsyncTask", "Callback = " + callback);
        mCallback = callback;
    }


    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String searchResults = null;
        try {
            Log.d("UltimateDex/PSAsyncTask", "Doing in Background: doHttpGet of url " + url);
            searchResults = NetworkUtils.doHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("UltimateDex/PSAsyncTask", "Doing in Background: after doHttpGet of url " + url);
        return searchResults;
    }


    @Override
    protected void onPostExecute(String s) {
        List<PokemonRepo> searchResults = null;

        if (s != null) {

            searchResults = PokeUtils.parseSearchResults(s);
            Log.d("UltimateDex/PSAsyncTask", "Parsing Results" + s);
        }
        mCallback.onSearchFinished(searchResults);
    }
}
