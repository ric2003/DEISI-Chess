package pt.ulusofona.lp2.deisichess;


public class QuantumLeaper extends ChessPiece {


  public QuantumLeaper(int iD, int color, String nome, boolean tasEmJogoFilho) {
    super(iD, 8, color, nome, tasEmJogoFilho, 10);
  }
  @Override
  public ChessPiece cloneChessPiece() {
    QuantumLeaper novaPeca= new QuantumLeaper(this.iD,this.color,this.name,this.tasEmJogoFilho);
    novaPeca.setXandY(getX(),getY());
    novaPeca.setMove_MoveInv_Capturas_pCapturas(getMovimentosValidos(),
        getMovimentosInvalidos(),getPointsObtainedFromCaptures(),getCaptures());
    return novaPeca;
  }

  @Override
  boolean canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) {
    int dx = Math.abs(x1 - x0);
    int dy = Math.abs(y1 - y0);
    if(!validator(chessboard,x0,y0,x1,y1)){
      return false;
    }
    return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
  }
  @Override
  boolean isQuantumLeaper(){
    return true;
  }
  @Override
  int[][] getPossibleMoves(int boardSize) {

    return new int[][]{
        {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
  }

  @Override
  public boolean quantumLeaperIsTeleporting(int x0,int y0,int x1,int y1){
    if(x0 == x1 && y0 == y1 ) {
      return true;
    }
    return false;
  }

  @Override
  public String chessPiecePhoto(Board chessboar) {
    if(color==10){
      return "QuantumLeaperBlack.png";
    }
    return "QuantumLeaperWhite.png";  }
}