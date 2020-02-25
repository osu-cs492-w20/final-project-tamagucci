package com.example.theultimatedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.theultimatedex.utils.PokeUtils;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchBoxET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchBoxET = findViewById(R.id.et_search_box);

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

        String url = PokeUtils.buildPokeURL(searchQuery);
    }
}
