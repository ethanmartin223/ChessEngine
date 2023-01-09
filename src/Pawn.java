import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private int direction;

    public Pawn(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
        if (this.y == 1) {
            direction = 1;
        } else if (this.y == 6) {
            direction = -1;
        }
    }

    public String toString() {
        return " p ";
    }

    @Override
    public List<int[]> getValidMoves() {
        List<int[]> validMoves = new ArrayList<int[]>();
        if (!this.hasMoved) {
            if (this.y == 1 && board.getPieceAt(this.x, this.y+2) ==null) {
                validMoves.add(new int[] {this.x, this.y+2});
            } else if (this.y == 6 && board.getPieceAt(this.x, this.y-2) ==null) {
                validMoves.add(new int[] {this.x, this.y-2});
            }
        }
        if (direction == -1  &&)
        return validMoves;
    }
}
