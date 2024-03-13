package pt.ulusofona.lp2.deisichess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameManager {

  public Board board;
  public ChessGameRecorder chessGameRecorder;

  public Map<String, String> customizeBoard() {
    Map<String, String> customizationMap = new HashMap<>();

    customizationMap.put("title", "CRAZY DESI CHESS 🤯");
    customizationMap.put("imageBlackSquare", "darkGray_box.png");
    customizationMap.put("imageWhiteSquare", "lightGray-box.png");
    customizationMap.put("imageBackground", "ipadwithbg.png");
    customizationMap.put("boardMarginTop", "150");
    customizationMap.put("boardMarginBottom", "150");

    return customizationMap;
  }


  public void loadGame(File file) throws InvalidGameInputException, IOException{
      if (!file.exists()) {
          throw new FileNotFoundException(" Ficheiro não existente");
      }
      int lineNum = 2;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      int boardSize = Integer.parseInt(br.readLine());
      int numPieces = Integer.parseInt(br.readLine());
      board = new Board(boardSize,numPieces);
      chessGameRecorder= new ChessGameRecorder();

      HashMap<Integer, ChessPiece> pieceList = new HashMap<>();
      for (int i = 0; i < numPieces; i++) {
        String[] pieceInfo = br.readLine().split(":");
        lineNum++;
        if (pieceInfo.length == 4) {
          int iD = Integer.parseInt(pieceInfo[0]);
          int type = Integer.parseInt(pieceInfo[1]);
          int color = Integer.parseInt(pieceInfo[2]);
          String name = pieceInfo[3];

          ChessPiece newPiece;
          switch (type) {
            case 0:
              newPiece = new Rei(iD, color, name, false);
              break;
            case 1:
              newPiece = new Rainha(iD, color, name, false);
              break;
            case 2:
              newPiece = new PoneiMagico(iD, color, name, false);
              break;
            case 3:
              newPiece = new PadreDaVila(iD, color, name, false);
              break;
            case 4:
              newPiece = new TorreHorizontal(iD, color, name, false);
              break;
            case 5:
              newPiece = new TorreVertical(iD, color, name, false);
              break;
            case 6:
              newPiece = new Homer(iD, color, name, false);
              break;
            case 7:
              newPiece = new Joker(iD, color, name, false);
              break;
            case 8:
              newPiece = new QuantumLeaper(iD, color, name, false);
              break;
            default:
            throw new InvalidGameInputException(lineNum,"Invalid piece type: " + type);
          }
          pieceList.put(iD, newPiece);
        }else{
          if(pieceInfo.length <4){
            throw new InvalidGameInputException(lineNum,"DADOS A MENOS ",pieceInfo.length,4);
          }
            throw new InvalidGameInputException(lineNum, "DADOS A MAIS ", pieceInfo.length, 4);
        }
      }

      int[][] boardArray = new int[boardSize][boardSize];
      for (int i = 0; i < boardSize; i++) {
        String[] rowInfo = br.readLine().split(":");
        if(rowInfo.length==boardSize) {
          for (int j = 0; j < boardSize; j++) {
            boardArray[i][j] = Integer.parseInt(rowInfo[j]);
          }
        }
      }

      board.setPiecesGame(pieceList);
      ChessPiece[][] pieces = new ChessPiece[boardSize][boardSize];

      for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
          int id = boardArray[i][j];
          if (id != 0) {
            ChessPiece piece =  board.getPieceByID(id);
            if (piece != null) {
              piece.setXandY(j,i);
              piece.setTasEmJogoFilho(true);
              pieces[i][j] = piece;
            } else {
              pieces[i][j] = null;
            }
          } else {
            pieces[i][j] = null;
          }
        }
      }
      board.setPiecesOnBoard(pieces);
      chessGameRecorder.setIncialBoard(board);
      String input;
      while ((input = br.readLine()) != null) {
        // Regular expressions to match the specified formats
        String regex1 = "\\((-?\\d+),(-?\\d+)\\) to \\((-?\\d+),(-?\\d+)\\)";
        String regex2 = "\\((-?\\d+),(-?\\d+)\\) Teleport to \\((-?\\d+),(-?\\d+)\\)";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);

        if (matcher1.find()) { // Use find() instead of matches()
          int x0 = Integer.parseInt(matcher1.group(1));
          int y0 = Integer.parseInt(matcher1.group(2));
          int x1 = Integer.parseInt(matcher1.group(3));
          int y1 = Integer.parseInt(matcher1.group(4));
          move(x0, y0, x1, y1);
        } else if (matcher2.find()) {
          int x0 = Integer.parseInt(matcher2.group(1));
          int y0 = Integer.parseInt(matcher2.group(2));
          int x1 = Integer.parseInt(matcher2.group(3));
          int y1 = Integer.parseInt(matcher2.group(4));
          quantumLeaperMoverLoadGame(x0, y0, x1, y1);
        }
      }
      br.close();
      /*
      if(gameOver()){
        int tamanho = chessGameRecorder.getBoardStatesStack().size();
        for (int i = 0; i <tamanho; i++) {
          chessGameRecorder.getBoardStatesStack().get(i).resetJogadasStats();
        }
        board=chessGameRecorder.getBoardStatesStack().get(tamanho-1).cloneBoard();
      }*/

    } catch (FileNotFoundException fileNotFoundException) {
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public int getBoardSize() {
    if (board != null) {
      return board.getBoardSize();
    } else {
      return 0;
    }
  }
  public boolean move(int x0, int y0, int x1, int y1) {

    if(gameOver()){
      return false;
    }

    if(board.getChessPiece(x0,y0)==null){
      jogadainvalida();
      return false;
    }
    if(board.getChessPiece(x0,y0).quantumLeaperIsTeleporting(x0, y0, x1, y1)){
      return quantumLeaperMover(x0, y0, x1, y1);
    }

    if (!board.getChessPiece(x0,y0).canMoveTo(board,x0,y0,x1,y1)){
      board.getChessPiece(x0,y0).addMovimentosInvalidos();
      jogadainvalida();
      chessGameRecorder.recordMove(board,x0,y0,x1,y1);
      return false;
    }
    moverToNewPosition(x0, y0, x1, y1);

    return true;
  }

  private void moverToNewPosition(int x0, int y0, int x1, int y1) {
    if (board.getChessPiece(x1, y1) != null){
      board.getChessPiece(x1, y1).setTasEmJogoFilho(false);
      board.primeiraCaptura();
      board.resetNumJogadasSemCapturas();
      jogadaCaptura();
      board.getChessPiece(x0, y0).addCaptures();
      board.getChessPiece(x0, y0).addPointsObtainedFromCaptures(board.getChessPiece(x1, y1).getValor());
    }else if(board.getPrimeiraCaptura()){
      board.jogadaSemCaptura();
    }

    board.setpieceonborad(x1, y1,board.getChessPiece(x0, y0));
    board.getPieceByID(board.getChessPiece(x1, y1).getiD()).setXandY(x1, y1);
    board.setpieceonborad(x0, y0,null);

    jogadasValidas();
    board.vezDaProximaEquipa();
    board.getChessPiece(x1, y1).addMovimentosValidos();
    chessGameRecorder.recordMove(board, x0, y0, x1, y1);
  }

  private void jogadaCaptura() {
    if(getCurrentTeamID()==10){
      board.pretoCaptura();
    }else{
      board.brancoCaptura();
    }
  }

  private void jogadasValidas() {
    if(getCurrentTeamID()==20){
      board.jogadasValidasBranco();
    }else{
      board.jogadasValidasPreto();
    }
  }

  private boolean quantumLeaperMover(int x0, int y0, int x1, int y1) {//usado so para o Quantum Leaper
    if(board.equipaAJogar() == board.getChessPiece(x0, y0).getColor()){
            Random random = new Random();
      int randomNumberX;
      int randomNumberY;
      do {
        randomNumberX = random.nextInt(getBoardSize());
        randomNumberY = random.nextInt(getBoardSize());
      }while (board.getChessPiece(randomNumberX,randomNumberY)!=null);
      board.setpieceonborad(randomNumberX,randomNumberY,board.getChessPiece(x0, y0));
      board.getPieceByID(board.getChessPiece(randomNumberX,randomNumberY).getiD()).setXandY(randomNumberX,randomNumberY);
      board.setpieceonborad(x0, y0,null);
      jogadasValidas();
      board.vezDaProximaEquipa();
      board.getChessPiece(randomNumberX,randomNumberY).addMovimentosValidos();
      chessGameRecorder.recordMove(board, x0, y0, randomNumberX, randomNumberY);
      return true;
    }
    board.getChessPiece(x0,y0).addMovimentosInvalidos();
    chessGameRecorder.recordMove(board, x0, y0, x1, y1);
    return false;
  }
  private void quantumLeaperMoverLoadGame(int x0, int y0, int x1, int y1){//usado so para o Quantum Leaper
    if(board.getChessPiece(x0,y0)!=null&&board.getChessPiece(x0,y0).isQuantumLeaper()
            &&x1>=0&&x1<getBoardSize() &&y1>=0&&y1<getBoardSize() &&
            board.equipaAJogar() == board.getChessPiece(x0, y0).getColor()) {

      moverToNewPosition(x0, y0, x1, y1);

    }
  }

  private void jogadainvalida() {
    if(getCurrentTeamID()==10){
      board.jogadasInvalidasPreto();
    }else{
      board.jogadasInvalidasBranco();
    }
  }

  public String[] getSquareInfo(int x, int y) {
    if (board.getChessPiece(x,y) == null) {
      return new String[0];
    }
    ChessPiece square = board.getChessPiece(x,y);

    return new String[]{String.valueOf(square.getiD()), String.valueOf(square.getType()),
            String.valueOf(square.getColor()), square.getName(), square.chessPiecePhoto(board)};

  }

  public String[] getPieceInfo(int ID) {

      ChessPiece piece = board.getPieceByID(ID);

      if (piece != null) {
        String tasEmJogoFilho = (piece.getTasEmJogoFilho()) ? "em jogo" : "capturado";
        if(!piece.getTasEmJogoFilho()){
          return new String[]{String.valueOf(piece.getiD()), String.valueOf(piece.getType()), String.valueOf(piece.getColor()),
                  piece.getName(), tasEmJogoFilho, "", ""};
        }

        return new String[]{String.valueOf(piece.getiD()), String.valueOf(piece.getType()), String.valueOf(piece.getColor()),
                piece.getName(), tasEmJogoFilho, String.valueOf(piece.getX()), String.valueOf(piece.getY())};

      }

    return new String[0];
  }

  public String getPieceInfoAsString(int id) {
    ChessPiece piece = board.getPieceByID(id);

    if (piece == null){return "";}

    if(piece.isSleeping(board)){
      return "Doh! zzzzzz";
    }

    String getPieceInfoAsString = piece.getiD() + " | " + piece.getTypeName(board) + " | " + piece.getValorString() + " | " +
            piece.getColor() + " | " + piece.getName();
    if(piece.getTasEmJogoFilho()){
      return getPieceInfoAsString + " @ (" + piece.getX() + ", " + piece.getY() + ")";
    }else{
      return getPieceInfoAsString + " @ (n/a)";
    }
  }

  public int getCurrentTeamID() {
    if (board.getQuemTaAJogar() % 2 == 0) {
      return 10;
    }else {
      return 20;
    }
  }

  public boolean gameOver() {
    if (board.getCountNumJogadasSemCapturas()==10){
      return true;
    }else if (board.countNumPiecesRei(20)==0||board.countNumPiecesRei(10)==0) {
      return true;
    }else {
      return board.countNumPiecesRei(20) == 1 && board.countNumPiecesNRei(20) == 0 &&
              board.countNumPiecesRei(10) == 1 && board.countNumPiecesNRei(10) == 0;
      }

    }

  public ArrayList<String> getGameResults() {
    ArrayList<String> gameResults=new ArrayList<>();
    gameResults.add("JOGO DE CRAZY CHESS");
    if (gameOver()&&board.countNumPiecesRei(20)==0&&board.countNumPiecesRei(10)>=1){
      gameResults.add("Resultado: VENCERAM AS PRETAS");
    } else if (gameOver()&&board.countNumPiecesRei(20)>=1&&board.countNumPiecesRei(10)==0){
      gameResults.add("Resultado: VENCERAM AS BRANCAS");
    }else {
      gameResults.add("Resultado: EMPATE");
    }
    gameResults.add("---");
    gameResults.add("Equipa das Pretas");
    gameResults.add(String.valueOf(board.getNrCapurasP()));
    gameResults.add(String.valueOf(board.getJogadasValidasP()));
    gameResults.add(String.valueOf(board.getTentativasInvalidasP()));
    gameResults.add("Equipa das Brancas");
    gameResults.add(String.valueOf(board.getNrCapturasB()));
    gameResults.add(String.valueOf(board.getJogadasValidasB()));
    gameResults.add(String.valueOf(board.getTentativasInvalidasB()));
    return gameResults;
  }

  public JPanel getAuthorsPanel() {
    JPanel mainPanel = new JPanel(new BorderLayout());

    try {
      BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/x9fJaWA5cWmGAGPkpzNbe8.png")));
      pt.ulusofona.lp2.deisichess.ImagePanel imagePanel = new pt.ulusofona.lp2.deisichess.ImagePanel(image);

      mainPanel.add(imagePanel.getPanel(), BorderLayout.CENTER);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return mainPanel;
  }

  public GameManager() {
  }
  public void saveGame(File file) throws IOException{
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      Board chessboard = chessGameRecorder.getIncialBoard();
      writer.write(chessboard.getBoardSize()+"\n");
      writer.write(chessboard.getNumberOfPieces()+"\n");
      HashMap<Integer, ChessPiece> pieces = chessboard.getGamePieces();
      for(int i =1;i<=chessboard.getNumberOfPieces();i++) {
        ChessPiece piece = pieces.get(i);
        if (piece != null) {
          writer.write(piece.getiD() + ":" + piece.getType() + ":" + piece.getColor() + ":" + piece.getName() + "\n");
        }
      }
      for(int i=0;i<chessboard.getBoardSize();i++){
        for(int j=0;j<chessboard.getBoardSize();j++){
          ChessPiece piece = chessboard.getChessPiece(j,i);
          int id = piece==null? 0:piece.getiD();
          if(j==chessboard.getBoardSize()-1){
            writer.write(id+"\n");
          }else{
            writer.write(id+":");
          }
        }
      }
      List<String> moveHistory = chessGameRecorder.getMoveHistory();

      for(int i=0;i<moveHistory.size();i++){
        String move = moveHistory.get(i);
        writer.write(move);
        if(moveHistory.size()-1!=i){
          writer.write("\n");
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  public void undo(){
    board.updateBoardState(chessGameRecorder.undoMove());
  }

  public List<Comparable> getHints(int x, int y){
    List<Comparable> moves = null;

    if(board.getChessPiece(x,y)!=null){
      moves = board.getChessPiece(x,y).getHintsPiece(board,x,y,board.getChessPiece(x,y).getPossibleMoves(getBoardSize()));
        Collections.sort(moves);
    }
    return  moves;
  }

}
