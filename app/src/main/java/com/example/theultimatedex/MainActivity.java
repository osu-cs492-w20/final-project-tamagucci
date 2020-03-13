package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        Log.d("UltimateDex/MainActivit", "Starting Activity");
        mSearch = findViewById(R.id.tv_search_select);
        mGenSearch = findViewById(R.id.tv_gen_select);
        mTypeSearch = findViewById(R.id.tv_type_select);
        mAllPokemon = findViewById(R.id.tv_all_select);
        mFavePokemon = findViewById(R.id.tv_fave_select);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });

        mGenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenSelect();
            }
        });

        mTypeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTypeSelect();
            }
        });

    }


    public void openSearch() {
        Log.d("UltimateDex/MainActivit", "Opening Search Activity");
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

    }

    public void openGenSelect() {
        Log.d("UltimateDex/MainActivit", "Opening Gen Activity");
        Intent intent = new Intent(this, GenActivity.class);
        startActivity(intent);
    }

    public void openTypeSelect() {
        Log.d("UltimateDex/MainActivit", "Opening Type Activity");
        Intent intent = new Intent(this, TypeActivity.class);
        startActivity(intent);
    }


}
