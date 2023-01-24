import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final int[][] directions = new int[][] { {0,1}, {1,0}, {-1,0}, {0,-1}};

    public Rook(ChessBoard board, int x, int y, String color) {
        super(board, x,y,color);
        this.value = 5;
        this.identifier = (byte)(color.equals(Player.WHITE)?0x3:0x9);
    }

    public String toString() {
        return " R ";
    }

    // Return a list of all valid coordinates by this piece.
    @Override
    public List<int[]> getValidMoves() {
        int evalX, evalY;
        List<int[]> validMoves = new ArrayList<int[]>();
        for (int[] direction : directions) {
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
