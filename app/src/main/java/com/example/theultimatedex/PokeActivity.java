package com.example.theultimatedex;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.GenerationRepo;
import com.example.theultimatedex.data.PokeSearchAsyncTask;
import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.utils.NetworkUtils;
import com.example.theultimatedex.utils.PokeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokeActivity extends AppCompatActivity implements PokeAdapter.pokeItemClickListener {
    private static final String TAG = PokeActivity.class.getSimpleName();

    private static final String POKE_RV = "POKE_RV";
    private static final String GEN_AC = "GEN_AC";
    private static final String TYP_AC = "TYP_AC";


    private ArrayList<PokemonRepo> mPokemonToShow;
    private String mActivityType;
    private String mTypNum;
    private String mGenNum;

    private RecyclerView mPokeRV;
    private ProgressBar mPokeProgress;
    private TextView mPokeError;
    private PokeAdapter mPokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate");
        setContentView(R.layout.poke_recycle_layout);
        // Begin creating new recycle-view to display the data.
        mPokeAdapter = new PokeAdapter(this);
        mPokeRV = findViewById(R.id.poke_RV);
        mPokeRV.setAdapter(mPokeAdapter);
        mPokeRV.setLayoutManager(new LinearLayoutManager(this));
        //mPokeProgress = findViewById(R.id.);
        //mPokeError = findViewById();
        mPokemonToShow = new ArrayList<>();

        mTypNum = "0";
        mGenNum = "0";
        // Determine what type of activity this is.
        mActivityType = getIntent().getStringExtra(POKE_RV);
        // Fill out appropriate forms for the type of activity.
        String url = null;
        switch(mActivityType) {
            case "GEN_AC":
                mGenNum = getIntent().getStringExtra(GEN_AC);
                Log.d(TAG, "GEN_AC");
                // Generation API call here.
                url = PokeUtils.buildPokeURL("generation/" + mGenNum);
                loadGen(url);
                break;
            case "TYP_AC":
                mTypNum = getIntent().getStringExtra(TYP_AC);
                Log.d(TAG, "TYP_AC");
                // Type API call here.
                url = PokeUtils.buildPokeURL("type/" + mTypNum);
                //loadPoke(url);
                break;
            default:
                break;
        }
    }



    public void unloadGenerationResults(List<GenerationRepo> generationItems) {
        GenerationRepo mGenRepo = generationItems.get(0);
        for(int i = 0; i < mGenRepo.pokemon_species.size(); i++) {
            GenerationRepo.pokemon_species poke = mGenRepo.pokemon_species.get(i);
            String url = PokeUtils.buildPokeURL("pokemon/" + poke.name);
            loadPoke(url);
        }
    }




    @Override
    public void onPokeItemClick(PokemonRepo pokeRepo) {
        Intent intent = new Intent(this,PokeItemDetailActivity.class);
        intent.putExtra("POKE_DT","HI!");
        startActivity(intent);
    }

    public void loadGen(String url) {
        new GenLoad().execute(url);
    }

    public void loadPoke(String url) {
        new PokeLoad().execute(url);
    }


    public class GenLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: onPreExecute!");
            //mPokeError.setVisibility(View.INVISIBLE);
            //mPokeProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG,"Prushka: Execute!");
            String url = strings[0];
            String pokeRes = null;
            try {
                pokeRes = NetworkUtils.doHttpGet(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG,"Prushka: results: " + pokeRes);
            return pokeRes;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG,"Prushka: onPostExecute!");

            if (s != null) {
                ArrayList<GenerationRepo> results = PokeUtils.parseGenerationResults(s);
                unloadGenerationResults(results);
                Log.d(TAG,"Prushka: Success!");
            } else {
                //mPokeError.setVisibility(View.VISIBLE);
                //mPokeProgress.setVisibility(View.INVISIBLE);
                Log.d(TAG,"Prushka: Failure!");
            }
        }
    }



    public class PokeLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: onPreExecute!");
            //mPokeError.setVisibility(View.INVISIBLE);
            //mPokeProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG,"Prushka: Execute!");
            String url = strings[0];
            String pokeRes = null;
            try {
                pokeRes = NetworkUtils.doHttpGet(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG,"Prushka: results: " + pokeRes);
            return pokeRes;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG,"Prushka: onPostExecute!");

            if (s != null) {
                ArrayList<PokemonRepo> results = PokeUtils.parseSearchResults(s);
                mPokemonToShow.add(results.get(0));
                mPokeAdapter.updateSearchResults(mPokemonToShow);
                Log.d(TAG,"Prushka: Success!");
            } else {
                //mPokeError.setVisibility(View.VISIBLE);
                //mPokeProgress.setVisibility(View.INVISIBLE);
                Log.d(TAG,"Prushka: Failure!");
            }
        }
    }

}
