import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private final int[][] directions = new int[][] {{-2,1}, {-1,2}, {-2,-1}, {-1,-2}, {2,1}, {2,-1}, {1,2}, {1,-2}};

    public Knight(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
    }

    public String toString() {
        return " N ";
    }

    @Override
    public List<int[]> getValidMoves() {
        List<int[]> validMoves = new ArrayList<>();
        for (int[] direction : directions) {
            if ((this.x+direction[0] > -1 && this.x+direction[0] < 8 && this.y+direction[1] > -1 && this.y+direction[1] < 8)
                    && ((board.getPieceAt(this.x+direction[0], this.y+direction[1]) == null) ||
                    !(board.getPieceAt(this.x+direction[0], this.y+direction[1]).getColor().equals(this.color)))) {
                validMoves.add(new int[] {this.x+direction[0], this.y+direction[1]});
            }
        }
        return validMoves;
    }
}
