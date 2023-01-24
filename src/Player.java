import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";

    protected String color;
    protected List<Piece> pieceList;
    protected ChessBoard board;
    protected int score;
    protected boolean isComputerPlayer;

    public Player(ChessBoard chessboard, String color) {
        this.color = color;
        this.pieceList = new ArrayList<>();
        score = 0;
        board = chessboard;
        isComputerPlayer = false;
        initializePieces();
    }

    public void incrementScore(int n) {
        this.score += n;
    }

    /** Create new pieces for this Player's side of the board. **/
    private void initializePieces() {
        boolean isWhite = (color.equals(Player.WHITE));
        for (int x=0; x<8; x++) {
            pieceList.add(new Pawn(board, x,isWhite?1:6,color));
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

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public boolean isComputerPlayer() {
        return isComputerPlayer;
    }

    public boolean move(int startX, int startY, int endX, int endY) {
        Piece piece;
        boolean moveSucceeded;
        if (startX < 8 && startX > -1 && startY < 8 && startY > -1) {
            piece = board.getPieceAt(startX, startY);
        } else piece = null;
        if (piece != null && piece.getColor().equals(this.getColor())) {
            moveSucceeded = piece.moveTo(endX, endY);
        } else {
            moveSucceeded = false;
        }
        return moveSucceeded;
    }
}
