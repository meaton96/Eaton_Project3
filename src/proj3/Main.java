package proj3;

import adt.LinkedStack;

public class Main {


    private static LinkedStack<Queen> queenStack;

    public static void main(String[] args) {

        queenStack = new LinkedStack<>();
        runScenario(5);
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.println(queenStack.itemAt(x) + "\n");
        }
    }

    public static boolean runScenario(int boardSize) {
        queenStack.push(new Queen(1,1));
        int startingCol = 1;
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
        return true;
    }
    public static boolean runSingleScenario(int boardSize, int startingCol, int startingRow, boolean wasRemoved) {
        Queen queen = new Queen(startingCol, startingRow);
        /* System.out.println("Stack: ");
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.println(x + ": " + queenStack.itemAt(x));
        }
        System.out.println("created new queen: " + queen);

         */
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
