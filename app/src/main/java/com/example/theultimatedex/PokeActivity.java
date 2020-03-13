package com.example.theultimatedex;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PokeActivity extends AppCompatActivity {
    private static final String TAG = PokeActivity.class.getSimpleName();

    private static final String POKE_RV = "POKE_RV";
    private static final String GEN_AC = "GEN_AC";
    private static final String TYP_AC = "TYP_AC";

    private String mActivityType;
    private String mTypNum;
    private String mGenNum;

    private RecyclerView mPokeRV;
    private ProgressBar mPokeProgress;
    private TextView mPokeError;
    private PokeAdapter mPokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Prushka: PokeActivity onCreate");
        setContentView(R.layout.poke_recycle_layout);
        mTypNum = "0";
        mGenNum = "0";
        // Determine what type of activity this is.
        mActivityType = getIntent().getStringExtra(POKE_RV);
        // Fill out appropriate forms for the type of activity.
        switch(mActivityType) {
            case "GEN":
                mGenNum = getIntent().getStringExtra(GEN_AC);
                // Generation API call here.
                break;
            case "TYP":
                mTypNum = getIntent().getStringExtra(TYP_AC);
                // Type API call here.
                break;
            default:
                break;
        }
        // Begin creating new recycle-view to display the data.
        mPokeAdapter = new PokeAdapter();
        mPokeRV = findViewById(R.id.poke_RV);
        //mPokeProgress = findViewById(R.id.);
        //mPokeError = findViewById();
    }
}
