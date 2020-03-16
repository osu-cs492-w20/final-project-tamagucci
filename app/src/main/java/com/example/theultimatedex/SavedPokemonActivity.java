package com.example.theultimatedex;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theultimatedex.data.PokemonRepo;

import java.util.List;

public class SavedPokemonActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonItemClickListener {


    private SavedReposViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_pokemon);

        RecyclerView savedReposRV = findViewById(R.id.rv_saved_repos);
        savedReposRV.setLayoutManager(new LinearLayoutManager(this));
        savedReposRV.setHasFixedSize(true);

        final PokemonAdapter adapter = new PokemonAdapter(this);
        savedReposRV.setAdapter(adapter);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedReposViewModel.class);

        mViewModel.getAllRepos().observe(this, new Observer<List<PokemonRepo>>() {
            @Override
            public void onChanged(List<PokemonRepo> PokemonRepos) {
                adapter.updateSearchResults(PokemonRepos);
            }
        });
    }




    @Override
    public void onPokemonItemClick(PokemonRepo pokemonRepo) {

    }
}
