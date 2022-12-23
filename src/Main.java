public class Main {
    public static void main(String[] args) {

        ChessBoard chessboard = new ChessBoard();

        Player blackPlayer = new Player(chessboard, Player.BLACK);
        Player whitePlayer = new Player(chessboard, Player.WHITE);

        chessboard.setWhitePlayer(whitePlayer);
        chessboard.setBlackPlayer(blackPlayer);

        chessboard.show();
        chessboard.getPieceAt(0,0).moveTo(1,2);
        chessboard.show();


    }
}