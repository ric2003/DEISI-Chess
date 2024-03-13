package pt.ulusofona.lp2.deisichess;

import java.io.FileNotFoundException;

import kotlin.jvm.functions.Function1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestGameManager {

  public GameManager gameManager8x8;
  public GameManager gameManager4x4;



  @BeforeEach
  public void setUp() throws IOException, InvalidGameInputException {
    gameManager8x8 = new GameManager();
    gameManager8x8.loadGame(new File("test-files/8x8.txt"));
    gameManager4x4 = new GameManager();
    gameManager4x4.loadGame(new File("test-files/4x4.txt"));

  }
  @Test
  public void testLoadFilesAlmostRightMoreData() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/AlmostRightFileMoreData.txt";
    assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(new File(filePath)));
  }
  @Test
  public void testLoadFilesNonExistentFile() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/blablabla.txt";
    assertThrows(FileNotFoundException.class, () -> gameManager.loadGame(new File(filePath)));
  }
  @Test
  public void testLoadFilesAlmostRightLessData() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/AlmostRightFileLessData.txt";
    assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(new File(filePath)));
  }
  @Test
  public void testLoadFilesWrongPiece() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/AlmostRightFileWrongPiece.txt";
    assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(new File(filePath)));
  }

    @Test
    public void testLoadFilesEmpty() {
      GameManager gameManager = new GameManager();
      String filePath = "test-files/EmptyFile.txt";
      assertThrows(NumberFormatException.class,() -> gameManager.loadGame(new File(filePath)));
    }
  @Test
  public void testLoadFilesRandomFile() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/songs.txt";
    assertThrows(NumberFormatException.class, () -> gameManager.loadGame(new File(filePath)));
  }
  @Test
  public void testLoadValid4x4File() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/4x4.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    String resultado="";
    String[] results =gameManager.getSquareInfo(0,0);
    String[] result = new String[0];
    assertArrayEquals(result,results);
    results=gameManager.getSquareInfo(1,0);
    result = new String[5];
    result[0]="1";
    result[1]="0";
    result[2]="10";
    result[3]="Chefe";
    result[4]="black_king.png";
    assertArrayEquals(result,results);
    result=new String[0];
    assertArrayEquals(result,gameManager.getPieceInfo(10));
    assertEquals(gameManager.getPieceInfoAsString(10),resultado);
    result = new String[7];
    result[0]="1";
    result[1]="0";
    result[2]="10";
    result[3]="Chefe";
    result[4]="em jogo";
    result[5]="1";
    result[6]="0";
    resultado="1 | Rei | (infinito) | 10 | Chefe @ (1, 0)";
    assertArrayEquals(result,gameManager.getPieceInfo(1));
    assertEquals(gameManager.getPieceInfoAsString(1),resultado);
    result = new String[7];
    result[0]="6";
    result[1]="0";
    result[2]="20";
    result[3]="O Beberolas";
    result[4]="capturado";
    result[5]="";
    result[6]="";
    resultado="6 | Rei | (infinito) | 20 | O Beberolas @ (n/a)";
    assertArrayEquals(result,gameManager.getPieceInfo(6));
    assertEquals(gameManager.getPieceInfoAsString(6),resultado);
    resultado="Doh! zzzzzz";
    assertEquals(gameManager.getPieceInfoAsString(4),resultado);

  }
  @Test
  public void testLoadValid8x8File() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
  }
  /* falta ficheiro
  @Test
  public void testLoadSavedFileEmpate() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/FicheiroGuardadoJogoEmpate";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    assertTrue(gameManager.move(1,2,2,1));
    assertTrue(gameManager.gameOver());
    ArrayList<String> results =gameManager.getGameResults();
    StringBuilder resultado= new StringBuilder();
    for(String result:results){
      resultado.append(result).append("\n");
    }
    assertEquals("""
JOGO DE CRAZY CHESS
Resultado: EMPATE
---
Equipa das Pretas
1
2
0
Equipa das Brancas
1
2
0
""",String.valueOf(resultado));
  }
  */

  /*
  @Test
  public void testLoadSavedFileGanharamBrancas() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/FicheiroGuardadoJogoGanharBrancas.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    assertTrue(gameManager.move(3,0,0,0));
    assertTrue(gameManager.gameOver());
    ArrayList<String> results =gameManager.getGameResults();
    StringBuilder resultado= new StringBuilder();
    for(String result:results){
      resultado.append(result).append("\n");
    }
    assertEquals("""
JOGO DE CRAZY CHESS
Resultado: VENCERAM AS BRANCAS
---
Equipa das Pretas
0
3
1
Equipa das Brancas
2
3
1
""",String.valueOf(resultado));
  }
  */

  @Test
  public void testLoadSavedFile() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/FicheiroGuardadoJogo.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
  }
