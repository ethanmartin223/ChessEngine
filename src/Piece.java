public class Piece {

    private String color;
    private int x,y;
    private ChessBoard board;

    public Piece(ChessBoard board, int x, int y, String color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.board = board;
        goTo(x,y);
    }

    private void goTo(int x, int y) {
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

    public void moveTo(int x, int y) {
        board.getBoard()[this.y][this.x] = null;
        this.x = x;
        this.y = y;
        board.getBoard()[this.y][this.x] = this;
    }
}
