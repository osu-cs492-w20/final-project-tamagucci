package com.example.theultimatedex.data;

import android.app.Application;
import android.os.AsyncTask;

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
            mAsyncTaskDAO.delete(PokemonRepos[0]);
            return null;
        }
    }

}
