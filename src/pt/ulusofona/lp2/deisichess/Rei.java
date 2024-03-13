package pt.ulusofona.lp2.deisichess;

public class Rei extends ChessPiece {
    public Rei(int iD, int color,String nome, boolean tasEmJogoFilho) {
        super(iD, 0, color, nome, tasEmJogoFilho,1000);
    }

    public boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
    }

    @Override
    int[][] getPossibleMoves(int boardSize) {

        return new int[][]{
            {1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}
        };
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "black_king.png";
        }
        return "white_king.png";
    }

    @Override
    public ChessPiece cloneChessPiece() {
        Rei novaPeca= new Rei(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

}