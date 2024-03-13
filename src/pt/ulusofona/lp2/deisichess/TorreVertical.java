package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class TorreVertical extends ChessPiece {
    public TorreVertical(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 5, color, nome, tasEmJogoFilho, 3);
    }
    public boolean canMoveTo(Board chessboard,int x0, int y0, int x1, int y1) {

        if(!validator(chessboard,x0,y0,x1,y1)){
            return false;
        }
        int size=chessboard.getBoardSize();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if ((dx == 0 && dy < size) ) {
            int yStep = Integer.compare(y1, y0);

            int y = y0 + yStep;

            while (y != y1) {
                if (chessboard.getChessPiece(x0,y) != null) {
                    return false;
                }
                y += yStep;
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
            possibleMoves[index] = new int[]{0, i};
            index++;
            possibleMoves[index] = new int[]{0, -i};
            index++;
        }

        return possibleMoves;
    }

    @Override
    public ChessPiece cloneChessPiece() {
        TorreVertical novaPeca= new TorreVertical(this.iD,this.color,this.name,this.tasEmJogoFilho);
        novaPeca.setXandY(getX(),getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        if(color==10){
            return "TorreVerticalPreta.png";
        }
        return "TorreVerticalBranca.png";
    }

}