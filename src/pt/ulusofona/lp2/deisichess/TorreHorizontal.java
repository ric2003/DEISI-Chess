package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends ChessPiece {
    public TorreHorizontal(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 4, color, nome, tasEmJogoFilho, 3);
    }

    public boolean canMoveTo(Board chessboard,int x0, int y0, int x1, int y1) {

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int size=chessboard.getBoardSize();

        if ((dx < size && dy == 0)) {
            int xStep = Integer.compare(x1, x0);

            int x = x0 + xStep;

            while (x != x1) {
                if (chessboard.getChessPiece(x,y0) != null) {
                    return false;
                }
                x += xStep;
            }
            return true;
        }

        return false;
    }

    @Override
    public int[][] getPossibleMoves(int boardSize) {
        int maxMove = (boardSize - 1);
        int[][] possibleMoves = new int[maxMove * 2][2];

        int index = 0;
        for (int i = 1; i <= maxMove; i++) {
            possibleMoves[index] = new int[]{i, 0};
            index++;
            possibleMoves[index] = new int[]{-i, 0};
            index++;
        }

        return possibleMoves;
    }

    @Override
    public ChessPiece cloneChessPiece() {
        TorreHorizontal novaPeca= new TorreHorizontal(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "TorreHorizontalPreta.png";
        }
        return "TorreHorizontalBranca.png";
    }

}