package com.example.theultimatedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.utils.PokeUtils;

import org.w3c.dom.Text;

public class PokeItemDetailActivity extends AppCompatActivity {
    private static final String TAG = PokeItemDetailActivity.class.getSimpleName();

    private static final String POKE_DT = "POKE_DT";

    public static final String EXTRA_POKEMON_REPO = "PokemonRepo";
    private PokemonRepo mRepo;
    private boolean mIsSaved = false;

    private String mPokeItemString;

    private TextView repoIDTV;
    private ImageView mPokemonSpriteIV;
    private TextView mPokemonNameTV;
    private TextView mPokemonDescTV;
    private TextView mPokemonType1;
    private TextView mPokemonType2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate!");
        setContentView(R.layout.poke_detail);

        repoIDTV = findViewById(R.id.poke_detail_num);
        mPokemonSpriteIV = findViewById(R.id.poke_detail_picture);
        mPokemonNameTV = findViewById(R.id.poke_detail_name);
        mPokemonDescTV = findViewById(R.id.poke_detail_desc);
        mPokemonType1 = findViewById(R.id.poke_detail_type1);
        mPokemonType2 = findViewById(R.id.poke_detail_type2);
        mPokemonType2.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_POKEMON_REPO)) {
            Log.d(TAG,"Prushka: Intent not null and has Extra!");
            mRepo = (PokemonRepo)intent.getSerializableExtra(EXTRA_POKEMON_REPO);
            fillInLayout(mRepo);
        }

        final ImageView addPokemonIV = findViewById(R.id.iv_repo_add_pokemon);
        addPokemonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (mRepo != null) {
                    if (!mIsSaved) {
                        mViewModel.insertSavedRepo(mRepo);
                    } else {
                        mViewModel.deleteSavedRepo(mRepo);
                    }
                }
*/

                if(!mIsSaved) {
                    mIsSaved = true;
                    addPokemonIV.setImageResource(R.drawable.ic_action_remove);
                } else {
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
        mPokemonDescTV.setText("under construction!");
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
}
