package com.example.vijarprelimexamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button[][] tiles = new Button[5][5];
    private Button restartButton;
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        match = new Match();
        restartButton = findViewById(R.id.buttonRestart);
        setTiles();
        restart();

    }

    private void setTiles(){
        int p1, p2;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "btn" + (i * 5 + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                tiles[i][j] = findViewById(resID);
                tiles[i][j].setBackgroundColor(Match.intToColor(match.getTile(i,j)));
                // Set the OnClickListener for this button
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }
    }

    private void restart() {
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match.restart();
                refresh();
            }
        });
    }

    private void refresh() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tiles[i][j].setBackgroundColor(Match.intToColor(match.getTile(i, j)));
            }
        }

    }


}