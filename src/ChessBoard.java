import java.util.List;

public class ChessBoard {
    private Piece[][] pieceArray;
    private Player whitePlayer;
    private Player blackPlayer;
    private int turnNumber;

    public ChessBoard() {
        pieceArray = new Piece[8][8];
        turnNumber = 1;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public boolean isWhitesTurn() {
        return (turnNumber%2)==1;
    }

    public void show() {
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                if ((x+y)%2==1) {
                    System.out.print("\u001B[47m"+(pieceArray[y][x]!=null?pieceArray[y][x]:"   ")+"\u001B[0m");
                }
                else {
                    System.out.print("\u001B[40m"+(pieceArray[y][x]!=null?pieceArray[y][x]:"   ")+"\u001B[0m");
                }
            }
            System.out.println(" "+y+" ");
        }
        System.out.println(" 0  1  2  3  4  5  6  7 \n\n");
    }

    public void showValidMoves(List<int[]> validMoves) {
        boolean inValidMoves;
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                inValidMoves = false;
                for (int[] move : validMoves) {
                    if (move[0] == x && move[1] == y) {
                        inValidMoves = true;
                    }
                }
                if (inValidMoves) {
                    System.out.print("\u001B[46m" + (pieceArray[y][x] != null ? pieceArray[y][x] : "   ") + "\u001B[0m");
                }
                else {
                    if ((x + y) % 2 == 1) {
                        System.out.print("\u001B[47m" + (pieceArray[y][x] != null ? pieceArray[y][x] : "   ") + "\u001B[0m");
                    } else {
                        System.out.print("\u001B[40m" + (pieceArray[y][x] != null ? pieceArray[y][x] : "   ") + "\u001B[0m");
                    }
                }
            }
            System.out.println(" "+y+" ");
        }
        System.out.println(" 0  1  2  3  4  5  6  7 \n\n");
    }

    public Piece[][] getBoard() {
        return pieceArray;
    }
    
    public Piece getPieceAt(int x, int y) {
        return pieceArray[y][x];
    }
}
