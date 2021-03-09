package proj3;

import adt.LinkedStack;

public class Main {


    private static LinkedStack<Queen> queenStack;

    public static void main(String[] args) {

        queenStack = new LinkedStack<>();
        runScenario(4);
        for (int x = 0; x < queenStack.size(); x++) {
            System.out.println(queenStack.pop() + "\n");
        }
    }
    /////infinite loop
    public static boolean runScenario(int boardSize) {
        queenStack.push(new Queen(1,1));

        while (queenStack.size() <= boardSize) {
            Queen queen = new Queen(queenStack.peek().getBoardPosition());
            queen.getBoardPosition().incrementRow();

            while (queen.getBoardPosition().getCol() <= boardSize) {
                queen.getBoardPosition().incrementCol();

                for (int x = 0; x < queenStack.size(); x++) {
                    if (!queen.conflicts(queenStack.itemAt(x))) {
                        queenStack.push(queen);
                        break;
                    }
                }

            }
            queenStack.pop();
        }
        return true;
    }

}
