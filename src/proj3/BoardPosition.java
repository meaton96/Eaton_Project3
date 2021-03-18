package proj3;

public class BoardPosition {

    private int row, col;


    public BoardPosition(int x, int y) {
        row = y;
        col = x;
    }

    public void incrementRow() { row++; }
    public void incrementCol() { col++; }
    public void decrementRow() { row--; }
    public void decrementPos() { col--; row--; }
    public void incrementPos() { col++; row++; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public String toString() { return "Row: " + row + " Col: " + col; }
    public boolean equals(BoardPosition other) { return other.row == row && other.col == col; }
    public BoardPosition copy() { return new BoardPosition(col, row); }


}
