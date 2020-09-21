package minesweeper;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int numOfMines = scanner.nextInt(); 
        char[][] mine = new char[9][9];

        Random random = new Random();
        for(int i=1;i<=numOfMines;i++){
            int x = random.nextInt(9);
            int y= random.nextInt(9);
            if(mine[x][y]=='X'){
                i--;
                continue;
            }
                 mine[x][y] = 'X';
            
        }
        for(char[] carray :mine){
            for(char c : carray){
                if(c!='X'){
                    c = '.';
                }
                 System.out.print(c);
            }
            System.out.println();
        }

    }
}
    
