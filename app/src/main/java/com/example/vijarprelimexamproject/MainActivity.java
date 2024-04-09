package com.example.vijarprelimexamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[][] tiles = new Button[5][5];
    private Button restartButton;
    private TextView displayScore;
    private Match match;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        match = new Match();
        restartButton = findViewById(R.id.buttonRestart);
        displayScore = findViewById(R.id.displayScore);
        setTiles();
        setRestart();

        refresh();
    }

    private int firstClickedX = -1;
    private int firstClickedY = -1;

    private void setTiles() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int buttonId = getResources().getIdentifier("btn" + ((i * 5) + j + 1), "id", getPackageName());
                tiles[i][j] = findViewById(buttonId);

                final int x = i; // Store the x coordinate
                final int y = j; // Store the y coordinate

                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (firstClickedX == -1 && firstClickedY == -1) {
                            // Record the first clicked button
                            firstClickedX = x;
                            firstClickedY = y;
                        } else {
                            // Perform a swap between the first clicked button and the current button
                            match.swap(firstClickedX, firstClickedY, x, y);
                            // Reset the first clicked coordinates
                            firstClickedX = -1;
                            firstClickedY = -1;
                            match.replaceMatchedTiles();
                            setAllTileColor();
                            displayScore.setText(String.valueOf(match.score()));
                        }
                    }
                });
            }
        }
    }


    private void setAllTileColor() {
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                setTileColor(i, j, match.getTileColor(i, j));
            }
        }
    }

    private void setTileColor(int x, int y, int colorInt) {
        tiles[x][y].setBackgroundColor(colorInt);
    }

    private int getTileColor(int x, int y) {
        return getBackgroundColor(tiles[x][y]);
    }

    private int getBackgroundColor(Button button) {
        Drawable background = button.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        }
        // Return a default color if the background is not a ColorDrawable
        return Color.TRANSPARENT;
    }

    private void setRestart() {
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
                tiles[i][j].setBackgroundColor(match.getTileColor(i, j));
            }
        }
        displayScore.setText("0");
        System.out.println(match);
    }


}