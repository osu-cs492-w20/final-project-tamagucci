package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mSearch;
    private TextView mGenSearch;
    private TextView mTypeSearch;
    private TextView mAllPokemon;
    private TextView mFavePokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSearch = findViewById(R.id.tv_search_select);
        mGenSearch = findViewById(R.id.tv_gen_select);
        mTypeSearch = findViewById(R.id.tv_type_select);
        mAllPokemon = findViewById(R.id.tv_all_select);
        mFavePokemon = findViewById(R.id.tv_fave_select);

    }
}
