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
    void insert(PokemonRepo repo);

    @Delete
    void delete(PokemonRepo repo);

    @Query("SELECT count(*) FROM repos")
    int getRepoCount();

    @Query("SELECT * FROM repos")
    LiveData<List<PokemonRepo>> getAllRepos();

    @Query("SELECT * FROM repos WHERE id = :pokemonID LIMIT 1")
    LiveData<PokemonRepo> getRepoByID(String pokemonID);

}
