package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GenActivity extends AppCompatActivity {
    private static final String TAG = GenActivity.class.getSimpleName();
    private static final String POKE_RV = "POKE_RV";
    private static final String GEN_AC = "GEN_AC";

    private TextView mGen1;
    private TextView mGen2;
    private TextView mGen3;
    private TextView mGen4;
    private TextView mGen5;
    private TextView mGen6;
    private TextView mGen7;
    private TextView mGen8;

    HomeWatcher mHomeWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: GenActivity onCreate");


        // MUSIC COMMENT BLOCK START HERE /*

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


        setContentView(R.layout.activity_gen);
        mGen1 = findViewById(R.id.tv_gen1_select);
        mGen2 = findViewById(R.id.tv_gen2_select);
        mGen3 = findViewById(R.id.tv_gen3_select);
        mGen4 = findViewById(R.id.tv_gen4_select);
        mGen5 = findViewById(R.id.tv_gen5_select);
        mGen6 = findViewById(R.id.tv_gen6_select);
        mGen7 = findViewById(R.id.tv_gen7_select);
        mGen8 = findViewById(R.id.tv_gen8_select);

        mGen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 1 selected!");
                showPokeRV(1);
            }
        });
        mGen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 2 selected!");
                showPokeRV(2);
            }
        });
        mGen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 3 selected!");
                showPokeRV(3);
            }
        });
        mGen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 4 selected!");
                showPokeRV(4);
            }
        });
        mGen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 5 selected!");
                showPokeRV(5);
            }
        });
        mGen6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 6 selected!");
                showPokeRV(6);
            }
        });
    }
    public void showPokeRV(Integer genNum) {
        Log.d(TAG,"Prushka: showPokeRV!");
        Intent intent = new Intent(this, PokeActivity.class);
        Bundle extras = new Bundle();
        // Add the Extras to the bundle.
        extras.putString(POKE_RV,"GEN_AC");
        extras.putString(GEN_AC, genNum.toString());
        // Put the extras.
        intent.putExtras(extras);
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
