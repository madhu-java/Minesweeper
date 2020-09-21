package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int numOfMines = scanner.nextInt();
        char[][] gameField = new char[9][9];


        Random random = new Random();
        for (int i = 1; i <= numOfMines; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (gameField[x][y] == 'X') {
                i--;
                continue;
            }
            gameField[x][y] = 'X';

        }
//        for (char[] carray : gameField) {
//            for (char c : carray) {
//                if (c != 'X') {
//                    c = '.';
//                    char[][]  gameFieldWithMineNumbers =checkMines(gameField);
//                }
//                System.out.print(c);
//            }
//            System.out.println();
//        }
        char[][] gameFieldWithMineNumbers = checkMines(gameField);
        for (int i = 0; i < gameFieldWithMineNumbers.length; i++) {
            for (int j = 0; j < gameFieldWithMineNumbers[0].length; j++) {
                if(Character.isDigit(gameFieldWithMineNumbers[i][j]))
                {
                    System.out.print(Character.digit(gameFieldWithMineNumbers[i][j],10));
                }else
                System.out.print(gameFieldWithMineNumbers[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] checkMines(char[][] gamefield) {
        char[][] gameFieldWithMineNumbers = new char[9][9];
        for (int i = 0; i < gamefield.length; i++) {
            for (int j = 0; j < gamefield[0].length; j++) {
                 /*if i-1>=0 toprow exist
                 if(j-1)>=0 left col exist
                 if(j+1>=collength right col exist)
                 if(i+1>=roelength bottom row exist)*/
                if (gamefield[i][j] == 'X') {
                    gameFieldWithMineNumbers[i][j] = 'X';
                } else {
                    int count = 0;
                    if (i - 1 >= 0) {//if top row exists
                        if (j - 1 >= 0) {//ifif top left col exist
                            //top left cell
                            if (gamefield[i - 1][j - 1] == 'X') {
                                count++;
                            }
                        }
                        //top cell
                        if (gamefield[i - 1][j] == 'X') {
                            count++;
                        }


                        if (j + 1 <= gamefield[0].length - 1) { //if top right col exist
                            if (gamefield[i - 1][j + 1] == 'X') {//check top right cell
                                count++;
                            }
                        }
                    }
                    //if right col exist
                    if (j + 1 <= gamefield[0].length - 1) {
                        if (gamefield[i][j + 1] == 'X') {//check right cell
                            count++;
                        }
                    }
                    //if left col exist
                    if (j - 1 >= 0) {
                        if (gamefield[i][j - 1] == 'X') {//check right cell
                            count++;
                        }
                    }
                    //if bottom row exist
                    if (i + 1 < gamefield.length) {
                        if (j - 1 >= 0) {//if left col exist
                            if (gamefield[i + 1][j - 1] == 'X') {//botteom left cell
                                count++;
                            }
                        }
                        if (gamefield[i + 1][j] == 'X') {//bottom cell
                            count++;
                        }
                        if (j + 1 < gamefield[0].length) {//if right col exist
                            if (gamefield[i + 1][j + 1] == 'X') {
                                count++;
                            }

                        }
                    }
                    if (count >= 1) {
                        gameFieldWithMineNumbers[i][j] = Character.forDigit(count, 10);
                    } else
                        gameFieldWithMineNumbers[i][j] = '.';
                }
            }

        }
       return gameFieldWithMineNumbers;
    }


}
        


    

