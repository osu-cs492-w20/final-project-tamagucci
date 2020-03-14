package com.example.theultimatedex.utils;

import android.net.Uri;
import android.util.Log;

import com.example.theultimatedex.data.PokemonRepo;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PokeUtils {

    private final static String POKE_API_BASE_URL = "http://pokeapi.co/api/v2/pokemon";
    private final static String POKE_POKE_PARAM = "pokemon/";

    static class PokeSearchResults {

        ArrayList<PokemonRepo> items;
    }


    public static String buildPokeURL(String q) {

        // need to validate that whatever is passed gets sent to lowercase
        String query = q.toLowerCase();
        Log.d("UltimateDex/PokeUtils", "q = " + q + " | query = " + query);
        String url = Uri.parse(POKE_API_BASE_URL).buildUpon()
                .appendPath(query)
                .build()
                .toString();

        Log.d("UltimateDex/PokeUtils", "Returning with url: " + url);
        return url;
    }

    /*
    public static String buildIconURL(String icon) {
        return String.format(OWM_ICON_URL_FORMAT_STR, icon);
    }
     */
    public static String buildIconURL(String icon) {
        Log.d("UltimateDex/PokeUtils", "Returning with icon url: " + icon);
        return String.format(icon);
    }

    public static ArrayList<PokemonRepo> parseSearchResults(String json) {
        Gson gson = new Gson();
        Log.d("UltimateDex/Pokeutils","json length: " + json.length());
        //PokeSearchResults results = gson.fromJson(json, PokeSearchResults.class);
        Log.d("UltimateDex/Pokeut/json","json = \n" + json);
        //ArrayList<PokemonRepo> results = gson.fromJson(json, PokeSearchResults.class).items;

        PokemonRepo Pokemon = gson.fromJson(json, PokemonRepo.class);
        ArrayList<PokemonRepo> results = new ArrayList<PokemonRepo>();
        results.add(Pokemon);

        Log.d("UltimateDex/Pokeutils","parseSearchResults item 0 id");
        if (results != null){ // && results.items != null) {
            Log.d("UltimateDex/Pokeutils","results NOT NULL");
            return results;
        }
        else {
            Log.d("UltimateDex/Pokeutils","results IS NULL");
            return null;
        }
    }

}
