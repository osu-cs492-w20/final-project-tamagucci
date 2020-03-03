package com.example.theultimatedex.utils;

import android.net.Uri;
import android.util.Log;

import com.example.theultimatedex.data.PokemonRepo;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PokeUtils {

    private final static String POKE_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private final static String POKE_POKE_PARAM = "pokemon/";

    static class PokeSearchResults {

        ArrayList<PokemonRepo> items;
    }


    public static String buildPokeURL(String q) {

        // need to validate that whatever is passed gets sent to lowercase
        String query = q.toLowerCase();

        // need to validate that whatever is passed gets sent to lowercase
        String query = q.toLowerCase();

        String url = Uri.parse(POKE_API_BASE_URL).buildUpon()
                .appendPath(POKE_POKE_PARAM)
                .appendPath(query)
                .build()
                .toString();

        Log.d("Kira Tag", "Executing with url: " + url);
        return url;
    }


    public static ArrayList<PokemonRepo> parseSearchResults(String json) {
        Gson gson = new Gson();
        PokeSearchResults results = gson.fromJson(json, PokeSearchResults.class);

        if (results != null && results.items != null) {
            return results.items;
        }
        else {
            return null;
        }
    }

}
