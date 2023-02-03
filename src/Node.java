import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private final Node parent;
    private ArrayList<Node> children;
    private int value;
    private int nodeDepth;
    private BoardState board;

    public Node(int value, BoardState board) {
        this.parent = null;
        this.children = new ArrayList<>();
        this.nodeDepth = 0;
        this.value = value;
        this.board = board;
    }

    public Node(int value, BoardState board, Node parent) {
        this.parent = parent;
        this.children = new ArrayList<>();
        this.nodeDepth = parent.getDepth()+1;
        this.value = value;
        this.board = board;
    }

    public static Node getInstance() {return null;}

    public void addChild(int v, BoardState board) {
        this.children.add(new Node(v, board, this));
    }

    public List<Node> getChildren() {return children.size()>0?children:null;}

    public Node getParent() {return parent;}

    public int getValue() {return value;};

    public static Node minimax(Node startingNode, int depth, boolean isMaxingPlayer) {
        if ((startingNode.getDepth() == depth) || (startingNode.getChildren() == null)) {
            return startingNode;
        }
        int comparisonValue = isMaxingPlayer?Integer.MIN_VALUE:Integer.MAX_VALUE;
        int currentNodeValue;
        Node bestNode = null;
        for (Node n: startingNode.getChildren()) {
            currentNodeValue = minimax(n, n.getDepth(), !isMaxingPlayer).getValue();
            if (isMaxingPlayer?(comparisonValue < currentNodeValue):(comparisonValue > currentNodeValue)) {
                comparisonValue = currentNodeValue;
                bestNode = n;
            }
        }
        return bestNode;
    }

    public static Node minimax(Node startingNode, boolean isMaxingPlayer) {
        return minimax(startingNode, Integer.MAX_VALUE, isMaxingPlayer);
    }

    public String toString() {
        return board.toString();
    }

    private int getDepth() {
        return nodeDepth;
    }

    public void generateNodes(String color) {
        BoardState newBoardState;
        Map<int[], List<int[]>> pieceMap = this.board.getAllPossibleMoves(color);
        for (int[] key : pieceMap.keySet()) {
            for (int[] move : pieceMap.get(key)) {
                newBoardState = new BoardState(board.getBoardData());
                newBoardState.setValueOfPos(move[0], move[1], newBoardState.getValueOfPieceAt(key[0], key[1]));
                newBoardState.setValueOfPos(key[0], key[1], (byte)0x0);
                this.addChild(0, newBoardState);
            }
        }
    }
}
