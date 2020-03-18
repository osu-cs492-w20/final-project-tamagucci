package com.example.theultimatedex.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedPokemonDao {

    @Insert
    void insert(savedPokemonNames repo);

    @Delete
    void delete(savedPokemonNames repo);

    @Query("SELECT count(*) FROM pokemonNames")
    int getRepoCount();

    @Query("SELECT * FROM pokemonNames")
    LiveData<List<savedPokemonNames>> getAllRepos();

    @Query("SELECT * FROM pokemonNames WHERE name = :pokemonName LIMIT 1")
    LiveData<savedPokemonNames> getRepoByID(String pokemonName);

}
