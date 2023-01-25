import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public static final int[][] DIRECTIONS = new int[][] {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {0,1}, {1,0}, {-1,0}, {0,-1}};

    public King(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
        this.value = Integer.MAX_VALUE;
        identifier = color.equals(Player.WHITE)?BoardState.WHITE_KING:BoardState.BLACK_KING;
    }

    public String toString() {
        return " K ";
    }

    // Return a list of all valid coordinates by this piece.
    @Override
    public List<int[]> getValidMoves() {
        List<int[]> validMoves = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            if ((this.x+direction[0] > -1 && this.x+direction[0] < 8 && this.y+direction[1] > -1 && this.y+direction[1] < 8)
                    && ((board.getPieceAt(this.x+direction[0], this.y+direction[1]) == null) ||
                    !(board.getPieceAt(this.x+direction[0], this.y+direction[1]).getColor().equals(this.color)))) {
                validMoves.add(new int[] {this.x+direction[0], this.y+direction[1]});
            }
        }
        return validMoves;
    }
}
