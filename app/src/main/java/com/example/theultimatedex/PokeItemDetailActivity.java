package com.example.theultimatedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    private String mPokeItemString;

    private TextView repoIDTV;
    private ImageView mPokemonSpriteIV;
    private TextView mPokemonNameTV;
    private TextView mPokemonType1;
    //private TextView mPokemonType2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate!");
        setContentView(R.layout.poke_detail);

        repoIDTV = findViewById(R.id.poke_detail_num);
        mPokemonSpriteIV = findViewById(R.id.poke_detail_picture);
        mPokemonNameTV = findViewById(R.id.poke_detail_name);

        //mPokemonType1.findViewById(R.id.poke_detail_type1);
        //mPokemonType2.findViewById(R.id.poke_detail_type2);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_POKEMON_REPO)) {
            Log.d(TAG,"Prushka: Intent not null and has Extra!");
            mRepo = (PokemonRepo)intent.getSerializableExtra(EXTRA_POKEMON_REPO);

            fillInLayout(mRepo);
        }

    }


    private void fillInLayout(PokemonRepo pokemonRepo) {

        //repoIDTV.setText(mRepo.id);
        String pokemonNumber = "No. " + PadLeft(pokemonRepo.id,'0',3);

        repoIDTV.setText(pokemonNumber);

        String pokemonName = mRepo.name.substring(0, 1).toUpperCase() + mRepo.name.substring(1).toLowerCase();
        mPokemonNameTV.setText(pokemonName);

        /*
        if (mRepo.types[0].type[1].name != null) {
            String pokemonType2 = mRepo.types[0].type[1].name.substring(0, 1).toUpperCase() + mRepo.types[0].type[1].name.substring(1).toLowerCase();
            mPokemonType2.setText(pokemonType2);
            mPokemonType2.setVisibility(View.VISIBLE);
        }
        */
        //String pokemonType1 = mRepo.types[0].type[1].name.substring(0, 1).toUpperCase() + mRepo.types[0].type[1].name.substring(1).toLowerCase();
        //mPokemonType1.setText(pokemonType1);



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
