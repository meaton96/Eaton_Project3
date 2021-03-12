package proj3;

import adt.LinkedStack;

public class Main {


    private static LinkedStack<Queen> queenStack;

    public static void main(String[] args) {

        queenStack = new LinkedStack<>();
        int columnCount = 1;
        int boardSize = 4;
        int numSolutions = 0;
       /* while (runScenario(columnCount, boardSize) && columnCount <= boardSize) {
            for (int x = 0; x < queenStack.size(); x++) {
                System.out.print(queenStack.pop() + "\t");
            }
            System.out.println("\n");
            columnCount++;
            numSolutions++;
        }

        */
        for (int y = 1; y <= 2; y++) {
            runScenario(y, boardSize);

            //System.out.println("Number of solutions: " + numSolutions);
            int size  = queenStack.size();
            for (int x = 0; x < size; x++) {
                System.out.print(queenStack.pop() + "\t");
            }
            System.out.println();
        }
    }

    public static int runScenario(int start, int boardSize) {
        //queenStack.clear();
        queenStack.push(new Queen(start,1));
        int startingCol = start;
        int startingRow = 1;
        boolean wasRemoved = false;
        while (queenStack.size() < boardSize) {
            if (runSingleScenario(boardSize, startingCol, startingRow, wasRemoved)){
                startingRow = queenStack.peek().getBoardPosition().getRow();
                startingCol = 1;
                wasRemoved = false;
                continue;
            }
            startingCol = queenStack.peek()
                    .getBoardPosition()
                    .getCol() + 1;
            startingRow = queenStack.pop()
                    .getBoardPosition()
                    .getRow();
            wasRemoved = true;

            //System.out.println("removing queen: " + queenStack.peek());
        }
        return 1;
    }
    public static boolean runSingleScenario(int boardSize, int startingCol, int startingRow, boolean wasRemoved) {
        Queen queen = new Queen(startingCol, startingRow);
        /* System.out.println("Stack: ");
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.println(x + ": " + queenStack.itemAt(x));
        }

         */
        //System.out.println("created new queen: " + queen);


        boolean wasAdded = false;
        if (!wasRemoved)
            queen.getBoardPosition().incrementRow();

        while (queen.getBoardPosition().getCol() <= boardSize && !wasAdded) {

            boolean isConflict = false;
            for (int x = 0; x < queenStack.size(); x++) {
                //System.out.println("Trying queen: " + queen + " vs " + queenStack.itemAt(x));

                if (queen.conflicts(queenStack.itemAt(x))) {
                    isConflict = true;
                    break;
                }
            }
            if (!isConflict) {
                wasAdded = true;
                queenStack.push(queen);
            }
            if (!wasAdded)
                queen.getBoardPosition().incrementCol();
        }
        return wasAdded;
    }

}
