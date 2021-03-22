package proj3;

import adt.LinkedStack;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

        Queen baseQueen = new Queen(1, 1);                                   //create and add a new queen at top left of the board
        int numSolutions = 0;
        queenStack.push(baseQueen);
        int col = 3;                                                        //start trying queens at col 3 row 2, the first valid spot on the next row, saves 2 tests
        int row = 2;

        QueensTask.setBoardSize(boardSize);
        QueensTask.setRowNumber(2);
        QueensTask.setColumnNumber(3);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        while (!QueensTask.isFinished()) {
            executor.submit(new QueensTask(queenStack));
        }
        executor.shutdown();
        return QueensTask.getNumSolutions();
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
