public class King extends Piece {
    private int[][] directions = new int[][] {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {0,1}, {1,0}, {-1,0}, {0,-1}};

    public King(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
    }

    public String toString() {
        return " K ";
    }
}
