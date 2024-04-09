package com.example.vijarprelimexamproject;

import android.graphics.Color;

import java.util.Random;


public class Match {
    private int[][] grid;
    private int score;

    public Match() {
        grid = new int[5][5];
        score = 0;
        initializeGrid();
    }

    private void initializeGrid() {
        Random rand = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = rand.nextInt(4) + 1; // Numbers from 1 to 5
            }
        }
        if (this.isThreeInARow()) {
            initializeGrid();
        }
    }

    public boolean swap(int x1, int y1, int x2, int y2) {
        // Check if the positions are adjacent vertically or horizontally
        if ((x1 == x2 && Math.abs(y1 - y2) == 1) || (y1 == y2 && Math.abs(x1 - x2) == 1)) {
            int temp = grid[x1][y1];
            grid[x1][y1] = grid[x2][y2];
            grid[x2][y2] = temp;
            if (!this.isThreeInARow()) {
                temp = grid[x1][y1];
                grid[x1][y1] = grid[x2][y2];
                grid[x2][y2] = temp;
                return false;
            }
            return true;
        } else {
            System.out.println("Invalid swap! Positions must be adjacent vertically or horizontally.");
            return false;
        }
    }

    public boolean isThreeInARow() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length - 2; j++) {
                if (grid[i][j] == grid[i][j+1] && grid[i][j] == grid[i][j+2]) {
                    return true;
                }
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length - 2; i++) {
                if (grid[i][j] == grid[i+1][j] && grid[i][j] == grid[i+2][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void replaceMatchedTiles() {
        Random rand = new Random();
        boolean replaced;

        do {
            replaced = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    // Check horizontally
                    int countHorizontal = 1;
                    while (j + countHorizontal < grid[i].length && grid[i][j] == grid[i][j + countHorizontal]) {
                        countHorizontal++;
                    }
                    if (countHorizontal >= 3) {
                        for (int k = j; k < j + countHorizontal; k++) {
                            grid[i][k] = rand.nextInt(4) + 1;
                        }
                        this.score += countHorizontal / 3;
                        replaced = true;
                    }

                    // Check vertically
                    int countVertical = 1;
                    while (i + countVertical < grid.length && grid[i][j] == grid[i + countVertical][j]) {
                        countVertical++;
                    }
                    if (countVertical >= 3) {
                        for (int k = i; k < i + countVertical; k++) {
                            grid[k][j] = rand.nextInt(4) + 1;
                        }
                        this.score += countVertical / 3;
                        replaced = true;
                    }
                }
            }
        } while (replaced);
    }


    public int score() {
        return score;
    }

    public void restart() {
        initializeGrid();
        this.score = 0;
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getter and Setter for grid
    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setTile(int x, int y, int value){
        grid[x][y] = value;
    }

    public void setTileColor(int x, int y, int colorInt) {
        grid[x][y] = colorToInt(colorInt);
    }

    public int getTile(int x, int y) {
        return grid[x][y];
    }

    public int getTileColor(int x, int y) {
        return intToColor(grid[x][y]);
    }



    private int intToColor(int value) {
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


    private int colorToInt(int color) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : grid) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
