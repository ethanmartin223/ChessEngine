public class Move {
    public String pieceName;
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
        piece = board.getPieceAt(startX, startY);
        pieceName = piece.getName();
        takenPiece = board.getPieceAt(endX, endY);

        if (piece.getColor().equals(player.getColor())) {
            moveSucceeded = piece.attemptMove(endX, endY);
        } else {
            moveSucceeded = false;
        }
    }
}
