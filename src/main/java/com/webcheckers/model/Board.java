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

    /**
     * Constructor for the Board.
     */
    public Board() {
        for(int row = 0; row < BOARD_DIM; row++) {
            for(int col = 0; col < BOARD_DIM; col++) {
                Space space;
                if(col % 2 + row % 2 == 1) {
                    space = new Space(row, col, null, true);
                    if(row > BOARD_DIM - 4) {
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.RED));
                    }
                    else if(row < 3) {
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));
                    }
                }
                else {
                    space = new Space(row, col, null, false);
                }
                this.board[row][col] = space;
            }
        }
        System.out.println(toString());
    }

    /*
     * This checks if the moves made during a turn were valid
     *
    public void isValid(ArrayList<Space> moves) {
        //row = cellIdx
        //col = location in
        Space startSpace;
        Space endSpace;
        if(moves.size() > 1) {
            //TODO make copy of board
            Iterator<Space> moveIterator = moves.iterator();//moves can be made up of 2+ spaces
            startSpace = moveIterator.next();
            while (moveIterator.hasNext()) {
                endSpace = moveIterator.next();
                Move curMove = new Move(startSpace, endSpace, activeColor);
                if(curMove.isValid()) {
                    //TODO change board copy
                    makeMove(curMove);
                    startSpace = endSpace;
                }
                else {
                    break;
                }
            }
        }
    }
    */

    public Space getSpace(int row, int col){
        return this.board[row][col];
    }

    public void makeSingleMove(SingleMove curMove) {
       System.out.println(toString());
       // board.set()
       // board.get(curMove.getStart().getRowIdx()).changeSpace(curMove.getStart().getCellIdx(), curMove.getStart());
    }

    /**
     * This method flips the board to provide the proper orientation for a player.
     */
    public void flip() {
        Space[][] flippedBoard = new Space[BOARD_DIM][BOARD_DIM];
        for(int row = 0; row < BOARD_DIM; row++) {
            for(int col = 0; col < BOARD_DIM; col++) {
                Space space = this.board[row][col];
                flippedBoard[BOARD_DIM - row - 1][BOARD_DIM - col - 1] = space;
            }
        }
        this.board = flippedBoard;
        System.out.println(toString());
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
    public void debugMove(){
        Space spaceStart = this.board[5][0];
        Space spaceEnd = this.board[4][2];
        SingleMove move = new SingleMove(spaceStart,spaceEnd);

        move.executeMove();

        System.out.println(toString());
    }

    public static void main(String[] args) {//for debugging purposes only
        Board board = new Board();
        board.debugMove();



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
