import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        List<Move> moveList = new ArrayList<>();

        ChessBoard chessboard = new ChessBoard();

        Player blackPlayer = new Player(chessboard, Player.BLACK);
        Player whitePlayer = new Player(chessboard, Player.WHITE);

        chessboard.setWhitePlayer(whitePlayer);
        chessboard.setBlackPlayer(blackPlayer);

        String userMove;
        String[] splitUserMove, splitUserStart, splitUserEnd;
        Move attemptedMove;
        Player currentPlayer = whitePlayer;

        while (true) {
            chessboard.show();
            userMove = stdin.nextLine();
            splitUserMove = userMove.split(" ");
            splitUserStart = splitUserMove[0].split(",");
            splitUserEnd = splitUserMove[1].split(",");
            chessboard.showValidMoves(chessboard.getPieceAt(
                    Integer.parseInt(splitUserStart[0]),
                    Integer.parseInt(splitUserStart[1])).getValidMoves());

            attemptedMove = new Move(chessboard, currentPlayer,
                    Integer.parseInt(splitUserStart[0]),
                    Integer.parseInt(splitUserStart[1]),
                    Integer.parseInt(splitUserEnd[0]),
                    Integer.parseInt(splitUserEnd[1])
            );
            if (attemptedMove.moveSucceeded) {
                moveList.add(attemptedMove);
                currentPlayer = whitePlayer == currentPlayer ? blackPlayer : whitePlayer;
            } else {
                System.out.println("InvalidMove");
            }
        }
    }
}