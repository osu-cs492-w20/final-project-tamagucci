package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GenActivity extends AppCompatActivity {
    private static final String TAG = GenActivity.class.getSimpleName();
    private static final String POKE_RV = "POKE_RV";
    private static final String GEN_AC = "GEN_AC";

    private TextView mGen1;
    private TextView mGen2;
    private TextView mGen3;
    private TextView mGen4;
    private TextView mGen5;
    private TextView mGen6;
    private TextView mGen7;
    private TextView mGen8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: GenActivity onCreate");
        setContentView(R.layout.activity_gen);
        mGen1 = findViewById(R.id.tv_gen1_select);
        mGen2 = findViewById(R.id.tv_gen2_select);
        mGen3 = findViewById(R.id.tv_gen3_select);
        mGen4 = findViewById(R.id.tv_gen4_select);
        mGen5 = findViewById(R.id.tv_gen5_select);
        mGen6 = findViewById(R.id.tv_gen6_select);
        mGen7 = findViewById(R.id.tv_gen7_select);
        mGen8 = findViewById(R.id.tv_gen8_select);

        mGen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 1 selected!");
                showPokeRV(1);
            }
        });
        mGen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 2 selected!");
                showPokeRV(2);
            }
        });
        mGen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 3 selected!");
                showPokeRV(3);
            }
        });
        mGen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 4 selected!");
                showPokeRV(4);
            }
        });
        mGen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 5 selected!");
                showPokeRV(5);
            }
        });
        mGen6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Prushka: Gen 6 selected!");
                showPokeRV(6);
            }
        });
    }
    public void showPokeRV(Integer genNum) {
        Log.d(TAG,"Prushka: showPokeRV!");
        Intent intent = new Intent(this, PokeActivity.class);
        Bundle extras = new Bundle();
        // Add the Extras to the bundle.
        extras.putString(POKE_RV,"GEN_AC");
        extras.putString(GEN_AC, genNum.toString());
        // Put the extras.
        intent.putExtras(extras);
        startActivity(intent);

    }
}
