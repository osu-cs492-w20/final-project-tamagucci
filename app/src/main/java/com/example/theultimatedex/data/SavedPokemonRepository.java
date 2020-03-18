package com.example.theultimatedex.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedPokemonRepository {

    private SavedPokemonDao mDAO;

    public SavedPokemonRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDAO = db.savedPokemonDao();
    }

    public void insertSavedRepo(PokemonRepo repo) {
        new InsertAsyncTask(mDAO).execute(repo);
    }

    public void deleteSavedRepo(PokemonRepo repo) {
        new DeleteAsyncTask(mDAO).execute(repo);
    }

    public int getRepoCount() {
        return mDAO.getRepoCount();
    }

    public LiveData<List<PokemonRepo>> getAllRepos() {
        return mDAO.getAllRepos();
    }

    public LiveData<PokemonRepo> getRepoByID(String pokemonID) {
        return mDAO.getRepoByID(pokemonID);
    }



    private static class InsertAsyncTask extends AsyncTask<PokemonRepo, Void, Void> {
        private SavedPokemonDao mAsyncTaskDAO;
        InsertAsyncTask(SavedPokemonDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(PokemonRepo... PokemonRepos) {
            Log.d("UltimateDex/SavedPkRepo","Inserting "+ PokemonRepos[0].name +" into Database.");
            mAsyncTaskDAO.insert(PokemonRepos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<PokemonRepo, Void, Void> {
        private SavedPokemonDao mAsyncTaskDAO;
        DeleteAsyncTask(SavedPokemonDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(PokemonRepo... PokemonRepos) {
            Log.d("UltimateDex/SavedPkRepo","Deleting "+ PokemonRepos[0].name +" from Database.");
            mAsyncTaskDAO.delete(PokemonRepos[0]);
            return null;
        }
    }

}
