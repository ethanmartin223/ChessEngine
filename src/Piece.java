import java.util.List;

public class Piece {

    protected int value;
    protected String color;
    protected int x,y;
    protected ChessBoard board;
    protected boolean hasMoved;
    protected Player player;
    protected byte identifier;


    public Piece(ChessBoard board, int x, int y, String color) {
        this.color = color;
        this.player = (color.equals(Player.WHITE))?board.getWhitePlayer():board.getBlackPlayer();
        this.x = x;
        this.y = y;
        this.board = board;
        this.hasMoved = false;
        this.value = 0;
        this.identifier = 0x00;
        assignSpace(x,y);
    }

    private void assignSpace(int x, int y) {
        this.x = x;
        this.y = y;
        board.getBoard()[y][x] = this;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String toString() {
        return "N/A";
    }

    private void moveBackend(int x, int y) {
        board.getBoard()[this.y][this.x] = null;
        this.x = x;
        this.y = y;
        if (board.getPieceAt(this.x, this.y)!=null) {
            ((color.equals(Player.BLACK))?board.getWhitePlayer():board.getBlackPlayer()
                    ).getPieceList().remove(board.getPieceAt(this.x, this.y));
        }
        board.getBoard()[this.y][this.x] = this;

    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return null;
    }

    /** Returns true if move succeeded, else returns false **/
    public boolean moveTo(int toX, int toY) {
        if (isValidMove(toX, toY)) {
            moveBackend(toX, toY);
            if (!hasMoved) hasMoved = true;
            return true;
        } else {
            return false;
        }
    }


    /** Returns True if the coordinates provided are valid moves for this piece. **/
    private boolean isValidMove(int toX, int toY) {
        if (getValidMoves() == null) {
            return false;
        }
        for (int[] validCoordinate : getValidMoves()) {
            if (validCoordinate[0] == toX && validCoordinate[1] == toY) {
                return true;
            }
        }
        return false;
    }
    public List<int[]> getValidMoves() {
        return null;
    }

    public byte getIdentifier() {
        return identifier;
    }
}

