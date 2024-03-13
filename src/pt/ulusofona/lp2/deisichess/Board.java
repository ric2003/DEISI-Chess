package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int boardSize;
    private int numPieces;
    private ChessPiece[][] piecesOnBoard;
    private HashMap<Integer,ChessPiece> gamePieces;
    private int quemTaAJogar;
    private int nrCapurasB;
    private int jogadasValidasB;
    private int tentativasInvalidasB;
    private int nrCapurasP;
    private int jogadasValidasP;
    private int tentativasInvalidasP;
    private int countNumJogadasSemCapturas;
    private boolean primeiraCaptura;

    Board(int boardSize,int numPieces) {
        this.boardSize = boardSize;
        this.numPieces = numPieces;
        this.gamePieces=new HashMap<>();
        this.quemTaAJogar = 0;
        this.countNumJogadasSemCapturas = 0;
        this.primeiraCaptura = false;
    }
    public Board cloneBoard() {
        Board clone = new Board(boardSize, numPieces);
        ChessPiece[][] clonedBoard = new ChessPiece[boardSize][boardSize];
        HashMap<Integer, ChessPiece> clonedGamePieces = new HashMap<>();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (piecesOnBoard[i][j] != null) {
                    ChessPiece originalPiece = piecesOnBoard[i][j];
                    ChessPiece clonedPiece = originalPiece.cloneChessPiece();
                    clonedBoard[i][j] = clonedPiece;
                    clonedGamePieces.put(originalPiece.getiD(), clonedPiece);
                }
            }
        }
        for (Map.Entry<Integer, ChessPiece> entry : gamePieces.entrySet()) {
            int id = entry.getKey();
            ChessPiece originalPiece = entry.getValue();
            if (!clonedGamePieces.containsKey(id)) {
                ChessPiece clonedPiece = originalPiece.cloneChessPiece();
                clonedGamePieces.put(id, clonedPiece);
            }
        }

        clone.setPiecesOnBoard(clonedBoard);
        clone.gamePieces = clonedGamePieces;
        clone.numPieces = this.numPieces;
        clone.quemTaAJogar = this.quemTaAJogar;
        clone.nrCapurasB = this.nrCapurasB;
        clone.jogadasValidasB = this.jogadasValidasB;
        clone.tentativasInvalidasB = this.tentativasInvalidasB;
        clone.nrCapurasP = this.nrCapurasP;
        clone.jogadasValidasP = this.jogadasValidasP;
        clone.tentativasInvalidasP = this.tentativasInvalidasP;
        clone.countNumJogadasSemCapturas = this.countNumJogadasSemCapturas;
        clone.primeiraCaptura = this.primeiraCaptura;

        return clone;
    }
    public void updateBoardState(Board newBoardState) {
        this.boardSize = newBoardState.getBoardSize();
        this.numPieces = newBoardState.getNumPieces();
        this.piecesOnBoard = newBoardState.getPiecesOnBoard();
        this.gamePieces = newBoardState.getGamePieces();
        this.quemTaAJogar = newBoardState.getQuemTaAJogar();
        this.nrCapurasB = newBoardState.getNrCapurasB();
        this.jogadasValidasB = newBoardState.getJogadasValidasB();
        this.tentativasInvalidasB = newBoardState.getTentativasInvalidasB();
        this.nrCapurasP = newBoardState.getNrCapurasP();
        this.jogadasValidasP = newBoardState.getJogadasValidasP();
        this.tentativasInvalidasP = newBoardState.getTentativasInvalidasP();
        this.countNumJogadasSemCapturas = newBoardState.getCountNumJogadasSemCapturas();
        this.primeiraCaptura = newBoardState.getPrimeiraCaptura();
    }
    int getBoardSize() {
        return boardSize;
    }
    void resetJogadasStats(){
        this.nrCapurasB = 0;
        this.jogadasValidasB = 0;
        this.tentativasInvalidasB = 0;
        this.nrCapurasP = 0;
        this.jogadasValidasP = 0;
        this.tentativasInvalidasP = 0;
    }
    public HashMap<Integer, ChessPiece> getGamePieces() {
        return gamePieces;
    }
    public int getNumberOfPieces(){
        return gamePieces.size();
    }
    public ChessPiece getChessPiece(int x,int y){
        return piecesOnBoard[y][x];
    }
    public int getNrCapturasB() {
        return nrCapurasB;
    }

    public int getJogadasValidasB() {
        return jogadasValidasB;
    }

    public int getTentativasInvalidasB() {
        return tentativasInvalidasB;
    }
    public int getQuemTaAJogar(){
        return quemTaAJogar;
    }
    public int equipaAJogar(){
        return quemTaAJogar%2==0?10:20;
    }

    public int getNrCapurasP() {
        return nrCapurasP;
    }
    public int getNrCapurasB(){
        return nrCapurasB;
    }

    public int getJogadasValidasP() {
        return jogadasValidasP;
    }

    public int getTentativasInvalidasP() {
        return tentativasInvalidasP;
    }

    public void pretoCaptura() {
        nrCapurasP++;
    }
    public void brancoCaptura() {
        nrCapurasB++;
    }
    public void jogadaSemCaptura() {
        countNumJogadasSemCapturas++;
    }
    public void jogadasInvalidasBranco() {
        tentativasInvalidasB++;
    }
    public void jogadasInvalidasPreto() {
        tentativasInvalidasP++;
    }

    public void jogadasValidasBranco() {
        jogadasValidasB++;
    }
    public void jogadasValidasPreto() {
        jogadasValidasP++;
    }
    public void vezDaProximaEquipa(){
        quemTaAJogar++;
    }

    int getNumPieces() {
        return numPieces;
    }

    void setNumPiecesOnBoard(int numPieces) {
        this.numPieces = numPieces;
    }
    void setpieceonborad(int x, int y,ChessPiece piece){
        this.piecesOnBoard[y][x]=piece;
    }
    ChessPiece[][] getPiecesOnBoard() {
        return piecesOnBoard;
    }
    void setPiecesOnBoard(ChessPiece[][] piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }

    void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }
    void resetNumJogadasSemCapturas(){
        this.countNumJogadasSemCapturas=0;
    }
    void primeiraCaptura(){
        this.primeiraCaptura =true;
    }
    boolean getPrimeiraCaptura(){
        return primeiraCaptura;
    }
    int getCountNumJogadasSemCapturas(){
        return countNumJogadasSemCapturas;
    }

    ChessPiece getPieceByID(int ID){return gamePieces.get(ID);}

    int countNumPiecesEquipa(int numEquipa){
        int count = 0;
        for (int i = 0; i < piecesOnBoard.length; i++) {
            for (int j = 0; j < piecesOnBoard[0].length; j++) {
                ChessPiece currentPiece = piecesOnBoard[j][i];
                if (currentPiece != null && currentPiece.tasEmJogoFilho && currentPiece.getColor() == numEquipa) {
                    count++;
                }
            }
        }
        return count;
    }

    int countNumPiecesRei(int numEquipa){
        int count = 0;
        for (int i = 0; i < piecesOnBoard.length; i++) {
            for (int j = 0; j < piecesOnBoard[0].length; j++) {
                ChessPiece currentPiece = piecesOnBoard[j][i];
                if (currentPiece != null && currentPiece.tasEmJogoFilho && currentPiece.getColor() == numEquipa&&currentPiece.getType()==0) {
                    count++;
                }
            }
        }
        return count;
    }
    int countNumPiecesNRei(int numEquipa){
        int count = 0;
        for (int i = 0; i < piecesOnBoard.length; i++) {
            for (int j = 0; j < piecesOnBoard[0].length; j++) {
                ChessPiece currentPiece = piecesOnBoard[j][i];
                if (currentPiece != null && currentPiece.tasEmJogoFilho && currentPiece.getColor() == numEquipa&&currentPiece.getType()!=0) {
                    count++;
                }
            }
        }
        return count;
    }

    void setPiecesGame(HashMap<Integer, ChessPiece> gamePieces) {
        this.gamePieces = gamePieces;
    }




}