package proj3;

import adt.LinkedStack;

public class Queen {

    private final BoardPosition boardPosition;
    
    public Queen(int x, int y) {
        boardPosition = new BoardPosition(x, y);
    }
    
    /**
     *
     * @param otherQueen
     * @return
     */
    public boolean conflicts(Queen otherQueen) {
        if (otherQueen.boardPosition.getCol() == boardPosition.getCol() ||
            otherQueen.boardPosition.getRow() == boardPosition.getRow()) {
            return true;
        }
        BoardPosition testPosition = boardPosition.copy();
        while (testPosition.getRow() > 0 && testPosition.getCol() > 0) {
            testPosition.decrementPos();
            if (testPosition.equals(otherQueen.boardPosition)) {
                return true;
            }
        }
        testPosition = boardPosition.copy();
        while (testPosition.getRow() > 0) {
            testPosition.decrementRow();
            testPosition.incrementCol();
            if (testPosition.equals(otherQueen.boardPosition)) {
                return true;
            }
        }
        return false;
    }
    public boolean conflicts(LinkedStack<Queen> queenStack) {
        for (int x = 0; x < queenStack.size(); x++) {
            if (conflicts(queenStack.itemAt(x)))
                return true;
        }
        return false;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    @Override
    public String toString() {
        return boardPosition.toString();
    }
    public boolean isOnBoard(int max) {
        return boardPosition.getCol() <= max && boardPosition.getRow() <= max;
    }
}
