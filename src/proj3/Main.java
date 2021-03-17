package proj3;

import adt.LinkedStack;
import utility.BlindSearchIntermediate;

import java.util.EmptyStackException;

public class Main {


    private static LinkedStack<Queen> queenStack;

    public static void main(String[] args) {

        queenStack = new LinkedStack<>();
        run(4);
        
        //runScenario(5);

        //BlindSearchIntermediate.search(8, 100000, false);
    }
    public static void run(int boardSize) {

        Queen baseQueen = new Queen(1,1);
        int numSolutions = 0;
        queenStack.push(baseQueen);
        int startCol = 1;
        int startRow = 2;
        while (true) {
            while (queenStack.size() < boardSize) {
                Queen queen = new Queen(startCol, startRow);

                System.out.println("created queen at " + queen);

                while (queen.isOnBoard(boardSize)) {
                    System.out.println("testing queen: " + queen);
                    if (queen.conflicts(queenStack)) {
                        queen.getBoardPosition().incrementCol();
                        continue;
                    }
                    System.out.println("adding Queen at: " + queen);
                    queenStack.push(queen);
                    queen = new Queen(startCol, queen.getBoardPosition().getRow());
                    queen.getBoardPosition().incrementRow();

                }
                if (queenStack.peek().equals(baseQueen)) {
                    baseQueen.getBoardPosition().incrementCol();
                    continue;
                }
                queenStack.pop();
            }
            if (queenStack.isEmpty()) {
                System.out.println("Number of Solutions: " + numSolutions);
                return;
            }
            System.out.println("solution: " + ++numSolutions + "\n");
            for (int x = 0; x < boardSize; x++) {
                System.out.print(queenStack.itemAt(x));
            }
            queenStack.pop();
            startCol++;
            //infinite loop

        }



    }






    public static void runScenario(int boardSize) {
        int start = 1;
        int startingCol = start;
        int startingRow = 1;
        boolean wasRemoved = false;
        int solCount = 0;
        Queen queen = new Queen(start, startingRow);
        while (true) {
            queenStack.push(queen);
         //   for (int x = 0; x < queenStack.size(); x++)
           //     System.out.print(queenStack.itemAt(x) + "\t");
            //System.out.println();
            while (queenStack.size() < boardSize) {
              //  System.out.print("stack:\t");
                /*for (int x = 0; x < queenStack.size(); x++) {
                    System.out.print(queenStack.itemAt(x) + "\t");
                }*/
              //  System.out.println();
                if (runSingleScenario(boardSize, startingCol, startingRow, wasRemoved)) {
                    startingRow = queenStack.peek()
                            .getBoardPosition()
                            .getRow();
                    startingCol = 1;
                    wasRemoved = false;
                    continue;
                }
                try {
                   // System.out.println("Removed queen at: " + queenStack.peek());
                    startingCol = queenStack.peek()
                            .getBoardPosition()
                            .getCol() + 1;
                    startingRow = queenStack.pop()
                            .getBoardPosition()
                            .getRow();

                    wasRemoved = true;
                } catch (EmptyStackException e) {
                    System.out.println("Solution count: " + solCount);
                    return;
                }
            }
            /*for (int x = 1; x < boardSize; x++) {
                if (queenStack.itemAt(x).getBoardPosition().getRow() == 1) {
                    start = queenStack.itemAt(x).getBoardPosition().getCol();
                    break;
                }
            }
            start = queenStack.itemAt(boardSize - 1)
                    .getBoardPosition()
                    .getCol() + 1;
            */
            System.out.println("Solution: " + ++solCount);


            for (int x = 0; x < boardSize; x++) {
                System.out.print(queenStack.itemAt(x) + "\t");
            }

            /*for (int x = 0; x < boardSize - 2; x++) {
                queenStack.pop();
            }*/
           /* System.out.println("queen: " + queen);
            System.out.print("\nS2: ");
            for (int x = 0; x < queenStack.size(); x++) {
                System.out.print(queenStack.itemAt(x) + "\t");
            }
            if (start > boardSize)
                start = 1;
            */
            while (queenStack.peek().getBoardPosition().getCol() + 1 > boardSize)
                queen = queenStack.pop();
            if (queenStack.isEmpty()) {
                System.out.println("Solution count: " + solCount);
                return;
            }

            try {
                new Thread().sleep(1000);
            } catch (Exception e) {}
            queen.getBoardPosition().incrementCol();
            

            System.out.println("\n");

            //System.out.println("removing queen: " + queenStack.peek());
        }
        //System.out.println("Solution count: " + solCount);
    }

    public static boolean runSingleScenario(int boardSize, int startingCol, int startingRow, boolean wasRemoved) {
        if (startingCol > boardSize || startingRow > boardSize)
            return false;

        Queen queen = new Queen(startingCol, startingRow);
        /* System.out.println("Stack: ");
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.println(x + ": " + queenStack.itemAt(x));
        }

         */
      //  System.out.println("created new queen: " + queen);


        boolean wasAdded = false;
        if (!wasRemoved)
            queen.getBoardPosition().incrementRow();

        while (queen.getBoardPosition().getCol() <= boardSize && !wasAdded) {

            boolean isConflict = false;
            for (int x = 0; x < queenStack.size(); x++) {
                // System.out.println("Trying queen: " + queen + " vs " + queenStack.itemAt(x));

                if (queen.conflicts(queenStack.itemAt(x))) {
                    isConflict = true;
                    break;
                }
            }
            if (!isConflict) {
                wasAdded = true;
                queenStack.push(queen);
               // System.out.println("added queen: " + queenStack.peek());
            }
            if (!wasAdded)
                queen.getBoardPosition().incrementCol();
        }
        return wasAdded;
    }

}
