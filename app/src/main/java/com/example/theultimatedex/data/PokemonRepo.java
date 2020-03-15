package com.example.theultimatedex.data;

import android.icu.lang.UProperty;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonRepo implements Serializable {

    public String id;
    public String name;
    
    //@SerializedName("pokemon_species")
    //public String species;


    //public ArrayList<BaseStats> stats;

    // Info to retrieve from API of each pokemon
    // species {} -- actually no there's just a place for name
    //      - name
    // sprites {}
    //      - front_default
    // stats [ {} ] ( for each stat )
    //      - base stat
    //      - stat {}
    //          - name
    // types [ {} ] ( for each potential type )
    //      - type {}
    //          - name
}
