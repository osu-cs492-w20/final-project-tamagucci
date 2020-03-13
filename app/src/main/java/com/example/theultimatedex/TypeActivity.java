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
        mTyp1 = findViewById(R.id.tv_normal_select);
        mTyp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPokeRV("normal");
            }
        });
    }
    public void showPokeRV(String type) {
        Log.d(TAG,"Prushka: showPokeRV!");
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
