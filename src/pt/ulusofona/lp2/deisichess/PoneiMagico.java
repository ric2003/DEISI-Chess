package pt.ulusofona.lp2.deisichess;


public class PoneiMagico extends ChessPiece {

    public PoneiMagico(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 2, color, nome, tasEmJogoFilho, 5);
    }

    public boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        if (Math.abs(dx) != 2 || Math.abs(dy) != 2) {
            return false;
        }

        int midX = x0 + dx / 2;
        int midY = y0 + dy / 2;

        boolean canMoveHorizontally = chessboard.getChessPiece(midX, y0) == null &&
                chessboard.getChessPiece(x1, y0) == null;
        boolean canMoveVertically = chessboard.getChessPiece(x0, midY) == null &&
                chessboard.getChessPiece(x0, y1) == null;

        return (canMoveHorizontally && chessboard.getChessPiece(x1, midY) == null) ||
                (canMoveVertically && chessboard.getChessPiece(midX, y1) == null);
    }

    @Override
    int[][] getPossibleMoves(int boardSize) {

        return new int[][]{
            {2, 2}, {2, -2}, {-2, 2}, {-2, -2}
        };
    }

    @Override
    public ChessPiece cloneChessPiece() {
        PoneiMagico novaPeca= new PoneiMagico(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "PoneyMagicoBlack.png";
        }
        return "PoneyMagicoWhite.png";
    }

}
/*
public boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) {//old one

        int dx = x1 - x0;
        int dy = y1 - y0;
        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        boolean canMoveHorizontally = false;
        boolean canMoveVertically = false;
        boolean canMoveHorizontallyAfterVertically = false;
        boolean canMoveVerticallyAfterHorizontally = false;

        if ((Math.abs(dx) == 2 && Math.abs(dy) == 2) && ((x0 + 2 == x1) || (x0 - 2 == x1) || (y0 + 2 == y1) || (y0 - 2 == y1))) {
            if (dx == 2 || dx == -2) { // Moving horizontally first
                int midX = x0 + (dx / 2); // X-coordinate of the middle position

                if (dx > 0 && chessboard.getChessPiece(midX,y0) == null && chessboard.getChessPiece(x0 + dx,y0) == null||
                    dx < 0 && chessboard.getChessPiece(midX,y0) == null && chessboard.getChessPiece(x0 - 2,y0) == null) {
                    canMoveHorizontally = true; // Can move horizontally without obstruction
                }
            }
            if (dy == 2 || dy == -2) { // Moving vertically first
                int midY = y0 + (dy / 2); // Y-coordinate of the middle position

                if (dy > 0 &&chessboard.getChessPiece(x0,midY)== null && chessboard.getChessPiece(x0,y0 + dy) == null||
                    dy < 0 && chessboard.getChessPiece(x0,midY) == null && chessboard.getChessPiece(x0,y0 - 2)== null) {
                    canMoveVertically = true; // Can move vertically without obstruction
                }
            }
        }
        if (canMoveHorizontally && (dy == 2 || dy == -2)) { // Moving vertically first
            int midY = y0 + (dy / 2); // Y-coordinate of the middle position

            if (dx > 0 && chessboard.getChessPiece(x0 + 2,midY) == null||dx < 0 && chessboard.getChessPiece(x0 - 2,midY)== null) {
                canMoveVerticallyAfterHorizontally = true; // Can move vertically without obstruction
            }
        }
        if (canMoveVertically && (dx == 2 || dx == -2)) { // Moving horizontally first
            int midX = x0 + (dx / 2); // X-coordinate of the middle position

            if (dy > 0 && chessboard.getChessPiece(midX,y0 + 2) == null||dy < 0 && chessboard.getChessPiece(midX,y0 - 2) == null) {
                canMoveHorizontallyAfterVertically = true; // Can move horizontally without obstruction
            }
        }
        return (canMoveVertically && canMoveHorizontallyAfterVertically) || (canMoveHorizontally && canMoveVerticallyAfterHorizontally);
    }
 */