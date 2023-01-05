public class Move {
    public Piece piece;
    public ChessBoard board;
    public int[] startCoords;
    public int[] endCoords;
    public boolean moveSucceeded;
    public Piece takenPiece;
    public Player player;

    public Move(ChessBoard chessBoard, Player p, int startX, int startY, int endX, int endY) {
        startCoords = new int[] {startX, startY};
        endCoords = new int[] {endX, endY};
        board = chessBoard;
        player = p;
        if (startX < 8 && startX > -1 && startY < 8 && startY > -1) {
            piece = board.getPieceAt(startX, startY);
        } else piece = null;
        if ((endX < 8 && endX > -1) && (endY < 8 && endY > -1)) {
            takenPiece = board.getPieceAt(endX, endY);
        } else takenPiece = null;
        if (piece != null && piece.getColor().equals(player.getColor())) {
            moveSucceeded = piece.attemptMove(endX, endY);
        } else {
            moveSucceeded = false;
        }
    }
}
