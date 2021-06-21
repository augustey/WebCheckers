package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Row>{

    private final int BOARD_DIM = 8;//the size of the board

    private ArrayList<Row> board = new ArrayList<Row>();

    @Override
    public Iterator<Row> iterator() {
        return null;
    }

    /**
     * Makes the starting board
     */
    public Board(){
        Space space;
        for(int row = 0; row < BOARD_DIM; row++){
            ArrayList<Space> curRow = new ArrayList<Space>();//holds what will latter be put into a row object
            for(int col = 0; col < BOARD_DIM; col++){
                space = new Space(col);//creates a blank space
                if(col % 2 + row % 2 == 1){//is on valid spot if only row or col is even but not both
                    space.setIsValid(true);//because it is a valid spot it needs to be set as such
                    if(row > BOARD_DIM - 3)//for white piece placement
                    {
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));//fills the blank space with a white piece
                    }
                    else if(row < 3){//for red piece placement
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.RED));//fills the blank space with a red piece
                    }

                }
                curRow.add(space);//puts space into the collection holding all spaces in row
            }
            this.board.add(new Row(row, curRow));//creates a new row and adds it to the board
        }
        System.out.println();
        System.out.println(toString());
    }

    /**
     * puts the board in text format is used for debugging
     * @return
     */
    @Override
    public String toString(){
        String textBoard = "";
        Iterator<Row> rowIterator = board.iterator();
        while (rowIterator.hasNext()){
            Row curRow = rowIterator.next();
            Iterator<Space> colIterator = curRow.iterator();
            while (colIterator.hasNext()){
                Space curSpace = colIterator.next();

                if(!curSpace.isValid()){
                    textBoard = textBoard + ("*");
                }
                else if(curSpace.getPiece() == null){
                    textBoard = textBoard + ("_");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.RED){
                    textBoard = textBoard + ("r");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.WHITE){
                    textBoard = textBoard + ("w");
                }


            }
            textBoard = textBoard + "\n";
        }
        return textBoard;
    }
    public static void main(String[] args) {//for debugging purposes only
        new Board();
    }
}
