public class Queen extends Piece {
    public Queen(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
    }

    public String toString() {
        return " Q ";
    }
}
