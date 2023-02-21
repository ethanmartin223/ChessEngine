import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessboard = new ChessBoard();

        Player blackPlayer = new Player(chessboard, Player.BLACK, true);
        Player whitePlayer = new Player(chessboard, Player.WHITE, false);

        chessboard.setWhitePlayer(whitePlayer);
        chessboard.setBlackPlayer(blackPlayer);

        String userMove = null;
        String[] splitUserMove, splitUserStart, splitUserEnd;
        Player currentPlayer = whitePlayer;
        Scanner stdin = new Scanner(System.in);
        boolean moveSucceeded=false;
        while (true) {
            chessboard.show();
            if (!currentPlayer.isComputerPlayer()) {
                System.out.print("Move: ");
                userMove = stdin.nextLine();
            } else {
                BoardState currentBoardState = new BoardState(chessboard.getBoard());
                Node parentNode = new Node(0, currentBoardState);
                parentNode.generateMoveTree(currentPlayer.getColor(), 4);
                Node bestNode = Node.minimax(parentNode, Integer.MAX_VALUE, true);
                userMove= bestNode.getMove();
            }
            splitUserMove = userMove.split(" ");
            splitUserStart = splitUserMove[0].split(",");
            splitUserEnd = splitUserMove[1].split(",");
            moveSucceeded = currentPlayer.move(Integer.parseInt(splitUserStart[0]),
                    Integer.parseInt(splitUserStart[1]),
                    Integer.parseInt(splitUserEnd[0]),
                    Integer.parseInt(splitUserEnd[1])
            );

            if (moveSucceeded)currentPlayer = whitePlayer == currentPlayer ? blackPlayer : whitePlayer;
            else {
                System.out.println("InvalidMove");
            }
        }
    }
}
