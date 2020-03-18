package com.example.theultimatedex;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.data.SavedPokemonRepository;
import com.example.theultimatedex.data.savedPokemonNames;
import com.example.theultimatedex.utils.PokeUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;

public class PokeItemDetailActivity extends AppCompatActivity {
    private static final String TAG = PokeItemDetailActivity.class.getSimpleName();

    private static final String POKE_DT = "POKE_DT";

    public static final String EXTRA_POKEMON_REPO = "PokemonRepo";
    private PokemonRepo mRepo;
    private boolean mIsSaved = false;
    HomeWatcher mHomeWatcher;

    private SavedReposViewModel mViewModel;

    private String mPokeItemString;

    private TextView repoIDTV;
    private ImageView mPokemonSpriteIV;
    private TextView mPokemonNameTV;
    private TextView mPokemonDescTV;
    private TextView mPokemonType1;
    private TextView mPokemonType2;

    /* Stats */
    private TextView mPokemonSpeedStat;
    private TextView mPokemonSpecialDefStat;
    private TextView mPokemonSpecialAttStat;
    private TextView mPokemonDefenseStat;
    private TextView mPokemonAttackStat;
    private TextView mPokemonHPStat;


    savedPokemonNames PokeName;
    private SavedPokemonRepository mSavedPokemon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate!");
        setContentView(R.layout.poke_detail);

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

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedReposViewModel.class);

        repoIDTV = findViewById(R.id.poke_detail_num);
        mPokemonSpriteIV = findViewById(R.id.poke_detail_picture);
        mPokemonNameTV = findViewById(R.id.poke_detail_name);
        mPokemonDescTV = findViewById(R.id.poke_detail_desc);
        mPokemonType1 = findViewById(R.id.poke_detail_type1);
        mPokemonType2 = findViewById(R.id.poke_detail_type2);
        mPokemonType2.setVisibility(View.INVISIBLE);

        mPokemonSpeedStat = findViewById(R.id.poke_detail_stat_speed);
        mPokemonSpecialDefStat = findViewById(R.id.poke_detail_stat_special_defense);
        mPokemonSpecialAttStat = findViewById(R.id.poke_detail_stat_special_attack);
        mPokemonDefenseStat = findViewById(R.id.poke_detail_stat_defense);
        mPokemonAttackStat = findViewById(R.id.poke_detail_stat_attack);
        mPokemonHPStat = findViewById(R.id.poke_detail_stat_HP);


        PokeName = new savedPokemonNames();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_POKEMON_REPO)) {
            Log.d(TAG,"Prushka: Intent not null and has Extra!");
            mRepo = (PokemonRepo)intent.getSerializableExtra(EXTRA_POKEMON_REPO);
            fillInLayout(mRepo);
        }

        final ImageView addPokemonIV = findViewById(R.id.iv_repo_add_pokemon);
        addPokemonIV.setOnClickListener(new View.OnClickListener() {

/*
            List<savedPokemonNames> savedPokemonNames = mSavedPokemon.getAllRepos().observe(this, new Observer<List<savedPokemonNames>>() {
                @Override
                public void onChanged(List<savedPokemonNames> savedPokemonNames) {
                    ArrayList<String> mPokemonSaved = new ArrayList<>();
                    for(Integer i = 0; i < savedPokemonNames.size(); i++) {
                        mPokemonSaved.add(savedPokemonNames.get(i).name);
                        Log.d(TAG,"Prushka: name = ");
                    }
                    handleSave(savedPokemonNames.size(),mPokemonSaved);
                }
            });

 */


            @Override
            public void onClick(View v) {


                if (mRepo != null) {
                    if (!mIsSaved) {
                        Log.d("UltimateDex/PokeItemDet", "Adding Pokemon to Saved: " + mRepo.name);
                        toastFavorited(mRepo.name);
                        mIsSaved = true;
                        PokeName.name = mRepo.name;
                        mViewModel.insertSavedRepo(PokeName);
                    } else {
                        Log.d("UltimateDex/PokeItemDet", "Removing Pokemon from Saved: "+ mRepo.name);
                        toastUnfavorited(mRepo.name);
                        mIsSaved = false;
                        PokeName.name = mRepo.name;
                        mViewModel.deleteSavedRepo(PokeName);
                    }
                }



/*
                if (mIsSaved == true) {
                    addPokemonIV.setImageResource(R.drawable.ic_action_remove);
                } else {
                    addPokemonIV.setImageResource(R.drawable.ic_action_add);
                }

 */

            }
        });




        mViewModel.getRepoByID(PokeName.name).observe(this, new Observer<savedPokemonNames>() {
            @Override
            public void onChanged(savedPokemonNames repo) {
                if (mRepo.name != PokeName.name) {
                    Log.d("UltimateDex/PokeItemDet", "Pokemon is Saved.");
                    mIsSaved = true;
                    addPokemonIV.setImageResource(R.drawable.ic_action_remove);
                } else {
                    Log.d("UltimateDex/PokeItemDet", "Pokemon is Not Saved.");
                    mIsSaved = false;
                    addPokemonIV.setImageResource(R.drawable.ic_action_add);
                }
            }
        });
        





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pokemon_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                sharePokemon();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sharePokemon() {
        if (mRepo != null) {
            String pokemonName = mRepo.name.substring(0, 1).toUpperCase() + mRepo.name.substring(1).toLowerCase();
            String pokemonNumber = "#" + PadLeft(mRepo.id,'0',3);
            String shareText = pokemonName + " is " + pokemonNumber + " in the Pokedex!";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            shareIntent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(shareIntent, null);
            startActivity(chooserIntent);
        }
    }

    private void fillInLayout(PokemonRepo pokemonRepo) {

        //repoIDTV.setText(mRepo.id);
        String pokemonNumber = "No. " + PadLeft(pokemonRepo.id,'0',3);

        repoIDTV.setText(pokemonNumber);

        String pokemonName = mRepo.name.substring(0, 1).toUpperCase() + mRepo.name.substring(1).toLowerCase();
        mPokemonNameTV.setText(pokemonName);
        if(mRepo.types != null) {
            if(mRepo.types.size() > 0) {
                String type1 = "Type 1: " + mRepo.types.get(0).type.name.substring(0, 1).toUpperCase() + mRepo.types.get(0).type.name.substring(1).toLowerCase();
                mPokemonType1.setText(type1);
            }
            if(mRepo.types.size() > 1) {
                String type2 = "Type 2: " + mRepo.types.get(1).type.name.substring(0, 1).toUpperCase() + mRepo.types.get(1).type.name.substring(1).toLowerCase();
                mPokemonType2.setVisibility(View.VISIBLE);
                mPokemonType2.setText(type2);
            }
        }

        String speedStatText = "Speed: " + pokemonRepo.stats.get(0).base_stat;
        mPokemonSpeedStat.setText(speedStatText);
        String sdStatText = "Special Defense: " + pokemonRepo.stats.get(1).base_stat;
        mPokemonSpecialDefStat.setText(sdStatText);
        String saStatText = "Special Attack: " + pokemonRepo.stats.get(2).base_stat;
        mPokemonSpecialAttStat.setText(saStatText);
        String defStatText = "Defense: " + pokemonRepo.stats.get(3).base_stat;
        mPokemonDefenseStat.setText(defStatText);
        String attStatText = "Attack: " + pokemonRepo.stats.get(4).base_stat;
        mPokemonAttackStat.setText(attStatText);
        String hpStatText = "HP: " + pokemonRepo.stats.get(5).base_stat;

        //for (int i = 0; i < pokemonRepo.stats.size())


        mPokemonDescTV.setText("This portion of UltimateDex remains under construction.\n" +
                "In order to show that this section of the Pokemon Entry was to fill out the Pokemon's Description via a scroll box\n" +
                "Here are the lyrics to the Pokemon Theme Song!\n" +
                "\n" +
                "I want to be the very best,\n" +
                "Like no one ever was\n" +
                "To catch them is my real test,\n" +
                "To train them is my cause.\n" +
                "I will travel across the land, \n" +
                "searching far and wide...\n" +
                "Each Pokemon to understand\n" +
                "The power that's inside!\n" +
                "Pokemon!\n" +
                "Gotta catch em' all!\n" +
                "It's you and me,\n" +
                "I know it's my destiny!\n" +
                "Pokemon!\n" +
                "Oh, you're my best friend,\n" +
                "In a world we must defend!\n" +
                "Pokemon!\n" +
                "Gotta catch em' all\n" +
                "A heart so true,\n" +
                "Our courage will pull us through!\n" +
                "You teach me and I'll teach you,\n" +
                "Po-ke-mon!");

        String iconURL = PokeUtils.buildIconURL(pokemonRepo.sprites.front_default);
        Glide.with(this).load(iconURL).into(mPokemonSpriteIV);
    }

    String PadLeft(String input, Character PadChar, int ToLength){
        int LenDiff = ToLength - input.length();
        String output = "";
        if (LenDiff > 0) {
            for(int i=0; i < LenDiff;i++){
                output += PadChar;
            }
        }
        output += input;
        return output;
    }

    public void toastFavorited(String name) {
        Toast.makeText(this, name + " Favorited!", Toast.LENGTH_LONG).show();
    }
    public void toastUnfavorited(String name) {
        Toast.makeText(this, name + " Unfavorited!", Toast.LENGTH_LONG).show();
    }

    public void handleSave(int size, List<String> mREPO) {
        for (int i = 0; i < mREPO.size(); i++) {
            if (mREPO.get(i) == mRepo.name) {
                // Pokemon is already saved
                mIsSaved = false;
                toastFavorited(mRepo.name);
                PokeName.name = mRepo.name;
                mViewModel.deleteSavedRepo(PokeName);
            }
            else {
                Log.d("UltimateDex/PokeItemDet", "Adding Pokemon to Saved: " + mRepo.name);
                mIsSaved = true;
                toastUnfavorited(mRepo.name);
                PokeName.name = mRepo.name;
                mViewModel.insertSavedRepo(PokeName);
            }
        }
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
