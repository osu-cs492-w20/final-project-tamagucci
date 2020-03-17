package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.data.Status;
import com.example.theultimatedex.utils.PokeUtils;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonItemClickListener {

    private EditText mSearchBoxET;
    private PokeSearchViewModel mViewModel;
    private ImageView mPokeImage;

    private RecyclerView mPokemonItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mErrorMessageTV;

    private PokemonAdapter mPokemonAdapter;
    private Context context;

    HomeWatcher mHomeWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        // MUSIC COMMENT BLOCK START HERE /*

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

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


        Log.d("UltimateDex/SearchActiv", "In Search Activity");
        mSearchBoxET = findViewById(R.id.et_search_box);



        //mPokeImage = findViewById(R.id.pokemon_image);
        mPokemonItemsRV = findViewById(R.id.rv_pokemon_items);

        mPokemonAdapter = new PokemonAdapter(this);
        mPokemonItemsRV.setAdapter(mPokemonAdapter);
        mPokemonItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mPokemonItemsRV.setHasFixedSize(true);
        context = getApplicationContext();

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        mErrorMessageTV = findViewById(R.id.tv_loading_error_message);


        // why not this? -- this now works when using the new vvvvvvvvvvvvvvvvvvvvv
        //mViewModel = new ViewModelProvider(this).get(PokeSearchViewModel.class);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(PokeSearchViewModel.class);
        //mViewModel = new ViewModelProvider(context).get(PokeSearchViewModel.class);


        mViewModel.getResults().observe(this, new Observer<List<PokemonRepo>>() {
            @Override
            public void onChanged(List<PokemonRepo> pokemonRepos) {
                mPokemonAdapter.updateSearchResults(pokemonRepos);
            }
        });

        mViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                }
                /*
                else if (status == Status.SUCCESS) {
                    Log.d("SPECIAL TEST", "Success!!");
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPokemonItemsRV.setVisibility(View.VISIBLE);
                    mErrorMessageTV.setVisibility(View.INVISIBLE);
                }
                else {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPokemonItemsRV.setVisibility(View.INVISIBLE);
                    mErrorMessageTV.setVisibility(View.VISIBLE);
                }

                 */
                else if(status == Status.ERROR) {
                    Log.d("SPECIAL TEST", "Error!!");
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPokemonItemsRV.setVisibility(View.INVISIBLE);
                    mErrorMessageTV.setVisibility(View.VISIBLE);
                }
                else {
                    Log.d("SPECIAL TEST", "Success!!");
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPokemonItemsRV.setVisibility(View.VISIBLE);
                    mErrorMessageTV.setVisibility(View.INVISIBLE);
                }
            }
        });



        Button GOButton = findViewById(R.id.btn_search);
        GOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doPokeSearch(searchQuery);
                }
            }
        });
    }

    private void doPokeSearch(String searchQuery) {


        //String url = PokeUtils.buildPokeURL(searchQuery);
        Log.d("UltimateDex/SearchActiv", "Searching For: " + searchQuery);
        mViewModel.loadResults(searchQuery);

    }

    @Override
    public void onPokemonItemClick(PokemonRepo pokemonRepo) {


        // Take to new activit
        Intent intent = new Intent(this, PokeItemDetailActivity.class);
        intent.putExtra(PokeItemDetailActivity.EXTRA_POKEMON_REPO, pokemonRepo);
        startActivity(intent);


    }

    // MUSIC COMMENT BLOCK START HERE /*

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
                Scon,Context.BIND_AUTO_CREATE);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }
    // MUSIC COMMENT BLOCK END HERE */


}
