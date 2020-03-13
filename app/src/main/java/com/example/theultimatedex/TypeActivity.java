package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TypeActivity extends AppCompatActivity {
    private static final String TAG = TypeActivity.class.getSimpleName();
    private static final String POKE_RV = "POKE_RV";
    private static final String TYP_AC = "TYP_AC";

    private TextView mTyp1;
    private TextView mTyp2;
    private TextView mTyp3;
    private TextView mTyp4;
    private TextView mTyp5;
    private TextView mTyp6;
    private TextView mTyp7;
    private TextView mTyp8;
    private TextView mTyp9;
    private TextView mTyp10;
    private TextView mTyp11;
    private TextView mTyp12;
    private TextView mTyp13;
    private TextView mTyp14;
    private TextView mTyp15;
    private TextView mTyp16;
    private TextView mTyp17;
    private TextView mTyp18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: onCreate");
        setContentView(R.layout.activity_type);
        // Init the views.
        mTyp1 = findViewById(R.id.tv_normal_select);
        mTyp2 = findViewById(R.id.tv_fighting_select);
        mTyp3 = findViewById(R.id.tv_flying_select);
        mTyp4 = findViewById(R.id.tv_poison_select);
        mTyp5 = findViewById(R.id.tv_ground_select);
        mTyp6 = findViewById(R.id.tv_rock_select);
        mTyp7 = findViewById(R.id.tv_bug_select);
        mTyp8 = findViewById(R.id.tv_ghost_select);
        mTyp9 = findViewById(R.id.tv_steel_select);
        mTyp10 = findViewById(R.id.tv_fire_select);
        mTyp11 = findViewById(R.id.tv_water_select);
        mTyp12 = findViewById(R.id.tv_grass_select);
        mTyp13 = findViewById(R.id.tv_electric_select);
        mTyp14 = findViewById(R.id.tv_psychic_select);
        mTyp15 = findViewById(R.id.tv_ice_select);
        mTyp16 = findViewById(R.id.tv_dragon_select);
        mTyp17 = findViewById(R.id.tv_dark_select);
        mTyp18 = findViewById(R.id.tv_fairy_select);
        // Add the listeners.
        mTyp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("normal");
            }
        });
        mTyp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("fighting");
            }
        });
        mTyp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("flying");
            }
        });
        mTyp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("poison");
            }
        });
        mTyp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("ground");
            }
        });
        mTyp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("rock");
            }
        });
        mTyp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("bug");
            }
        });
        mTyp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("ghost");
            }
        });
        mTyp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("steel");
            }
        });
        mTyp10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("fire");
            }
        });
        mTyp11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("water");
            }
        });
        mTyp12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("grass");
            }
        });
        mTyp13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("electric");
            }
        });
        mTyp14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("psychic");
            }
        });
        mTyp15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("ice");
            }
        });
        mTyp16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("dragon");
            }
        });
        mTyp17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("dark");
            }
        });
        mTyp18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("fairy");
            }
        });
    }
    public void showPokeRV(String type) {
        Log.d(TAG,"Prushka: showPokeRV! Type: " + type);
        Intent intent = new Intent(this, PokeActivity.class);
        Bundle extras = new Bundle();
        // Add the Extras to the bundle.
        extras.putString(POKE_RV,"TYP_AC");
        extras.putString(TYP_AC, type);
        // Put the extras.
        intent.putExtras(extras);
        startActivity(intent);

    }
}
