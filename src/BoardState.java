import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardState {
    private int value;
    private byte[][] data;

    //to save memory this could be removed at the end, tho does not really matter
    public static final byte WHITE_QUEEN = 0x1;
    public static final byte WHITE_BISHOP = 0x2;
    public static final byte WHITE_ROOK = 0x3;
    public static final byte WHITE_KNIGHT = 0x4;
    public static final byte WHITE_PAWN = 0x5;
    public static final byte WHITE_KING = 0x6;
    public static final byte BLACK_QUEEN = 0xA;
    public static final byte BLACK_BISHOP = 0xB;
    public static final byte BLACK_ROOK = 0xC;
    public static final byte BLACK_KNIGHT = 0xD;
    public static final byte BLACK_PAWN = 0xE;
    public static final byte BLACK_KING = 0xF;

    //BOARD EVALUATION FUNCTIONS
    public static final double KNIGHT_CENTERED_MODIFIER = 0.10; //knights near center is better than off to side
    public static final double ATTACKED_SQUARES_MODIFIER = 0.45; // Number of squares that are being attacked
    public static final double ATTACKING_WITHOUT_REPERCUSSIONS_MODIFIER = 0.85; // Number of squares that could be taken without then losing that piece
    public static final double PINNED_PIECE_MODIFIER = 0.05; //number of pinned pieces on opposing side.
    public static final double ATTACKING_CHECK_MODIFIER = 1; // opposing player put in check

    public static final double IN_CHECK_MODIFIER = -1;
    public static final double NUMBER_OF_THREATENED_PIECES_MODIFIER = -0.35; // pieces being attacked. modifier




    public BoardState(Piece[][] boardData) {
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

    public float evaluateFitness() {
        return 0f;
    }

    public List<int[]> getMovesForPieceAt(int x, int y) {
        String pieceColor = getColorOfPieceAt(x,y);
        if (data[y][x] == 0x0) {
            return null;
        } else {
            List<int[]> validMoves = new ArrayList<>();
            switch (data[y][x]) {
                case WHITE_QUEEN:
                case BLACK_QUEEN:
                    break;

                case WHITE_BISHOP:
                case BLACK_BISHOP:
                    break;

                case WHITE_KING:
                case BLACK_KING:
                    for (int[] direction : King.DIRECTIONS) {
                        if ((x+direction[0] > -1 && x+direction[0] < 8 && y+direction[1] > -1 && y+direction[1] < 8)
                                && ((this.getPieceAt(x+direction[0], y+direction[1]) == 0x0) ||
                                (!(this.getColorOfPieceAt(x+direction[0], y+direction[1]).equals(pieceColor))))) {
                            validMoves.add(new int[] {x+direction[0], y+direction[1]});
                        }
                    }
                    break;

                case WHITE_ROOK:
                case BLACK_ROOK:
                    break;

                case WHITE_PAWN:
                case BLACK_PAWN:
                    break;

                case WHITE_KNIGHT:
                case BLACK_KNIGHT:
                    for (int[] direction : Knight.DIRECTIONS) {
                        if ((x+direction[0] > -1 && x+direction[0] < 8 && y+direction[1] > -1 && y+direction[1] < 8)
                                && ((this.getPieceAt(x+direction[0], y+direction[1]) == 0x0) ||
                                (!this.getColorOfPieceAt(x+direction[0], y+direction[1]).equals(pieceColor)))) {
                            validMoves.add(new int[] {x+direction[0], y+direction[1]});
                        }
                    }
                    break;
            }
            return validMoves;
        }
    }

    private byte getPieceAt(int x, int y) {
        return data[y][x];
    }

    public List<int[]> getAllPossibleMoves(String color) {
        List<int[]> possibleMoves = new ArrayList<int[]>();
        boolean isWhite = color.equals(Player.WHITE);
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                if (data[y][x]!=0x0&&((isWhite && data[y][x]<0x9)|| (!isWhite && data[y][x]>0x9))) {
                    possibleMoves.addAll(getMovesForPieceAt(x,y));
                }
            }
        }
        return possibleMoves;
    }

    public String getColorOfPieceAt(int x, int y) {
        return (data[y][x]>0x9)?Player.BLACK:Player.WHITE;
    }
}
