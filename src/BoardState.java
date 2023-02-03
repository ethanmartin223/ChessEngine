import java.util.*;

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

    public BoardState(byte[][] boardData) {
        data = new byte[8][8];
        for (int y=0; y< boardData.length; y++) {
            for (int x=0; x< boardData[0].length; x++) {
                data[y][x] = boardData[y][x];
            }
        }
    }

    public String toString() {
        return Arrays.deepToString(data).replace("], ", "], \n");
    }

    public float evaluateFitness() {
        return 0f;
    }

    public byte[][] getBoardData() {
        return data;
    }

    public void setValueOfPos(int x, int y, byte value) {
        data[y][x] = value;
    }

    public List<int[]> getMovesForPieceAt(int x, int y) {
        String pieceColor = getColorOfPieceAt(x,y);
        int evalX, evalY;
        if (data[y][x] == 0x0) {
            return null;
        } else {
            List<int[]> validMoves = new ArrayList<>();
            switch (data[y][x]) {
                case WHITE_QUEEN:
                case BLACK_QUEEN:
                    for (int[] direction : Queen.DIRECTIONS) {
                        evalX = x+direction[0];
                        evalY = y+direction[1];

                        while (!(evalX > 7) && !(evalX < 0) && !(evalY > 7) && !(evalY < 0)) {
                            if ((getPieceAt(evalX, evalY) == 0x0) || !(getColorOfPieceAt(evalX, evalY).equals(pieceColor))) {
                                validMoves.add(new int[] {evalX, evalY});
                            } else if (getColorOfPieceAt(evalX, evalY).equals(pieceColor)) {
                                break;
                            }
                            evalX += direction[0];
                            evalY += direction[1];
                        }
                    }
                    break;

                case WHITE_BISHOP:
                case BLACK_BISHOP:
                    for (int[] direction : Bishop.DIRECTIONS) {
                        evalX = x+direction[0];
                        evalY = y+direction[1];

                        while (!(evalX > 7) && !(evalX < 0) && !(evalY > 7) && !(evalY < 0)) {
                            if ((getPieceAt(evalX, evalY) == 0x0) || !(getColorOfPieceAt(evalX, evalY).equals(pieceColor))) {
                                validMoves.add(new int[] {evalX, evalY});
                            } else if (getColorOfPieceAt(evalX, evalY).equals(pieceColor)) {
                                break;
                            }
                            evalX += direction[0];
                            evalY += direction[1];
                        }
                    }
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
                    for (int[] direction : Rook.DIRECTIONS) {
                        evalX = x+direction[0];
                        evalY = y+direction[1];

                        while (!(evalX > 7) && !(evalX < 0) && !(evalY > 7) && !(evalY < 0)) {
                            if ((getPieceAt(evalX, evalY) == 0x0) || !(getColorOfPieceAt(evalX, evalY).equals(pieceColor))) {
                                validMoves.add(new int[] {evalX, evalY});
                            } else if (getColorOfPieceAt(evalX, evalY).equals(pieceColor)) {
                                break;
                            }
                            evalX += direction[0];
                            evalY += direction[1];
                        }
                    }
                    break;

                case WHITE_PAWN:
                case BLACK_PAWN:
                    int dir = pieceColor.equals(Player.WHITE)?1:-1;
                    if ((y == 1 && pieceColor.equals(Player.WHITE)) || (y ==6 && pieceColor.equals(Player.BLACK))) {
                        if (y == 1 && getPieceAt(x, y+2) ==0x0) {
                            validMoves.add(new int[] {x, y+2});
                        } else if (y == 6 && getPieceAt(x, y-2) == 0x0) {
                            validMoves.add(new int[] {x, y-2});
                        }
                    }
                    if (y+dir > -1 && y+dir < 8 &&
                            getPieceAt(x, y+dir) == 0x0) {
                        validMoves.add(new int[] {x, y+dir});
                    }
                    if (x-1 > -1 && y+dir > -1 && y+dir < 8 && x-1 < 8 &&
                            getPieceAt(x-1, y+dir) != 0x0 &&
                            !(getColorOfPieceAt(x - 1, y + dir).equals(pieceColor))) {
                        validMoves.add(new int[] {x-1, y+dir});
                    }
                    if (y+dir > -1 && y+dir < 8 && x+1 < 8 &&
                            getPieceAt(x+1, y+dir) != 0x0 &&
                            !(getColorOfPieceAt(x + 1, y + dir).equals(pieceColor))) {
                        validMoves.add(new int[] {x+1, y+dir});
                    }
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

    public Map<int[], List<int[]>> getAllPossibleMoves(String color) {
        Map<int[], List<int[]>> moveMap = new HashMap<>();
        boolean isWhite = color.equals(Player.WHITE);
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                if (data[y][x]!=0x0&&((isWhite && data[y][x]<0x9) || (!isWhite && data[y][x]>0x9))) {
                    moveMap.put(new int[] {x,y}, getMovesForPieceAt(x,y));
                }
            }
        }
        return moveMap;
    }

    public String getColorOfPieceAt(int x, int y) {
        return (data[y][x]>0x9)?Player.BLACK:Player.WHITE;
    }

    public byte getValueOfPieceAt(int x, int y) {
        return data[y][x];
    }
}
