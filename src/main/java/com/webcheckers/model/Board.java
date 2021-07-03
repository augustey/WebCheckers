package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for creating the board for a checkers game.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Board implements Iterable<Row> {

    // The length and width of a checkers board.
    private final int BOARD_DIM = 8;

    // 2D Array of Spaces that form the board.
    private Space[][] board = new Space[BOARD_DIM][BOARD_DIM];

    private ArrayList<Move> possibleMoves = new ArrayList<>();

    private Piece.Color activePlayerColor;

    /**
     * Constructor for the Board.
     */
    public Board() {
        activePlayerColor = Piece.Color.RED;
        for(int row = 0; row < BOARD_DIM; row++) {
            for(int col = 0; col < BOARD_DIM; col++) {
                Space space;
                if(col % 2 + row % 2 == 1) {
                    space = new Space(row, col, null, true);
                    if(row > BOARD_DIM - 4) {
                        space.setPiece(new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED));
                    }
                    else if(row < 3) {
                        space.setPiece(new SinglePiece(Piece.Type.SINGLE, Piece.Color.WHITE));
                    }
                }
                else {
                    space = new Space(row, col, null, false);
                }
                this.board[row][col] = space;
            }
        }

        lookForSingleMoves();


        System.out.println(toString());
//        System.out.println(this.possibleMoves.size());

    }

    public Board(Space[][] board) {
        this.board = board;
    }



    public void lookForSingleMoves() throws ArrayIndexOutOfBoundsException{
        ArrayList<Move> singleMoves = new ArrayList<>();
        for(int row = 0; row < BOARD_DIM; row++) {
            for(int col = 0; col < BOARD_DIM; col++) {
                Piece piece = this.board[row][col].getPiece();
                if (piece != null && piece.getColor() == activePlayerColor) {
                    singleMoves.addAll(piece.allSingleMoves(row, col));
                }
            }
        }
        validateAllMoves(singleMoves);
    }

    public void validateAllMoves(ArrayList<Move> moves){
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);

            if(validateSimpleMove(move)) {
                possibleMoves.add(move);
            }
        }
    }

    private boolean validateSimpleMove(Move move) {
        Position end = move.getEnd();
        int row = end.getRow();
        int col = end.getCell();
        try {
            return this.board[row][col].isValid();
        }
        catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

    }

    public Space getSpace(Position position){
        int row = position.getRow();
        int col = position.getCell();
        return this.board[row][col];
    }

    public void makeMove(Move curMove) {
        if(possibleMoves.contains(curMove))
        {
            Move move = possibleMoves.get(possibleMoves.indexOf(curMove));
            executeMove(move);
            lookForSingleMoves();
            if(activePlayerColor == Piece.Color.RED) {
                activePlayerColor = Piece.Color.WHITE;
            }
            else {
                activePlayerColor = Piece.Color.RED;
            }
        }
        else{
            //TODO : through invalid move error
        }
        System.out.println(toString());



    }

    public void executeMove(Move move){
        Space start = getSpace(move.getStart());
        Space end = getSpace(move.getEnd());

        end.setPiece(end.getPiece());
        start.setPiece(null);
    }

    /**
     * This method flips the board to provide the proper orientation for a player.
     */
    public Board flip() {
        Space[][] flippedBoard = new Space[BOARD_DIM][BOARD_DIM];
        for(int row = 0; row < BOARD_DIM; row++) {
            for(int col = 0; col < BOARD_DIM; col++) {
                Space space = this.board[row][col];
                flippedBoard[BOARD_DIM - row - 1][BOARD_DIM - col - 1] = space;
            }
        }
        return new Board((flippedBoard));
    }

    /**
     * String representation of board used for debugging.
     *
     * @return
     *     A string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder textBoard = new StringBuilder();
        for (Space[] spaces : board) {
            for (Space curSpace : spaces) {
                if (!curSpace.isValid() && curSpace.getPiece() == null) {
                    textBoard.append("*");//none playable spot
                }
                else if (curSpace.getPiece() == null) {//playable spot
                    textBoard.append("_");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece().getType() == Piece.Type.SINGLE) {//single red
                    textBoard.append("r");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece().getType() == Piece.Type.SINGLE) {//single white
                    textBoard.append("w");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece().getType() == Piece.Type.KING) {//king white
                    textBoard.append("R");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece().getType() == Piece.Type.KING) {//king red
                    textBoard.append("W");
                }
            }
            textBoard.append("\n");
        }
        return textBoard.toString();
    }
//    public void debugMove(){
//        Space spaceStart = this.board[5][0];
//        Space spaceEnd = this.board[4][2];
//        SingleMove move = new SingleMove(spaceStart,spaceEnd);
//
//        move.executeMove();
//
//        System.out.println(toString());
//    }

    public static void main(String[] args) {//for debugging purposes only
        Board board = new Board();
//        board.debugMove();



//        board.flip();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Row> iterator() {
        ArrayList<Row> board = new ArrayList<>();
        for(int row = 0; row < BOARD_DIM; row++) {
            ArrayList<Space> spaces = new ArrayList<>();
            for(int col = 0; col < BOARD_DIM; col++) {
                spaces.add(this.board[row][col]);
            }
            Row curRow = new Row(row, spaces);
            board.add(curRow);
        }
        return board.iterator();
    }
}
