package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for creating the board for a checkers game.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'> William Dabney</a>
 */
public class Board implements Iterable<Row>{

    // The length and width of a checkers board.
    private final int BOARD_DIM = 8;

    private Piece.Color activeColor;

    // Array of Rows that form the board
    private ArrayList<Row> board = new ArrayList<>();

    /**
     * Constructor for the Board
     */
    public Board(){
        activeColor = Piece.Color.RED;
        for(int row = 0; row < BOARD_DIM; row++){
            ArrayList<Space> curRow = new ArrayList<>(); //holds what will later be put into a row object
            for(int col = 0; col < BOARD_DIM; col++) {
                Space space = new Space(row, col); //creates a blank space
                if(col % 2 + row % 2 == 1) { //is on valid spot if only row or col is even but not both
                    space.setColor(Space.Color.BLACK);//because it is a valid spot it needs to be set as such
                    if(row > BOARD_DIM - 4) { //for white piece placement 3 rows of them
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.RED));//fills the blank space with a red piece
                    }
                    else if(row < 3) { //for red piece placement 3 rows of them
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));//fills the blank space with a white piece
                    }
                }
                curRow.add(space);//puts space into the collection holding all spaces in row
            }
            this.board.add(new Row(row, curRow));//creates a new row and adds it to the board
        }
        System.out.println(toString());
        // boardFlip();
        // System.out.println(toString());

        // this.board = copyBoard().board;
        // System.out.println(toString());
    }

    /**
     * This checks if the moves made during a turn were valid
     * @param moves
     */
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

    public void makeMove(Move curMove){
       curMove.getEnd().setPiece(curMove.getStart().getPiece());
       curMove.getStart().setPiece(null);

       System.out.println(toString());
       // board.set()
       // board.get(curMove.getStart().getRowIdx()).changeSpace(curMove.getStart().getCellIdx(), curMove.getStart());
    }

//    public Board( copyBoard){
//
//        for(int row = 0; row < BOARD_DIM; row++){
//            Row curRow = board.get(row);
//            ArrayList<Space> row1 = new ArrayList<Space>();//empty row collection that the flipped row will be put into
//            for(int col = 0; col < BOARD_DIM>; col++){
//                row1.add(curRow.getSpaces().get(col));
//            }
//            curRow.setSpaces(row1);
//            board.add(curRow);//puts the board back together
//        }
//        copyBoard.board = board;
//        return copyBoard;
//    }

    /**
     * this method flips the board so that it is in the correct orientation for a player's move
     */
    public void boardFlip() {
        ArrayList<Row> flippedBoard = new ArrayList<Row>();//empty board collection that the flipped board will be put into
        for(int row = BOARD_DIM - 1; row >= 0; row--) {
            Row curRow = board.get(row);
            ArrayList<Space> flippedRow = new ArrayList<>();//empty row collection that the flipped row will be put into
            for(int col = BOARD_DIM - 1; col >= 0; col--) {
                flippedRow.add(curRow.getSpaces().get(col));
            }
            curRow.setSpaces(flippedRow);
            flippedBoard.add(curRow);//puts the board back together
        }
        this.board = flippedBoard;
    }

    public Piece.Color getActiveColor() {
        return activeColor;
    }
//    public void setActiveColor(){
//        this.activeColor =
//    }

    @Override
    public Iterator<Row> iterator() {
        return board.iterator();
    }

    /**
     * puts the board in text format is used for debugging
     * @return
     */
    @Override
    public String toString() {
        StringBuilder textBoard = new StringBuilder();
        Iterator<Row> rowIterator = board.iterator();
        while(rowIterator.hasNext()) {

            Row curRow = rowIterator.next();
            Iterator<Space> colIterator = curRow.iterator();

            while(colIterator.hasNext()) {
                Space curSpace = colIterator.next();

                if(!curSpace.isValid()) {
                    textBoard.append("*");//none playable spot
                }
                else if(curSpace.getPiece() == null) {//playable spot
                    textBoard.append("_");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.RED && curSpace.getPiece().getType() == Piece.Type.SINGLE) {//single red
                    textBoard.append("r");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.WHITE && curSpace.getPiece().getType() == Piece.Type.SINGLE) {//single white
                    textBoard.append("w");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.RED && curSpace.getPiece().getType() == Piece.Type.KING) {//king white
                    textBoard.append("R");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.WHITE && curSpace.getPiece().getType() == Piece.Type.KING) {//king red
                    textBoard.append("W");
                }
            }
            textBoard.append("\n");
        }
        return textBoard.toString();
    }

    public static void main(String[] args) {//for debugging purposes only
        new Board();
    }
}
