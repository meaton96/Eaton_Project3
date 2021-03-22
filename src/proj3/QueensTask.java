package proj3;

import adt.LinkedStack;

public class QueensTask implements Runnable{

    private static int columnNumber;
    private static int rowNumber;
    private static int numSolutions;
    private LinkedStack<Queen> queenStack;
    private static int boardSize;
    private static boolean finished;

    public QueensTask(LinkedStack<Queen> stack) {
        queenStack = stack;
        run();
    }

    @Override
    public void run() {
        while (true) {
            Queen queen = new Queen(columnNumber, rowNumber);                               //main loop, create queen at new location
            if (queen.isOnBoard(boardSize) && !queen.conflicts(queenStack)) {         //test if its on the board and if it conflicts with any queen currently in play
                queenStack.push(queen);                                     //if it doesn't add it to the stack, reset col and increment row
                columnNumber = 1;
                rowNumber++;
            } else if (columnNumber > boardSize) {                                   //if its off the board then backtrack
                if (queenStack.isEmpty()) {
                    finished = true;
                    return;
                }
                columnNumber = queenStack.pop()                                      //backtrack 1 row and increment col
                        .getBoardPosition()
                        .getCol() + 1;
                rowNumber--;
            } else
                columnNumber++;                                                      //only if conflict, increment col
            if (queenStack.size() == boardSize) {                           //a solution has been found
                printSolution(++numSolutions);                              //print it and backtrack 1 to keep trying solutions on that row
                columnNumber = queenStack.pop()
                        .getBoardPosition()
                        .getCol() + 1;
                rowNumber--;
            }
        }
    }
    public void initStack(LinkedStack<Queen> stack) {
        queenStack = stack;
    }


    private void printSolution(int numSolutions) {
        System.out.println("Solution number: " + numSolutions);
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.print(queenStack.itemAt(x) + "\t");
        }
        System.out.println();
    }
    public static boolean isFinished() { return finished; }
    public static int getBoardSize() {
        return boardSize;
    }

    public static void setBoardSize(int boardSize) {
        QueensTask.boardSize = boardSize;
    }

    public static int getNumSolutions() {
        return numSolutions;
    }

    public static void setNumSolutions(int numSolutions) {
        QueensTask.numSolutions = numSolutions;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static void setColumnNumber(int columnNumber) {
        QueensTask.columnNumber = columnNumber;
    }

    public static int getRowNumber() {
        return rowNumber;
    }

    public static void setRowNumber(int rowNumber) {
        QueensTask.rowNumber = rowNumber;
    }
}
