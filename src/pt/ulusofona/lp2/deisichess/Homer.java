package pt.ulusofona.lp2.deisichess;

import java.util.List;

public class Homer extends ChessPiece {



    public Homer(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 6, color, nome, tasEmJogoFilho, 2);
    }

    public boolean canMoveTo(Board chessboard,int x0, int y0, int x1, int y1) {
        if(isSleeping(chessboard)){
            return false;
        }
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        return (dx == 1 && dy == 1);
    }

    @Override
    int[][] getPossibleMoves(int boardSize) {

        return new int[][]{
            {1,1}, {1,-1}, {-1,1}, {-1,-1}
        };
    }

    @Override
    public ChessPiece cloneChessPiece() {
        Homer novaPeca= new Homer(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }
    @Override
    public boolean isSleeping(Board chessboard){
        int jogadas = chessboard.getQuemTaAJogar();
        if(jogadas%3==0){
            return true;
        }
        return false;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
       if (isSleeping(chessboard)){
           if(color==10){
               return "homer_black_sleeping.png";
           }else {
               return "homer_white_sleeping.png";
           }
       }else if(color==10){
           return "homer_black.png";
       }else {
           return "homer_white.png";
       }
    }

}