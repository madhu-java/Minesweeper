package minesweeper;

import java.util.Random;

public class GameField {
    char[][] field = new char[9][9];
    int mines;


    GameField(int mines) {
        this.mines = mines;
        fill();
        printGame(field);
    }

    public char[][] getField() {
        return field;
    }

    public void addMinesToGameField() {
        Random random = new Random();
        for (int i = 1; i <= mines; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (field[x][y] == 'X') {
                i--;
                continue;
            }
            field[x][y] = 'X';

        }

    }

    public void fill() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = '.';
            }
        }

    }

    public void printGame(char[][] gameFieldWithMineNumbers) {
        //char[][] gameFieldWithMineNumbers = checkMines(gameField);
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < gameFieldWithMineNumbers.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < gameFieldWithMineNumbers[0].length; j++) {
                if (Character.isDigit(gameFieldWithMineNumbers[i][j])) {
                    System.out.print(Character.digit(gameFieldWithMineNumbers[i][j], 10));
                } else if (gameFieldWithMineNumbers[i][j] == 'X') {
                    System.out.print('.');
                } else {
                    System.out.print(gameFieldWithMineNumbers[i][j]);
                }

            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    public void setInitialEmptyCell(int row, int col) {
        Random random = new Random();
        if (field[row][col] == 'X') {
            while (true) {
                int x = random.nextInt(9);
                int y = random.nextInt(9);
                if (field[x][y] == '.') {
                    field[x][y] = 'X';
                    field[row][col] = '.';
                    break;
                }
            }
        }
    }
      /*  char ch = '\\';
                 /*if i-1>=0 toprow exist
                 if(j-1)>=0 left col exist
                 if(j+1>=collength right col exist)
                 if(i+1>=roelength bottom row exist)

        field[i][j] = ch;
        if (i - 1 >= 0) {//if top row exists
            if (j - 1 >= 0) {//ifif top left col exist
                //top left cell
                field[i - 1][j - 1] = ch;
            }
            //top cell
            field[i - 1][j] = ch;
            if (j + 1 <= field[0].length - 1) { //if top right col exist
                field[i - 1][j + 1] = ch; //check top right cell
            }
        }
        //if right col exist
        if (j + 1 <= field[0].length - 1) {
            field[i][j + 1] = ch;//check right cell
        }
        //if left col exist
        if (j - 1 >= 0) {
            field[i][j - 1] = ch;//check right cell
        }
        //if bottom row exist
        if (i + 1 < field.length) {
            if (j - 1 >= 0) {//if left col exist
                field[i + 1][j - 1] = ch;//botteom left cell
            }
            field[i + 1][j] = ch; //bottom cell

            if (j + 1 < field[0].length) {//if right col exist
                field[i + 1][j + 1] = ch;
            }
        }
//        System.out.println("pring after initialise =======");
//        printGame(field);
    }
    */

}



