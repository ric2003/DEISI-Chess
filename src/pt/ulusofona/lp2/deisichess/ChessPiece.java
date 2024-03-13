package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChessPiece {
  protected String name;
  protected int iD;
  protected int type;
  protected int color;
  protected boolean tasEmJogoFilho;
  protected int x;
  protected int y;
  protected int valor;
  protected int captures;
  protected int pointsObtainedFromCaptures;
  protected int movimentosValidos;
  protected int movimentosInvalidos;

  ChessPiece(int iD, int type, int color, String name,boolean tasEmJogoFilho,int valor) {
    this.iD = iD;
    this.type = type;
    this.color=color;
    this.name = name;
    this.tasEmJogoFilho = tasEmJogoFilho;
    this.valor = valor;
    this.captures = 0;
    this.pointsObtainedFromCaptures = 0;
    this.movimentosValidos = 0;
    this.movimentosInvalidos = 0;
  }
  public abstract ChessPiece cloneChessPiece();
  abstract boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1);

  public boolean validator(Board chessboard, int x0, int y0, int x1, int y1){
    if (x1 < 0 || x1 >= chessboard.getBoardSize() || y1 < 0 || y1 >= chessboard.getBoardSize()) {
      return false;
    }
    if(chessboard.getChessPiece(x1,y1)!=null&&chessboard.getChessPiece(x0,y0)!=null&&
        chessboard.getChessPiece(x0,y0).getColor()==chessboard.getChessPiece(x1,y1).getColor()){
      return false;
    }

    if (chessboard.equipaAJogar() != chessboard.getChessPiece(x0, y0).getColor()) {
      return false;
    }
    return true;
  }

  /*public ChessPiece() {
    this.tasEmJogoFilho=false;
  }*/

  int getiD() {
    return iD;
  }
  void addCaptures(){
    captures++;
  }
  int getCaptures(){
    return captures;
  }
  void setCaptures(int captures){
    this.captures=captures;
  }
  void addPointsObtainedFromCaptures(int points){
    this.pointsObtainedFromCaptures+=points;
  }
  int getPointsObtainedFromCaptures(){
    return pointsObtainedFromCaptures;
  }
  void setpointsObtainedFromCaptures(int point){
    this.pointsObtainedFromCaptures = point;
  }
  int getMovimentosValidos(){
    return movimentosValidos;
  }
  void addMovimentosValidos(){
    this.movimentosValidos++;
  }
  void setMovimentosValidos(int moves){
    this.movimentosValidos =moves;
  }
  int getMovimentosInvalidos(){
    return movimentosInvalidos;
  }
  void addMovimentosInvalidos(){
    this.movimentosInvalidos++;
  }
  void setMovimentosInvalidos(int invalidMove){
    this.movimentosInvalidos=invalidMove;
  }
  void setMove_MoveInv_Capturas_pCapturas(int validMoves,int invalidMove,int point,int captures){
    this.movimentosValidos =validMoves;
    this.movimentosInvalidos=invalidMove;
    this.pointsObtainedFromCaptures = point;
    this.captures=captures;
  }



  String getName() {
    return name;
  }
  abstract int[][] getPossibleMoves(int boardSize);

  public boolean isSleeping(Board chessboard){
    return false;
  }

  boolean isQuantumLeaper(){
    return false;
  }
  int getValor(){return valor;}

  String getValorString(){
    if(valor==1000){
      return "(infinito)";
    }
    return valor+"";
  }

  int getType() {
    return type;
  }
  public String getTypeName(Board chessboard) {
    int currentPieceID = chessboard.getQuemTaAJogar() % 6 + 1;

    List<String> typeNames = new ArrayList<>(Arrays.asList(
        "Rei", "Rainha", "Ponei Mágico", "Padre da Vila", "TorreHor", "TorreVert", "Homer Simpson", "Joker/" ,"Quantum Leaper"
    ));

    switch (getType()) {
      case 0, 1, 2, 3, 4, 5, 6, 8:
        return typeNames.get(getType());
      case 7:
        return typeNames.get(getType()) + typeNames.get(currentPieceID);
      default:
        return null;
    }
  }

  int getColor() {
    return color;
  }
  String getColorString(){
    return color==10?"PRETA":"BRANCA";
  }


  boolean getTasEmJogoFilho() {
    return tasEmJogoFilho;
  }
  void setTasEmJogoFilho(boolean ta) {
    this.tasEmJogoFilho=ta;
  }


  int getX() {
    return x;
  }

  int getY() {
    return y;
  }
  void setX(int x){
    this.x=x;
  }
  void setY(int y){
    this.y=y;
  }
  void setXandY(int x,int y){
    this.x=x;
    this.y=y;
  }
  public abstract String chessPiecePhoto(Board chessboard);
  public boolean quantumLeaperIsTeleporting(int x0,int y0,int x1,int y1){
    return false;
  }
  public List<Comparable> getHintsPiece(Board chessboard, int x, int y,int[][] possibleMoves){
    boolean canMove;
    List<Comparable> possiblePositions = new ArrayList<>();

    if (quantumLeaperIsTeleporting(x, y, x, y)&&chessboard.getChessPiece(x,y).getColor()== chessboard.equipaAJogar()){
      possiblePositions.add(new ValidMovePoint(x,y,-1));//to suggest quantum leaper teleport move
    }

    for (int i = 0; i < possibleMoves.length; i++) {
      int moveX = possibleMoves[i][0] + x;
      int moveY = possibleMoves[i][1] + y;

      canMove = canMoveTo(chessboard, x, y, moveX, moveY);

      if(canMove){
        ChessPiece piece = chessboard.getChessPiece(moveX,moveY);
        int valor = piece==null?0:piece.getValor();
        possiblePositions.add(new ValidMovePoint(moveX,moveY,valor));
      }

    }
    return possiblePositions;
  }

  @Override
  public String toString() {
    return iD + " | " + type + " | " + valor + " | " + color + " | " + name + " @ (" + x + ", " + y + ")";
  }
}