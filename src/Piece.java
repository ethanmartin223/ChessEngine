import java.util.List;

public class Piece {

    protected String color;
    protected int x,y;
    protected ChessBoard board;

    public Piece(ChessBoard board, int x, int y, String color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.board = board;
        assignSpace(x,y);
    }

    private void assignSpace(int x, int y) {
        this.x = x;
        this.y = y;
        board.getBoard()[y][x] = this;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String toString() {
        return "N/A";
    }

    public void moveTo(int x, int y) {
        board.getBoard()[this.y][this.x] = null;
        this.x = x;
        this.y = y;
        board.getBoard()[this.y][this.x] = this;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return null;
    }

    // Returns true if move succeeded, else returns false;
    // This way error handling is kept to strictly areas that need it.
    public boolean attemptMove(int toX, int toY) {
        if (isValidMove(toX, toY)) {
            //move piece
            moveTo(toX, toY);
            return true;

        } else {
            return false;
        }
    }

    //returns True if the coordinates provided are valid moves for this piece.
    public boolean isValidMove(int toX, int toY) {
        for (int[] validCoordinate : getValidMoves()) {
            if (validCoordinate[0] == toX && validCoordinate[1] == toY) {
                return true;
            }
        }
        return false;
    }
    public List<int[]> getValidMoves() {
        return null;
    }
}

