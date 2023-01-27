import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessboard = new ChessBoard();

        Player blackPlayer = new Player(chessboard, Player.BLACK);
        Player whitePlayer = new Player(chessboard, Player.WHITE);

        chessboard.setWhitePlayer(whitePlayer);
        chessboard.setBlackPlayer(blackPlayer);

        whitePlayer.move(7,1, 7,3);
        whitePlayer.move(4,1, 4,3);


        BoardState currentBoardState = new BoardState(chessboard.getBoard());
        chessboard.show();
    }
}
