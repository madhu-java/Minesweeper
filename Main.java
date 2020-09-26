package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int numOfMines = scanner.nextInt();
        scanner.nextLine();
        //create 9*9 game field with n num of mines
        GameField field = new GameField(numOfMines);
        field.addMinesToGameField();
        //set the mines and play game asing the user to explore and find number of mines around each cell
        playGame(scanner, numOfMines, field.getField());
        //showGameWithNumOfMines(field.getField());
    }

    private static void playGame(Scanner scanner, int numOfMines, char[][] gameField) {
        int foundMines = 0;
        char[][] gameFieldWithMineNumbers = gameField.clone();
        boolean firstFreeCellChecked = false;
//first free cell to be explored must be empty(not mine)//game rule
        do {
            System.out.println("Set/unset mines marks or claim a cell as free:");
            String[] userSetInput = scanner.nextLine().split("\\s");
            int row = Integer.parseInt(userSetInput[1].trim()) - 1;
            int col = Integer.parseInt(userSetInput[0].trim()) - 1;

            String ForM = userSetInput[2].trim();

            if (ForM.equals("free")) {
                if (!firstFreeCellChecked) {
                   // System.out.println("inside first free cell:");
                    Random random = new Random();
                    if (gameField[row][col] == 'X') {
                        boolean exit = false;
                        while (!exit) {
                            int x = random.nextInt(9);
                            int y = random.nextInt(9);
                            if (gameField[x][y] == '.') {
                                gameField[x][y] = 'X';
                                gameField[row][col] = '.';
                                exit = true;
                            }
                        }
                    }
                    firstFreeCellChecked = true;
                }

                //check if provided coordinates got a mine(X)
                if (gameFieldWithMineNumbers[row][col] == 'X') {
                    showGameWithNumOfMines(gameFieldWithMineNumbers);
                    System.out.println("You stepped on a mine and failed!");
                    break;

                } else if (gameFieldWithMineNumbers[row][col] == '.') {
                    // if it's free you can explore by chking neighbours
                    System.out.println("gameFieldWithMineNumbers[row][col] == '.'");
                    gameFieldWithMineNumbers = checkEmptyNeighbours(row, col, gameFieldWithMineNumbers);
                } else if (Character.isDigit(gameFieldWithMineNumbers[row][col])) {
                    // System.out.println("There is a number here!\n");
                    gameFieldWithMineNumbers[row][col] = '.';
                    //continue;
                }
            } else if (ForM.equals("mine")) {
                //setting a mine
                //check if provided coordinates got a number if so mark with spl symbol '@'
                if (gameFieldWithMineNumbers[row][col] == 'X') {
                    foundMines++;
                    gameFieldWithMineNumbers[row][col] = '@';

                } else if (gameFieldWithMineNumbers[row][col] == '.') {
                    gameFieldWithMineNumbers[row][col] = '*';
                   //resetting a mine back to . or X
                } else if (gameFieldWithMineNumbers[row][col] == '@') {
                    gameFieldWithMineNumbers[row][col] = 'X';
                } else if (gameFieldWithMineNumbers[row][col] == '*') {
                    gameFieldWithMineNumbers[row][col] = '.';
                }
            }
            showGameWithNumOfMines(gameFieldWithMineNumbers);

        } while (foundMines != numOfMines);
        System.out.println("Congratulations! You found all mines!");
    }

