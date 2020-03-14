package com.example.theultimatedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.theultimatedex.data.PokemonRepo;
import com.example.theultimatedex.utils.PokeUtils;

public class PokeItemDetailActivity extends AppCompatActivity {
    private static final String TAG = PokeItemDetailActivity.class.getSimpleName();

    private static final String POKE_DT = "POKE_DT";

    public static final String EXTRA_POKEMON_REPO = "PokemonRepo";
    private PokemonRepo mRepo;

    private String mPokeItemString;

    private TextView repoIDTV;
    private ImageView mPokemonSpriteIV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate!");
        mPokeItemString = getIntent().getStringExtra(POKE_DT);
        setContentView(R.layout.poke_detail);


        repoIDTV = findViewById(R.id.poke_detail_num);
        mPokemonSpriteIV = findViewById(R.id.poke_detail_picture);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_POKEMON_REPO)) {
            mRepo = (PokemonRepo)intent.getSerializableExtra(EXTRA_POKEMON_REPO);



            fillInLayout(mRepo);
        }

    }


    private void fillInLayout(PokemonRepo pokemonRepo) {
        repoIDTV.setText(mRepo.id);

        String iconURL = PokeUtils.buildIconURL(pokemonRepo.sprites.front_default);
        Glide.with(this).load(iconURL).into(mPokemonSpriteIV);
    }
}
