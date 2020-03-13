package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
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
import com.example.theultimatedex.utils.PokeUtils;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonItemClickListener {

    private EditText mSearchBoxET;
    private PokeSearchViewModel mViewModel;
    private ImageView mPokeImage;

    private RecyclerView mPokemonItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private PokemonAdapter mPokemonAdapter;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("UltimateDex/SearchActiv", "In Search Activity");
        mSearchBoxET = findViewById(R.id.et_search_box);



        //mPokeImage = findViewById(R.id.pokemon_image);
        mPokemonItemsRV = findViewById(R.id.rv_pokemon_items);

        mPokemonAdapter = new PokemonAdapter(this);
        mPokemonItemsRV.setAdapter(mPokemonAdapter);
        mPokemonItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mPokemonItemsRV.setHasFixedSize(true);
        context = getApplicationContext();


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

        //String url = PokeUtils.buildPokeURL(searchQuery);


    }
}
