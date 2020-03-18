package com.example.theultimatedex;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.GenerationRepo;
import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.data.SavedPokemonRepository;
import com.example.theultimatedex.data.TypeRepo;
import com.example.theultimatedex.data.savedPokemonNames;
import com.example.theultimatedex.utils.NetworkUtils;
import com.example.theultimatedex.utils.PokeUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PokeActivity extends AppCompatActivity implements PokeAdapter.pokeItemClickListener {
    private static final String TAG = PokeActivity.class.getSimpleName();

    private static final String POKE_RV = "POKE_RV";
    private static final String GEN_AC = "GEN_AC";
    private static final String TYP_AC = "TYP_AC";
    private static final String FAV_AC = "FAV_AC";

    HomeWatcher mHomeWatcher;

    private ArrayList<AsyncTask<String,Void,String>> asyncTasks;


    private ArrayList<PokemonRepo> mPokemonToShow;
    private String mActivityType;
    private String mTypNum;
    private String mGenNum;
    private String mFavNum;

    private RecyclerView mPokeRV;
    private ProgressBar mPokeProgress;
    private TextView mPokeError;
    private PokeAdapter mPokeAdapter;

    private SavedPokemonRepository mSavedPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate");

        // MUSIC COMMENT BLOCK START HERE
        /*

        // Bind Music Service Here
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        // To Watch for if Screen goes black or goes to other screen outside of app;
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();
        // MUSIC COMMENT BLOCK END HERE */


        asyncTasks = new ArrayList<AsyncTask<String,Void,String>>();
        setContentView(R.layout.poke_recycle_layout);
        // Begin creating new recycle-view to display the data.
        mPokeAdapter = new PokeAdapter(this);
        mPokeRV = findViewById(R.id.poke_RV);
        mPokeRV.setAdapter(mPokeAdapter);
        mPokeRV.setLayoutManager(new LinearLayoutManager(this));
        mPokeProgress = findViewById(R.id.Poke_pb_loading_indicator);
        mPokeError = findViewById(R.id.Poke_tv_loading_error_message);
        mPokemonToShow = new ArrayList<>();

        mSavedPokemon = new SavedPokemonRepository(getApplication());

        mTypNum = "0";
        mGenNum = "0";
        mFavNum = "0";
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
                loadTyp(url);
                break;
            case "FAV_AC":
                Log.d(TAG,"FAV_AC");
                loadFav();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // MUSIC COMMENT BLOCK START HERE
        /*

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
        // MUSIC COMMENT BLOCK END HERE */

        Log.d(TAG,"Prushka: SAFETY CONTROL ROD AXE MAN");
        int size = asyncTasks.size();
        for(int i = 0; i < size; i++) {
            asyncTasks.get(i).cancel(true);
            if(asyncTasks.get(i).isCancelled()) {
                Log.d(TAG,"Prushka: FA9 Successfully canceled async task!");
            }
            else {
                Log.d(TAG,"Prushka: FA9 Problem canceling async task!");
            }
        }
        asyncTasks.clear();
        Log.d(TAG,"Canceled " + size + " tasks!");
        Log.d(TAG,"Thank you for your cooperation and have a nice day!");
    }




    public void unloadFavoriteResults(int size, List<PokemonRepo> mREPO) {
        Log.d(TAG,"Prushka: size = " + size);
        for(int i = 0; i < size; i++) {
            PokemonRepo poke = mREPO.get(i);
            Log.d(TAG,"Prushka: poke at " + i + ": " + poke.name);
            String url = PokeUtils.buildPokeURL("pokemon/" + poke.name);
            loadPoke(url);
        }
    }

    public void unloadGenerationResults(List<GenerationRepo> generationItems) {
        GenerationRepo mGenRepo = generationItems.get(0);
        for(int i = 0; i < mGenRepo.pokemon_species.size(); i++) {
            GenerationRepo.pokemon_species poke = mGenRepo.pokemon_species.get(i);
            String url = PokeUtils.buildPokeURL("pokemon/" + poke.name);
            loadPoke(url);
        }
        new loadingIndicator().execute();
    }

    public void unloadTypeResults(List<TypeRepo> typeItems) {
        TypeRepo mTypRepo = typeItems.get(0);
        for(int i = 0; i < mTypRepo.pokemon.size(); i++) {
            TypeRepo.Pokem.PokeMon poke = mTypRepo.pokemon.get(i).pokemon;
            String url = PokeUtils.buildPokeURL("pokemon/" + poke.name);
            loadPoke(url);
        }
        new loadingIndicator().execute();
    }




    @Override
    public void onPokeItemClick(PokemonRepo pokeRepo) {
        Intent intent = new Intent(this,PokeItemDetailActivity.class);
        intent.putExtra(PokeItemDetailActivity.EXTRA_POKEMON_REPO,pokeRepo);
        startActivity(intent);
    }

    public void loadFav() {
        new FavLoad().execute();
    }

    public void loadGen(String url) {
        new GenLoad().execute(url);
    }

    public void loadTyp(String url) {
        new TypLoad().execute(url);
    }

    public void loadPoke(String url) {
        new PokeLoad().execute(url);
    }

    public class FavLoad extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            LiveData<List<savedPokemonNames>> mRepo = mSavedPokemon.getAllRepos();
            int size = mSavedPokemon.getRepoCount();
            //unloadFavoriteResults(size, mRepo.getValue());
            return "YAY!";
        }
    }


    public class GenLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: onPreExecute!");
            mPokeError.setVisibility(View.INVISIBLE);
            mPokeProgress.setVisibility(View.VISIBLE);
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
                mPokeError.setVisibility(View.VISIBLE);
                mPokeProgress.setVisibility(View.INVISIBLE);
                Log.d(TAG,"Prushka: Failure!");
            }
        }
    }


    public class TypLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: onPreExecute!");
            mPokeError.setVisibility(View.INVISIBLE);
            mPokeProgress.setVisibility(View.VISIBLE);
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
                ArrayList<TypeRepo> results = PokeUtils.parseTypeResults(s);
                unloadTypeResults(results);
                Log.d(TAG,"Prushka: Success!");
            } else {
                mPokeError.setVisibility(View.VISIBLE);
                mPokeProgress.setVisibility(View.INVISIBLE);
                Log.d(TAG,"Prushka: Failure!");
            }
        }
    }



    public class PokeLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: onPreExecute!");
            asyncTasks.add(this);
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
                if(results != null) {
                    mPokemonToShow.add(results.get(0));
                    mPokeAdapter.updateSearchResults(mPokemonToShow);
                    Log.d(TAG,"Prushka: Success!");
                }
                else {
                    mPokeError.setVisibility(View.VISIBLE);
                    mPokeProgress.setVisibility(View.INVISIBLE);
                    Log.d(TAG,"Prushka: Failure!");
                }
            } else {
                mPokeError.setVisibility(View.VISIBLE);
                mPokeProgress.setVisibility(View.INVISIBLE);
                Log.d(TAG,"Prushka: Failure!");
            }
            asyncTasks.remove(this);
        }
    }


    public class FaveLoad extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


    public class loadingIndicator extends AsyncTask<Void, Void, String> {
        boolean isFinished;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"Prushka: FA9 Loading started!");
            Log.d(TAG,"Prushka: FA9 Tasks remaining: " + asyncTasks.size());
            mPokeRV.setVisibility(View.INVISIBLE);
            mPokeProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG,"Prushka: FA9 Loading!");
            while(asyncTasks.size() > 0) {
                // Wait
                isFinished = false;
            }
            isFinished = true;
            return "DONE";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG,"Prushka: FA9 Loading complete!");
            mPokeRV.setVisibility(View.VISIBLE);
            mPokeProgress.setVisibility(View.INVISIBLE);
        }
    }


    // MUSIC COMMENT BLOCK START HERE
    /*

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    // *** CHECK OTHER onDestroy in PokeActivity

    // MUSIC COMMENT BLOCK END HERE */

}
