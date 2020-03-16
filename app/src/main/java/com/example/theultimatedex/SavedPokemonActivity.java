package com.example.theultimatedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.PokemonRepo;

import java.util.List;

public class SavedPokemonActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonItemClickListener {


    private SavedReposViewModel mViewModel;
    private PokemonAdapter mPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("UltimateDex/SavedPoke", "Opened Fave Pokemon Activity");
        setContentView(R.layout.activity_saved_pokemon);

        RecyclerView savedReposRV = findViewById(R.id.rv_saved_repos);
        savedReposRV.setLayoutManager(new LinearLayoutManager(this));
        savedReposRV.setHasFixedSize(true);

        mPokemonAdapter = new PokemonAdapter(this);
        savedReposRV.setAdapter(mPokemonAdapter);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedReposViewModel.class);

        mViewModel.getAllRepos().observe(this, new Observer<List<PokemonRepo>>() {
            @Override
            public void onChanged(List<PokemonRepo> PokemonRepos) {
                mPokemonAdapter.updateSearchResults(PokemonRepos);
            }
        });
    }



    @Override
    public void onPokemonItemClick(PokemonRepo pokemonRepo) {
        Intent intent = new Intent(this, PokeItemDetailActivity.class);
        intent.putExtra(PokeItemDetailActivity.EXTRA_POKEMON_REPO, pokemonRepo);
        startActivity(intent);
    }
}
