package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String POKE_RV = "POKE_RV";

    HomeWatcher mHomeWatcher;

    private TextView mSearch;
    private TextView mGenSearch;
    private TextView mTypeSearch;
    private TextView mAllPokemon;
    private TextView mFavePokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // MUSIC COMMENT BLOCK START HERE
        //*

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



        Log.d("UltimateDex/MainActivit", "Starting Activity");
        mSearch = findViewById(R.id.tv_search_select);
        mGenSearch = findViewById(R.id.tv_gen_select);
        mTypeSearch = findViewById(R.id.tv_type_select);
        mAllPokemon = findViewById(R.id.tv_all_select);
        mFavePokemon = findViewById(R.id.tv_fave_select);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });

        mGenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenSelect();
            }
        });

        mTypeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTypeSelect();
            }
        });

        mFavePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFavoritePokemon();
            }
        });

    }


    public void openSearch() {
        Log.d("UltimateDex/MainActivit", "Opening Search Activity");
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

    }

    public void openGenSelect() {
        Log.d("UltimateDex/MainActivit", "Opening Gen Activity");
        Intent intent = new Intent(this, GenActivity.class);
        startActivity(intent);
    }

    public void openTypeSelect() {
        Log.d("UltimateDex/MainActivit", "Opening Type Activity");
        Intent intent = new Intent(this, TypeActivity.class);
        startActivity(intent);
    }



    public void openFavoritePokemon() {
        Log.d("UltimateDex/MainActivit", "Opening Fave Pokemon Activity");
        Intent intent = new Intent(this, PokeActivity.class);
        intent.putExtra(POKE_RV,"FAV_AC");
        startActivity(intent);
    }


    // MUSIC COMMENT BLOCK START HERE
    //*

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
