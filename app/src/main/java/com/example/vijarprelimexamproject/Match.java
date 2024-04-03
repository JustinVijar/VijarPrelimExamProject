package com.example.vijarprelimexamproject;


import android.graphics.Color;

import java.util.Random;

interface AbstractMatch{

    int[][] grid = new int[5][5];

    void swap(int x1, int y1, int x2, int y2);
    boolean isThreeInARow();
    void replaceMatchedTiles();
    int score();
    void restart();

}

public class Match implements AbstractMatch{

    private final int GRID_SIZE = 5;
    private int score;
    int[][] grid;
    private Random random;

    public Match() {
        this.grid = new int[GRID_SIZE][GRID_SIZE];
        this.score = 0;
        this.random = new Random();
        this.restart();
    }

    @Override
    public void swap(int x1, int y1, int x2, int y2) {
        // Swap numbers in grid[x1][y1] and grid[x2][y2]
        int temp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = temp;
    }

    @Override
    public boolean isThreeInARow() {
        // Check if three tiles are lined up (horizontally or vertically)
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // Check horizontally
                if (j + 2 < GRID_SIZE &&
                        grid[i][j] == grid[i][j + 1] &&
                        grid[i][j] == grid[i][j + 2]) {
                    return true;
                }
                // Check vertically
                if (i + 2 < GRID_SIZE &&
                        grid[i][j] == grid[i + 1][j] &&
                        grid[i][j] == grid[i + 2][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void replaceMatchedTiles() {
        // Replace matched tiles with new random numbers
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (isThreeInARow()) {
                    // Replace matched tiles with random numbers (1 to 4)
                    Random random = new Random();
                    grid[i][j] = random.nextInt(4) + 1;
                    // Update score
                    score++;
                }
            }
        }
    }


    @Override
    public int score() {
        return this.score;
    }

    @Override
    public void restart() {
        this.score = 0;
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = random.nextInt(4) + 1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                sb.append(this.grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setTile(int point1, int point2, int value){
        grid[point1][point2] = value;
    }

    public int getTile(int point1, int point2) {
        return grid[point1][point2];
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public static int intToColor(int value) {
        switch (value) {
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
        }
        return 0;
    }


    public static int colorToInt(int color) {
        switch (color) {
            case Color.RED:
                return 1;
            case Color.GREEN:
                return 2;
            case Color.BLUE:
                return 3;
            case Color.YELLOW:
                return 4;
        }
        return 0;
    }

}
