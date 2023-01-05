import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";

    private String color;
    private List<Piece> pieceList;
    private ChessBoard board;

    public Player(ChessBoard chessboard, String color) {
        this.color = color;
        this.pieceList = new ArrayList<>();
        board = chessboard;
        initializePieces();
    }

    private void initializePieces() {
        boolean isWhite = (color==Player.WHITE);
        for (int x=0; x<8; x++) {
            //pieceList.add(new Pawn(board, x,isWhite?1:6,color));
        }
        pieceList.add(new Bishop(board,2,isWhite?0:7,color));
        pieceList.add(new Bishop(board,5,isWhite?0:7,color));
        pieceList.add(new Knight(board,1,isWhite?0:7,color));
        pieceList.add(new Knight(board,6,isWhite?0:7,color));
        pieceList.add(new Rook(board,0,isWhite?0:7,color));
        pieceList.add(new Rook(board,7,isWhite?0:7,color));
        pieceList.add(new Queen(board,3,isWhite?0:7,color));
        pieceList.add(new King(board,4,isWhite?0:7,color));
    }

    public String getColor() {
        return color;
    }
}
