package proj3;

import adt.LinkedStack;
import utility.BlindSearchIntermediate;

import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {


    private static LinkedStack<Queen> queenStack;

    public static void main(String[] args) {

        queenStack = new LinkedStack<>();
        int boardSize;
        System.out.println("Enter the board size to test:");
        boardSize = new Scanner(System.in).nextInt();
        System.out.println("Number of solutions: " + run(boardSize));
    }
    public static int run(int boardSize) {

        Queen baseQueen = new Queen(1,1);                                   //create and add a new queen at top left of the board
        int numSolutions = 0;
        queenStack.push(baseQueen);
        int col = 3;                                                        //start trying queens at col 3 row 2, the first valid spot on the next row, saves 2 tests
        int row = 2;
        
        while (true) {
            Queen queen = new Queen(col,row);                               //main loop, create queen at new location
            if (queen.isOnBoard(boardSize) && !queen.conflicts(queenStack)) {         //test if its on the board and if it conflicts with any queen currently in play
                queenStack.push(queen);                                     //if it doesn't add it to the stack, reset col and increment row
                col = 1;
                row++;
            } else if (col > boardSize) {                                   //if its off the board then backtrack
                if (queenStack.isEmpty())
                    break;
                col = queenStack.pop()                                      //backtrack 1 row and increment col
                        .getBoardPosition()
                        .getCol() + 1;
                row--;
            } else
                col++;                                                      //only if conflict, increment col
            if (queenStack.size() == boardSize) {                           //a solution has been found
                printSolution(++numSolutions);                              //print it and backtrack 1 to keep trying solutions on that row
                col = queenStack.pop()
                        .getBoardPosition()
                        .getCol() + 1;
                row--;
            }
        }
        return numSolutions;
    }
    /*
    print a single solution
    */
    private static void printSolution(int numSolution) {
        System.out.println("Solution number: " + numSolution);
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.print(queenStack.itemAt(x) + "\t");
        }
        System.out.println();
    }


}
