package com.example.theultimatedex.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemonNames")
public class savedPokemonNames {

    @PrimaryKey
    @NonNull
    public String name;
}
