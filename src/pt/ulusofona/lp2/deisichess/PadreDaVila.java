package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class PadreDaVila extends ChessPiece {

    public PadreDaVila(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 3, color, nome, tasEmJogoFilho, 3);
    }
    public boolean canMoveTo(Board chessboard,int x0, int y0, int x1, int y1) {

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if (dx <= 3 && dy <= 3 && dx == dy) {
            int xStep = Integer.compare(x1, x0);
            int yStep = Integer.compare(y1, y0);

            int x = x0 + xStep;
            int y = y0 + yStep;

            while (x != x1 || y != y1) {
                if (chessboard.getChessPiece(x,y) != null) {
                    return false;
                }
                x += xStep;
                y += yStep;
            }
            return true;
        }

        return false;
    }

    @Override
    int[][] getPossibleMoves(int boardSize) {

        return new int[][]{
            {1, 1}, {2, 2}, {3, 3},
            {-1, 1}, {-2, 2}, {-3, 3},
            {1, -1}, {2, -2}, {3, -3},
            {-1, -1}, {-2, -2}, {-3, -3},
        };
    }

    @Override
    public ChessPiece cloneChessPiece() {
        PadreDaVila novaPeca= new PadreDaVila(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "PadreDaVilaPreto.png";
        }
        return "PadreDaVilaBranco.png";
    }

}