package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends ChessPiece {
    public Rainha(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 1, color, nome, tasEmJogoFilho, 8);
    }

    public boolean canMoveTo(Board chessboard,int x0, int y0, int x1, int y1) {

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }
        if(chessboard.getChessPiece(x1,y1)!=null&&chessboard.getChessPiece(x1,y1).getType()==1) {
            return false;
        }
        boolean eAVezDaRainha=chessboard.getQuemTaAJogar()%6==0;
        if(chessboard.getChessPiece(x1,y1)!=null&&chessboard.getChessPiece(x1,y1).getType()==7&&eAVezDaRainha) {
            return false;
        }


        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if ((dx <= 5 && dy == 0) || (dy <= 5 && dx == 0) || (dx <= 5 && dx == dy)) {
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
            {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0},
            {-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0},
            {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
            {0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5},
            {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5},
            {-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5},
            {1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5},
            {-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}
        };
    }

    @Override
    public ChessPiece cloneChessPiece() {
        Rainha novaPeca= new Rainha(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "black_Rainha.png";
        }
        return "white_Rainha.png";
    }

}