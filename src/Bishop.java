import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    public static final int[][] DIRECTIONS = new int[][] {{1,1}, {1,-1}, {-1,1},{-1,-1}};

    public Bishop(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
        this.value = 3;
        this.identifier = color.equals(Player.WHITE)?BoardState.WHITE_BISHOP:BoardState.BLACK_BISHOP;
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
        for (int[] direction : DIRECTIONS) {
            evalX = this.x+direction[0];
            evalY = this.y+direction[1];

            while (!(evalX > 7) && !(evalX < 0) && !(evalY > 7) && !(evalY < 0)) {
                if ((board.getPieceAt(evalX, evalY) == null) || !(board.getPieceAt(evalX, evalY).getColor().equals(this.color))) {
                    validMoves.add(new int[] {evalX, evalY});
                } else if (board.getPieceAt(evalX, evalY).getColor().equals(this.color)) {
                    break;
                }
                evalX += direction[0];
                evalY += direction[1];
            }
        }
        return validMoves;
    }
}
