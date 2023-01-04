import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private int[][] directions = new int[][] {{0,1}, {1,0}, {-1,0},{0,-1}};

    public Bishop(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
    }

    // Display piece symbol for showBoard function.
    public String toString() {
        return " B ";
    }

    // Return a list of all valid coordinates by this piece.
    @Override
    public List<int[]> getValidMoves() {
        int evalX, evalY;
        List<int[]> validMoves = new ArrayList<int[]>();
        for (int[] direction : directions) {
            evalX = 0;
            evalY = 0;

            while (!(evalX > 7) && !(evalX < 0) && !(evalY > 7) && !(evalY < 0)) {
                evalX += direction[0];
                evalY += direction[1];
                if (board.getPieceAt(evalX, evalY) == null || !(board.getPieceAt(evalX, evalY).getColor().equals(this.color))) {
                    validMoves.add(new int[] {evalX, evalY});
                }
            }
        }
        return validMoves;
    }
}
