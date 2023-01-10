import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (this.y+direction > -1 && this.y+direction < 8 &&
                board.getPieceAt(this.x, this.y+direction) == null) {
            validMoves.add(new int[] {this.x, this.y+direction});
        }
        if (this.x-1 > -1 && this.y+direction > -1 && this.y+direction < 8 &&
                board.getPieceAt(this.x-1, this.y+direction) != null &&
                !(board.getPieceAt(this.x - 1, this.y + direction).getColor().equals(this.color))) {
            validMoves.add(new int[] {this.x-1, this.y+direction});
        }
        return validMoves;
    }
}
