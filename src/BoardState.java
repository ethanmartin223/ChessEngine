import java.util.Arrays;

public class BoardState {
    private int value;
    private byte[][] data;
    private String turnColor;

    public static final byte WHITE_QUEEN = 0x1;
    public static final byte WHITE_BISHOP = 0x2;
    public static final byte WHITE_ROOK = 0x3;
    public static final byte WHITE_KNIGHT = 0x4;
    public static final byte WHITE_PAWN = 0x5;
    public static final byte WHITE_KING = 0x6;
    public static final byte BLACK_QUEEN = 0x7;
    public static final byte BLACK_BISHOP = 0x8;
    public static final byte BLACK_ROOK = 0x9;
    public static final byte BLACK_KNIGHT = 0xA;
    public static final byte BLACK_PAWN = 0xB;
    public static final byte BLACK_KING = 0xC;

    public BoardState(Piece[][] boardData, String turnColor) {
        this.turnColor = turnColor;
        data = new byte[8][8];
        for (int y=0; y< boardData.length; y++) {
            for (int x=0; x< boardData[0].length; x++) {
                if (boardData[y][x] != null) {
                    data[y][x] = boardData[y][x].getIdentifier();
                } else data[y][x] = 0x0;
            }
        }
    }

    public String toString() {
        return Arrays.deepToString(data).replace("], ", "], \n");
    }
}
