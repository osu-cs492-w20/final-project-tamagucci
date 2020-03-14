package com.example.theultimatedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theultimatedex.data.PokemonRepo;

public class PokeItemDetailActivity extends AppCompatActivity {
    private static final String TAG = PokeItemDetailActivity.class.getSimpleName();

    private static final String POKE_DT = "POKE_DT";

    public static final String EXTRA_POKEMON_REPO = "PokemonRepo";
    private PokemonRepo mRepo;

    private String mPokeItemString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate!");
        mPokeItemString = getIntent().getStringExtra(POKE_DT);
        setContentView(R.layout.poke_detail);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_POKEMON_REPO)) {
            mRepo = (PokemonRepo)intent.getSerializableExtra(EXTRA_POKEMON_REPO);

            TextView repoIDTV = findViewById(R.id.poke_detail_num);
            repoIDTV.setText(mRepo.id);
        }

    }
}
