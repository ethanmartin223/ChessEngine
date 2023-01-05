import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
    }

    public String toString() {
        return " p ";
    }

}
