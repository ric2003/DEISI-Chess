package pt.ulusofona.lp2.deisichess;

import java.util.Arrays;

public class Joker extends ChessPiece {

    public Joker(int iD, int color, String nome, boolean tasEmJogoFilho) {
        super(iD, 7, color, nome, tasEmJogoFilho, 4);
    }

    public boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) {
        int currentPieceID = chessboard.getQuemTaAJogar() % 6;
        switch (currentPieceID) {
            case 0:
                Rainha rainha = new Rainha(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return rainha.canMoveTo(chessboard, x0, y0, x1, y1);
            case 1:
                PoneiMagico poneiMagico = new PoneiMagico(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return poneiMagico.canMoveTo(chessboard, x0, y0, x1, y1);
            case 2:
                PadreDaVila padreDaVila = new PadreDaVila(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return padreDaVila.canMoveTo(chessboard, x0, y0, x1, y1);
            case 3:
                TorreHorizontal torreHorizontal = new TorreHorizontal(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return torreHorizontal.canMoveTo(chessboard, x0, y0, x1, y1);
            case 4:
                TorreVertical torreVertical = new TorreVertical(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return torreVertical.canMoveTo(chessboard, x0, y0, x1, y1);
            case 5:
                Homer homer = new Homer(this.iD, this.color, this.name, this.tasEmJogoFilho);
                return homer.canMoveTo(chessboard, x0, y0, x1, y1);
            default:
                return false;
        }
    }

    @Override
    public int[][] getPossibleMoves(int boardSize) {
        int maxMove = (boardSize - 1);
        int[][] possibleMoves = new int[(maxMove * 4) + 20][2];

        int index = 0;
        // Horizontal and vertical moves
        for (int i = 1; i <= maxMove; i++) {
            possibleMoves[index++] = new int[]{i, 0};
            possibleMoves[index++] = new int[]{-i, 0};
            possibleMoves[index++] = new int[]{0, i};
            possibleMoves[index++] = new int[]{0, -i};
        }

        int sizeRainha= Math.min((boardSize - 1), 5);
        for (int i = 1; i <= sizeRainha; i++) {
            possibleMoves[index++] = new int[]{i, i};
            possibleMoves[index++] = new int[]{-i, i};
            possibleMoves[index++] = new int[]{i, -i};
            possibleMoves[index++] = new int[]{-i, -i};
        }

        return Arrays.copyOf(possibleMoves, index);
    }

    @Override
    public ChessPiece cloneChessPiece() {
        Joker novaPeca = new Joker(this.iD, this.color, this.name, this.tasEmJogoFilho);
        novaPeca.setXandY(getX(), getY());
        novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
            getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
        return novaPeca;
    }

    @Override
    public String chessPiecePhoto(Board chessboard) {
        int currentPieceID = chessboard.getQuemTaAJogar() % 6;
        int jogadasHomer=chessboard.getQuemTaAJogar()%3;

        switch (currentPieceID) {
            case 0:
                return (color == 10) ? "black_Rainha.png" : "white_rainha.png";
            case 1:
                return (color == 10) ? "PoneyMagicoBlack.png" : "PoneyMagicoWhite.png";
            case 2:
                return (color == 10) ? "PadreDaVilaPreto.png" : "PadreDaVilaBranco.png";
            case 3:
                return (color == 10) ?"TorreHorizontalPreta.png" : "TorreHorizontalBranca.png";
            case 4:
                return (color == 10) ? "TorreVerticalPreta.png" : "TorreVerticalBranca.png";
            case 5:
                return (color == 10) ?((jogadasHomer==0)?"homer_black_sleeping.png":"homer_black.png"):
                        ((jogadasHomer==0)?"homer_white_sleeping.png":"homer_white.png");
            default:
                return "";
        }
    }


}