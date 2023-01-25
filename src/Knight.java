import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public static final int[][] DIRECTIONS = new int[][] {{-2,1}, {-1,2}, {-2,-1}, {-1,-2}, {2,1}, {2,-1}, {1,2}, {1,-2}};

    public Knight(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
        this.value = 3;
        identifier = color.equals(Player.WHITE)?BoardState.WHITE_KNIGHT:BoardState.BLACK_KNIGHT;
    }

    public String toString() {
        return " N ";
    }

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
