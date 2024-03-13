package pt.ulusofona.lp2.deisichess;

import java.util.*;

public class ChessGameRecorder {
    private List<String> moveHistory;
    private Stack<Board> boardStates;
    private Board incialBoard;

    public void setCurrentBoard(Board board){
        Board clonedBoard = board.cloneBoard();
        boardStates.add(clonedBoard);
    }
    public void setIncialBoard(Board board){
        this.incialBoard = board.cloneBoard();
        setCurrentBoard(board);

    }
    public ChessGameRecorder() {
        this.moveHistory = new ArrayList<>();
        this.boardStates = new Stack<>();
    }

    public boolean recordMove(Board board, int x0, int y0, int x1, int y1) {
        String move;
        if(x1>=0&&x1<board.getBoardSize()&&y1>=0&&y1<board.getBoardSize()&&
                board.getChessPiece(x1,y1)!=null&&board.getChessPiece(x1,y1).isQuantumLeaper()){
             move = String.format("(%d,%d) Teleport to (%d,%d)", x0, y0, x1, y1);
        }else {
             move = String.format("(%d,%d) to (%d,%d)", x0, y0, x1, y1);
        }
        moveHistory.add(move);

        Board clonedBoard = board.cloneBoard();
        boardStates.add(clonedBoard);

        return true;
    }
    public Board undoMove() {
        if (boardStates.size()>1) {
            moveHistory.remove(moveHistory.size()-1);
            boardStates.pop();
            return boardStates.peek().cloneBoard();

        }
        return incialBoard.cloneBoard();
    }


    public List<String> getMoveHistory() {
        return moveHistory;
    }

    public Stack<Board> getBoardStatesStack() {
        return boardStates;
    }
    public Board getIncialBoard(){
        return incialBoard;
    }
}