@Test
  public void testGetBoardSize(){
    GameManager gameManager=new GameManager();
    int result=gameManager.getBoardSize();
    assertEquals(0,result);
}
@Test
public void testMoveGameOver(){
  GameManager gameManager=new GameManager();
  String filePath = "test-files/4x4GameOver.txt";
  assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
  boolean result= gameManager.move(0,0,1,1);
  assertFalse(result);
}
  @Test
  public void testMoveInvalidMove(){
    GameManager gameManager=new GameManager();
    String filePath = "test-files/4x4.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    boolean result= gameManager.move(0,0,1,1);
    assertFalse(result);
  }
  @Test
  public void testMoveQuantumLeaperMove(){
    GameManager gameManager=new GameManager();
    String filePath = "test-files/4x4QuantumLeaper.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    boolean result= gameManager.move(0,0,0,0);
    assertTrue(result);
    result=gameManager.move(1,0,1,0);
    assertFalse(result);
  }
  @Test
  public void testGameOverNumJogadasSemCapturas(){
    GameManager gameManager = new GameManager();
    String filePath = "test-files/10x10QuantumLeaper.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    assertTrue(gameManager.move(8,8,8,9));
    assertTrue(gameManager.move(0,0,0,1));
    assertTrue(gameManager.move(1,0,1,1));
    assertTrue(gameManager.move(2,0,2,1));
    assertTrue(gameManager.move(3,0,3,1));
    assertTrue(gameManager.move(4,0,4,1));
    assertTrue(gameManager.move(5,0,5,1));
    assertTrue(gameManager.move(6,0,6,1));
    assertTrue(gameManager.move(7,0,7,1));
    assertTrue(gameManager.move(8,0,8,1));
    assertTrue(gameManager.move(9,0,9,1));
    assertTrue(gameManager.gameOver());
  }
  @Test
  public void testFullGameSUPER(){
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    gameManager.customizeBoard();
    //gameManager.getAuthorsPanel();

    gameManager.undo();
    assertTrue(gameManager.move(3,0,0,3));
    assertTrue(gameManager.move(1,7,4,4));
    assertTrue(gameManager.move(1,0,1,1));
    assertTrue(gameManager.move(2,7,0,5));
    assertTrue(gameManager.move(5,0,5,7));
    assertTrue(gameManager.move(0,5,2,3));
    gameManager.move(6,0,5,1);// Invalid Move
    assertTrue(gameManager.move(2,0,0,2));
    assertTrue(gameManager.move(4,4,4,2));
    assertTrue(gameManager.move(0,3,2,5));
    assertTrue(gameManager.move(0,7,1,7));
    gameManager.move(1,1,1,7); // Invalid Move
    gameManager.move(4,2,3,3); // Invalid Move
    gameManager.move(1,1,1,7); // Invalid Move
    assertTrue(gameManager.move(6,0,5,1));
    gameManager.undo();
    assertTrue(gameManager.move(6,0,5,1));
    List<Comparable> hints = gameManager.getHints(3,7);
    assertEquals("[(4,6) -> 0, (5,5) -> 0, (6,4) -> 0, (2,6) -> 0, (1,5) -> 0, (0,4) -> 0]",hints.toString());
    assertTrue(gameManager.move(3,7,0,4));
    assertTrue(gameManager.move(7,0,7,3));
    assertDoesNotThrow(() -> gameManager.saveGame(new File("test-files/Untitled.txt")));
    assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/Untitled.txt")));
    assertTrue(gameManager.move(4,2,5,1));
    assertTrue(gameManager.move(1,1,1,5));
    assertTrue(gameManager.move(5,1,2,1));
    assertTrue(gameManager.move(1,5,1,7));
    assertTrue(gameManager.gameOver());
    ArrayList<String> results =gameManager.getGameResults();
    StringBuilder resultado= new StringBuilder();
    for(String result:results){
      resultado.append(result).append("\n");
    }
    assertEquals("""
JOGO DE CRAZY CHESS
Resultado: VENCERAM AS PRETAS
---
Equipa das Pretas
2
9
4
Equipa das Brancas
1
8
0
""",String.valueOf(resultado));
  }
  @Test
  public void testGetHints(){
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    List<Comparable> hints = gameManager.getHints(0,0);
    assertEquals("[(0,1) -> 0, (1,1) -> 0]",hints.toString());
    hints = gameManager.getHints(1,0);
    assertEquals(
   "[(1,1) -> 0, (1,2) -> 0, (1,3) -> 0, (1,4) -> 0, (1,5) -> 0, (2,1) -> 0, (3,2) -> 0, (4,3) -> 0, (5,4) -> 0, (6,5) -> 0, (0,1) -> 0]",
            hints.toString());

    hints = gameManager.getHints(2,0);
    assertEquals("[(4,2) -> 0, (0,2) -> 0]",hints.toString());

    hints = gameManager.getHints(3,0);
    assertEquals("[(4,1) -> 0, (5,2) -> 0, (6,3) -> 0, (2,1) -> 0, (1,2) -> 0, (0,3) -> 0]",hints.toString());

    hints = gameManager.getHints(5,0);
    assertEquals("[(5,7) -> 3, (5,1) -> 0, (5,2) -> 0, (5,3) -> 0, (5,4) -> 0, (5,5) -> 0, (5,6) -> 0]",hints.toString());

    hints = gameManager.getHints(6,0);
    assertEquals("[]",hints.toString());

    hints = gameManager.getHints(7,0);
    assertEquals(
    "[(7,1) -> 0, (7,2) -> 0, (7,3) -> 0, (7,4) -> 0, (7,5) -> 0, (6,1) -> 0, (5,2) -> 0, (4,3) -> 0, (3,4) -> 0, (2,5) -> 0]",
            hints.toString());

    gameManager.move(5,0,5,5);
    gameManager.move(0,7,0,6);
    hints = gameManager.getHints(4,0);
    assertEquals("[(5,0) -> 0]",hints.toString());

    hints = gameManager.getHints(6,0);
    assertEquals("[(7,1) -> 0, (5,1) -> 0]",hints.toString());

    hints = gameManager.getHints(7,0);
    assertEquals("[(6,1) -> 0, (5,2) -> 0, (4,3) -> 0]",hints.toString());
  }
  @Test
  public void testGetStatsticsMovesDoProfesorPrimeiroVideo() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));

    gameManager.move(3, 0, 0, 3);
    gameManager.move(1, 7, 4, 4);
    gameManager.move(1, 0, 1, 1);
    gameManager.move(2, 7, 0, 5);
    gameManager.move(5, 0, 5, 7);
    gameManager.move(0, 5, 2, 3);
    gameManager.move(6, 0, 5, 1); // Invalid Move
    gameManager.move(2, 0, 0, 2);
    gameManager.move(4, 4, 4, 2);
    gameManager.move(0, 3, 2, 5);
    gameManager.move(0, 7, 1, 7);
    gameManager.move(1, 1, 1, 7); // Invalid Move
    gameManager.move(4, 2, 3, 3); // Invalid Move
    gameManager.move(1, 1, 1, 7); // Invalid Move
    gameManager.move(6, 0, 5, 1);
    gameManager.move(3, 7, 0, 4);
    gameManager.move(7, 0, 7, 3);
    gameManager.move(4, 2, 5, 1);
    gameManager.move(1, 1, 1, 5);
    gameManager.move(5, 1, 2, 1);
    gameManager.move(1, 5, 1, 7);


    List<String> expected = new ArrayList<>();
    expected.add("A Dama Selvagem (PRETA) fez 1 capturas");
    expected.add("O Maior Grande (PRETA) fez 1 capturas");
    expected.add("A Barulhenta do Bairro (BRANCA) fez 1 capturas");
    expected.add("O Poderoso Chefao (PRETA) fez 0 capturas");
    expected.add("O Grande Artista (PRETA) fez 0 capturas");

    Function1<GameManager, List<String>> calculator = StatisticsKt.getStatsCalculator(StatType.TOP_5_CAPTURAS);
    List<String> result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("A Dama Selvagem (PRETA) tem 1000 pontos");
    expected.add("O Maior Grande (PRETA) tem 3 pontos");
    expected.add("A Barulhenta do Bairro (BRANCA) tem 2 pontos");

    calculator = StatisticsKt.getStatsCalculator(StatType.TOP_5_PONTOS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();

    calculator = StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_5_CAPTURAS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("10:A Dama Selvagem:2:3");
    expected.add("10:Hommie:1:1");
    expected.add("20:A Barulhenta do Bairro:1:4");

    calculator = StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("Homer Simpson");
    expected.add("Rei");
    expected.add("TorreVert");
    calculator = StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

  }

  @Test
  public void testGetStatsticsOsMeusMoves() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));

    gameManager.move(1, 0, 1, 5);
    gameManager.move(2, 7, 0, 5);
    gameManager.move(2, 7, 4, 5);
    gameManager.move(1, 5, 2, 5);
    gameManager.move(1, 7, 1, 2);
    gameManager.move(2, 5, 4, 7);
    gameManager.move(4, 5, 6, 3);
    gameManager.move(5, 7, 5, 0);
    gameManager.move(5, 0, 5, 7);
    gameManager.move(7, 0, 5, 2);
    gameManager.move(7, 7, 5, 5);
    gameManager.move(5, 7, 5, 6);
    gameManager.move(3, 0, 5, 2);
    gameManager.move(5, 5, 3, 5);
    gameManager.move(4, 7, 6, 7);
    gameManager.move(3, 7, 0, 4);
    gameManager.move(7, 0, 3, 4);
    gameManager.move(3, 5, 5, 3);
    gameManager.move(6, 0, 7, 1);
    gameManager.move(5, 3, 0, 3);
    gameManager.move(2, 0, 0, 2);
    gameManager.move(0, 7, 1, 6);
    gameManager.move(0, 0, 1, 1);
    gameManager.move(0, 7, 0, 6);
    gameManager.move(1, 1, 2, 1);
    gameManager.move(0, 6, 0, 5);
    gameManager.move(2, 1, 1, 2);
    gameManager.move(6, 3, 4, 1);
    gameManager.move(4, 0, 4, 1);
    gameManager.move(3, 0, 4, 1);
    gameManager.move(1, 2, 0, 3);
    gameManager.move(5, 6, 5, 2);
    gameManager.move(0, 3, 1, 4);
    gameManager.move(1, 4, 0, 4);
    gameManager.move(3, 4, 7, 1);
    gameManager.move(5, 6, 1, 6);
    gameManager.move(1, 2, 1, 1);
    gameManager.move(1, 4, 3, 6);
    gameManager.move(5, 6, 3, 6);
    gameManager.move(1, 1, 0, 2);
    gameManager.move(3, 6, 5, 6);
    gameManager.move(0, 2, 0, 3);
    gameManager.move(3, 4, 4, 3);
    gameManager.move(5, 6, 6, 5);
    gameManager.move(0, 3, 0, 4);
    gameManager.move(0, 4, 1, 4);
    gameManager.move(0, 5, 1, 5);
    gameManager.move(2, 0, 0, 2);
    gameManager.move(1, 5, 2, 4);
    gameManager.move(4, 1, 2, 3);
    gameManager.move(2, 4, 3, 4);
    gameManager.move(4, 0, 4, 3);
    gameManager.move(4, 0, 2, 0);
    gameManager.move(3, 4, 2, 3);
    gameManager.move(6, 7, 3, 4);
    gameManager.move(2, 3, 1, 3);
    gameManager.move(3, 4, 1, 2);
    gameManager.move(1, 3, 0, 2);
    gameManager.move(0, 4, 1, 4);
    gameManager.move(0, 2, 1, 2);
    gameManager.move(2, 0, 1, 0);
    gameManager.move(1, 2, 1, 1);
    gameManager.move(1, 4, 1, 3);
    gameManager.move(1, 1, 1, 0);
    gameManager.move(1, 3, 1, 2);
    gameManager.move(1, 0, 1, 1);
    gameManager.move(7, 1, 6, 2);
    gameManager.move(1, 1, 1, 2);

    List<String> expected = new ArrayList<>();
    expected.add("Chefe dos Indios (BRANCA) fez 6 capturas");
    expected.add("O Poderoso Chefao (PRETA) fez 2 capturas");
    expected.add("A Dama Selvagem (PRETA) fez 2 capturas");
    expected.add("Amante de Praia (PRETA) fez 1 capturas");
    expected.add("O Maior Grande (PRETA) fez 1 capturas");

    Function1<GameManager, List<String>> calculator = StatisticsKt.getStatsCalculator(StatType.TOP_5_CAPTURAS);
    List<String> result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("Chefe dos Indios (BRANCA) tem 1023 pontos");
    expected.add("O Poderoso Chefao (PRETA) tem 11 pontos");
    expected.add("A Dama Selvagem (PRETA) tem 5 pontos");
    expected.add("Amante de Praia (PRETA) tem 5 pontos");
    expected.add("O Bobo da Corte (BRANCA) tem 3 pontos");

    calculator = StatisticsKt.getStatsCalculator(StatType.TOP_5_PONTOS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("BRANCA:Chefe dos Indios:6");

    calculator = StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_5_CAPTURAS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("20:Torre Trapalhona:1:0");
    expected.add("10:O Beberolas:3:1");
    expected.add("10:O Maior Grande:3:2");

    calculator = StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);

    expected = new ArrayList<>();
    expected.add("Homer Simpson");
    expected.add("Joker/TorreVert");
    expected.add("Padre da Vila");
    expected.add("Ponei Mágico");
    expected.add("Rainha");
    expected.add("Rei");
    expected.add("TorreHor");
    expected.add("TorreVert");
    calculator = StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS);
    result = calculator.invoke(gameManager);
    assertEquals(expected, result);
  }
  @Test
  public void photosDasPecasTaoBem() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    assertEquals("black_king.png",gameManager.board.getChessPiece(0,0).chessPiecePhoto(gameManager.board));
    assertEquals("black_Rainha.png",gameManager.board.getChessPiece(1,0).chessPiecePhoto(gameManager.board));
    assertEquals("PoneyMagicoBlack.png",gameManager.board.getChessPiece(2,0).chessPiecePhoto(gameManager.board));
    assertEquals("PadreDaVilaPreto.png",gameManager.board.getChessPiece(3,0).chessPiecePhoto(gameManager.board));
    assertEquals("TorreHorizontalPreta.png",gameManager.board.getChessPiece(4,0).chessPiecePhoto(gameManager.board));
    assertEquals("TorreVerticalPreta.png",gameManager.board.getChessPiece(5,0).chessPiecePhoto(gameManager.board));
    assertEquals("homer_black_sleeping.png",gameManager.board.getChessPiece(6,0).chessPiecePhoto(gameManager.board));
    assertEquals("black_Rainha.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("white_king.png",gameManager.board.getChessPiece(0,7).chessPiecePhoto(gameManager.board));
    assertEquals("white_Rainha.png",gameManager.board.getChessPiece(1,7).chessPiecePhoto(gameManager.board));
    assertEquals("PoneyMagicoWhite.png",gameManager.board.getChessPiece(2,7).chessPiecePhoto(gameManager.board));
    assertEquals("PadreDaVilaBranco.png",gameManager.board.getChessPiece(3,7).chessPiecePhoto(gameManager.board));
    assertEquals("TorreHorizontalBranca.png",gameManager.board.getChessPiece(4,7).chessPiecePhoto(gameManager.board));
    assertEquals("TorreVerticalBranca.png",gameManager.board.getChessPiece(5,7).chessPiecePhoto(gameManager.board));
    assertEquals("homer_white_sleeping.png",gameManager.board.getChessPiece(6,7).chessPiecePhoto(gameManager.board));
    assertEquals("white_rainha.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
    gameManager.move(0,0,0,1);
    assertEquals("homer_black.png",gameManager.board.getChessPiece(6,0).chessPiecePhoto(gameManager.board));
    assertEquals("homer_white.png",gameManager.board.getChessPiece(6,7).chessPiecePhoto(gameManager.board));
    assertEquals("PoneyMagicoBlack.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("PoneyMagicoWhite.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
    gameManager.move(0,7,0,6);
    assertEquals("PadreDaVilaPreto.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("PadreDaVilaBranco.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
    gameManager.move(0,1,0,2);
    assertEquals("TorreHorizontalPreta.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("TorreHorizontalBranca.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
    gameManager.move(0,6,0,5);
    assertEquals("TorreVerticalPreta.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("TorreVerticalBranca.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
    gameManager.move(0,2,0,3);
    assertEquals("homer_black.png",gameManager.board.getChessPiece(7,0).chessPiecePhoto(gameManager.board));
    assertEquals("homer_white.png",gameManager.board.getChessPiece(7,7).chessPiecePhoto(gameManager.board));
  }
  @Test
  public void testaPecaCriativa() {
    GameManager gameManager = new GameManager();
    String filePath = "test-files/4x4QuantumLeaper.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    gameManager.move(0,0,0,1);
    List<Comparable> expected =gameManager.getHints(3,0);
    assertEquals("[(2,0) -> 10, (3,1) -> 0, (2,1) -> 0, (3,0) -> find out]",expected.toString());
    assertEquals("QuantumLeaperBlack.png",gameManager.board.getChessPiece(2,0).chessPiecePhoto(gameManager.board));
    assertEquals("QuantumLeaperWhite.png",gameManager.board.getChessPiece(3,0).chessPiecePhoto(gameManager.board));

  }

  @Test
  public void jogarAteAoFimGravarELerFicheiro(){
    GameManager gameManager = new GameManager();
    String filePath = "test-files/8x8DoProf.txt";
    assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));
    gameManager.customizeBoard();


    gameManager.undo();
    assertTrue(gameManager.move(3,0,0,3));
    assertTrue(gameManager.move(1,7,4,4));
    assertTrue(gameManager.move(1,0,1,1));
    assertTrue(gameManager.move(2,7,0,5));
    assertTrue(gameManager.move(5,0,5,7));
    assertTrue(gameManager.move(0,5,2,3));
    gameManager.move(6,0,5,1);// Invalid Move
    assertTrue(gameManager.move(2,0,0,2));
    assertTrue(gameManager.move(4,4,4,2));
    assertTrue(gameManager.move(0,3,2,5));
    assertTrue(gameManager.move(0,7,1,7));
    gameManager.move(1,1,1,7); // Invalid Move
    gameManager.move(4,2,3,3); // Invalid Move
    gameManager.move(1,1,1,7); // Invalid Move
    assertTrue(gameManager.move(6,0,5,1));
    gameManager.undo();
    assertTrue(gameManager.move(6,0,5,1));
    assertTrue(gameManager.move(3,7,0,4));
    assertTrue(gameManager.move(7,0,7,3));
    assertTrue(gameManager.move(4,2,5,1));
    assertTrue(gameManager.move(1,1,1,5));
    assertTrue(gameManager.move(5,1,2,1));
    assertTrue(gameManager.move(1,5,1,7));
    assertTrue(gameManager.gameOver());
    ArrayList<String> resultsantesSave =gameManager.getGameResults();
    StringBuilder resultadoantesSave= new StringBuilder();
    for(String resultantesSave:resultsantesSave){
      resultadoantesSave.append(resultantesSave).append("\n");
    }
    assertEquals("""
JOGO DE CRAZY CHESS
Resultado: VENCERAM AS PRETAS
---
Equipa das Pretas
2
9
4
Equipa das Brancas
1
8
0
""",String.valueOf(resultadoantesSave));

    assertDoesNotThrow(() -> gameManager.saveGame(new File("test-files/Untitled.txt")));
    assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/Untitled.txt")));
    /*assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/8x8DoProfBrancasWin.txt")));
    assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/Untitled.txt")));*/
    assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/8x8DoProfBrancasWin.txt")));
    assertTrue(gameManager.gameOver());
    ArrayList<String> results =gameManager.getGameResults();
    StringBuilder resultado= new StringBuilder();
    for(String result:results){
      resultado.append(result).append("\n");
    }
    assertEquals("""
JOGO DE CRAZY CHESS
Resultado: VENCERAM AS BRANCAS
---
Equipa das Pretas
0
0
0
Equipa das Brancas
0
0
0
""",String.valueOf(resultado));
  }
  }