//check each neighbour(topleft,top,topright,righ,left,bottomleft,bottom,bottemright)
// starting with topleft if it's empty check its 8 neighbours starting from top left then top..
// ....as long as the cells are empty or you reach the boundary so on recursively
    //until you find a cell with number of cells around it
    public static char[][] checkEmptyNeighbours(int row, int col, char[][] gamefieldWithMineNumbers) {

        int count = getMinesNumAround(gamefieldWithMineNumbers, row, col);
       // System.out.println("current free cell,:" + row + " col:" + col + "count ;" + count);
        if (count == 0) {
            gamefieldWithMineNumbers[row][col] = '/';
            // checkEmptyNeighbours(row, i, gamefieldWithMineNumbers);
        } else {

            gamefieldWithMineNumbers[row][col] = Character.forDigit(count, 10);

        }
//        System.out.println("game field after check a cell is empty:");
//        for (int a = 0; a < 9; a++) {
//            for (int b = 0; b < 9; b++) {
//                System.out.print(gamefieldWithMineNumbers[a][b]);
//            }
//            System.out.println();
//        }
        char ch = '.';
        if (row - 1 >= 0) {//if top row exists
            if (col - 1 >= 0) {//ifif top left col exist
                //top left cell
                if (gamefieldWithMineNumbers[row - 1][col - 1] == ch || gamefieldWithMineNumbers[row - 1][col - 1] == '*') {
                   // System.out.println("inside topLeft: cell top " + (row - 1) + "col " + (col - 1));
                    explore(row - 1, gamefieldWithMineNumbers, col - 1);
                }
            }


            //top cell
            if (gamefieldWithMineNumbers[row - 1][col] == ch || gamefieldWithMineNumbers[row - 1][col] == '*') {
               // System.out.println("inside top: cell top " + (row - 1) + "col " + (col));

                explore(row - 1, gamefieldWithMineNumbers, col);
            }

            if (col + 1 <= gamefieldWithMineNumbers[0].length - 1) { //if top right col exist
                if (gamefieldWithMineNumbers[row - 1][col + 1] == ch || gamefieldWithMineNumbers[row - 1][col + 1] == '*') {//check top right cell

                   // System.out.println("inside topRight: cell top " + (row - 1) + "col " + (col + 1));
                    explore(row - 1, gamefieldWithMineNumbers, col + 1);
                }
            }
        }

        //if right col exist
        if (col + 1 <= gamefieldWithMineNumbers[0].length - 1) {
            if (gamefieldWithMineNumbers[row][col + 1] == ch || gamefieldWithMineNumbers[row][col + 1] == '*') {//check right cell

             //   System.out.println("inside right: cell top " + (row) + "col " + (col + 1));
                explore(row, gamefieldWithMineNumbers, col + 1);
            }
        }
        //if left col exist
        if (col - 1 >= 0) {
            if (gamefieldWithMineNumbers[row][col - 1] == ch || gamefieldWithMineNumbers[row][col - 1] == '*') {//check right cell

              //  System.out.println("inside left: cell top " + (row) + "col " + (col - 1));
                explore(row, gamefieldWithMineNumbers, col - 1);
            }
        }
        //if bottom row exist
        if (row + 1 < gamefieldWithMineNumbers.length) {
            if (col - 1 >= 0) {//if left col exist
                if (gamefieldWithMineNumbers[row + 1][col - 1] == ch || gamefieldWithMineNumbers[row + 1][col - 1] == '*') {//botteom left cell

                  //  System.out.println("inside BottomLeft: cell top " + (row + 1) + "col " + (col - 1));
                    explore(row + 1, gamefieldWithMineNumbers, col - 1);
                }
            }
            if (gamefieldWithMineNumbers[row + 1][col] == ch || gamefieldWithMineNumbers[row + 1][col] == '*') {//bottom cell

               // System.out.println("inside Bottom: cell top " + (row + 1) + "col " + (col));
                explore(row + 1, gamefieldWithMineNumbers, col);
            }
            if (col + 1 < gamefieldWithMineNumbers[0].length) {//if right col exist
                if (gamefieldWithMineNumbers[row + 1][col + 1] == ch || gamefieldWithMineNumbers[row + 1][col + 1] == '*') {

                   // System.out.println("inside BottomRight: cell top " + (row + 1) + "col " + (col + 1));
                    explore(row + 1, gamefieldWithMineNumbers, col + 1);
                }

            }

        }

        return gamefieldWithMineNumbers;
    }
//explres each cell for mines around recursively and returns number of mines if it come across a neighbour mine cell
    public static void explore(int row, char[][] gamefieldWithMineNumbers, int i) {
        int count = getMinesNumAround(gamefieldWithMineNumbers, row, i);
        System.out.println("count in explore row,:" + row + " col:" + i + "count ;" + count);
        if (count == 0) {
            gamefieldWithMineNumbers[row][i] = '/';
            checkEmptyNeighbours(row, i, gamefieldWithMineNumbers);
        } else {

            gamefieldWithMineNumbers[row][i] = Character.forDigit(count, 10);
        }
    }


    //print field in a formatted way
    public static void showGameWithNumOfMines(char[][] gameFieldWithMineNumbers) {
        System.out.println("insode print  ");
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
                } else if (gameFieldWithMineNumbers[i][j] == '@') {
                    System.out.print('*');
                } else {
                    System.out.print(gameFieldWithMineNumbers[i][j]);
                }

            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

//checks numberof mines around(topleft,top,top right,left,right,bottomleft,bottom,bottom right) a cell and
// returns the number
    public static int getMinesNumAround(char[][] gamefield, int i, int j) {
        int count = 0;
        char ch = 'X';
        if (i - 1 >= 0) {//if top row exists
            if (j - 1 >= 0) {//ifif top left col exist
                //top left cell
                if (gamefield[i - 1][j - 1] == ch || gamefield[i - 1][j - 1] == '@') {
                    count++;
                }
            }
            //top cell
            if (gamefield[i - 1][j] == 'X' || gamefield[i - 1][j] == '@') {
                count++;
            }


            if (j + 1 <= gamefield[0].length - 1) { //if top right col exist
                if (gamefield[i - 1][j + 1] == 'X' || gamefield[i - 1][j + 1] == '@') {//check top right cell
                    count++;
                }
            }
        }
        //if right col exist
        if (j + 1 <= gamefield[0].length - 1) {
            if (gamefield[i][j + 1] == 'X' || gamefield[i][j + 1] == '@') {//check right cell
                count++;
            }
        }
        //if left col exist
        if (j - 1 >= 0) {
            if (gamefield[i][j - 1] == 'X' || gamefield[i][j - 1] == '@') {//check right cell
                count++;
            }
        }
        //if bottom row exist
        if (i + 1 < gamefield.length) {
            if (j - 1 >= 0) {//if left col exist
                if (gamefield[i + 1][j - 1] == 'X' || gamefield[i + 1][j - 1] == '@') {//botteom left cell
                    count++;
                }
            }
            if (gamefield[i + 1][j] == 'X' || gamefield[i + 1][j] == '@') {//bottom cell
                count++;
            }
            if (j + 1 < gamefield[0].length) {//if right col exist
                if (gamefield[i + 1][j + 1] == 'X' || gamefield[i + 1][j + 1] == '@') {
                    count++;
                }

            }
        }
        return count;
    }


}
        


    

