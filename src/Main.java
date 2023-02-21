import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessboard = new ChessBoard();

        Player blackPlayer = new Player(chessboard, Player.BLACK, true);
        Player whitePlayer = new Player(chessboard, Player.WHITE, false);

        chessboard.setWhitePlayer(whitePlayer);
        chessboard.setBlackPlayer(blackPlayer);

        BoardState currentBoardState = new BoardState(chessboard.getBoard());
        chessboard.show();


        Node parentNode = new Node(0, currentBoardState);

        parentNode.generateMoveTree(Player.WHITE, 5);
        Node bestNode = Node.minimax(parentNode, Integer.MAX_VALUE, true);
        System.out.println("\n\n"+bestNode+"\nScore of: "+bestNode.getBoard().evaluateFitness(Player.WHITE));
    }
}
