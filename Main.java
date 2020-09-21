package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int numOfMines = scanner.nextInt();
        char[][] gameField = new char[9][9];

        //add n number of mines to gamefield
        addMinesToGameField(numOfMines, gameField);
        //check each cell neighbour cells(around the cell) and save in gameFieldWithMineNumbers
        char[][] gameFieldWithMineNumbers = checkMines(gameField);
        // print number of mines in the neighbour cells
        showGameWithNumOfMines(gameFieldWithMineNumbers);
        //guess the mine cells using provided number of neighbour cells displayed in each cell

        playGame(scanner, numOfMines, gameFieldWithMineNumbers);
    }

    private static void playGame(Scanner scanner, int numOfMines, char[][] gameFieldWithMineNumbers) {
        int foundMines = 0;
        while (foundMines != numOfMines) {
            System.out.println("Set/delete mines marks (x and y coordinates): ");
            int col = scanner.nextInt()-1;
            int row = scanner.nextInt()-1;
            //check if provided coordinates got a mine(X)
            if(gameFieldWithMineNumbers[row][col]=='X'){
                System.out.println("found mine");
                foundMines++;
            }
            //check if provided coordinates got a number
            if(Character.isDigit(gameFieldWithMineNumbers[row][col])){
                System.out.println("There is a number here!\n");
                continue;

            }else if(gameFieldWithMineNumbers[row][col]=='*'){
                //if coordinats got a * ehich means delete the mark ,so replace with '.
                gameFieldWithMineNumbers[row][col]='.';
            }else{
                gameFieldWithMineNumbers[row][col]='*';//if coordinates have '.' or 'X' replace with a mark *
            }

            showGameWithNumOfMines(gameFieldWithMineNumbers);
        }
        System.out.println("Congratulations! You found all mines!");
    }

    private static void showGameWithNumOfMines(char[][] gameFieldWithMineNumbers) {
        //char[][] gameFieldWithMineNumbers = checkMines(gameField);
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < gameFieldWithMineNumbers.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < gameFieldWithMineNumbers[0].length; j++) {
                if (Character.isDigit(gameFieldWithMineNumbers[i][j])) {
                    System.out.print(Character.digit(gameFieldWithMineNumbers[i][j], 10));
                } else if(gameFieldWithMineNumbers[i][j]=='X'){
                    System.out.print('.');
                }else{
                    System.out.print(gameFieldWithMineNumbers[i][j]);
                }

            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    private static void addMinesToGameField(int numOfMines, char[][] gameField) {
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
        


    

