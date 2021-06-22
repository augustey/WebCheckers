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
                space = new Space(row,col);//creates a blank space
                if(col % 2 + row % 2 == 1){//is on valid spot if only row or col is even but not both
                    space.setIsValid(true);//because it is a valid spot it needs to be set as such
                    if(row > BOARD_DIM - 4)//for white piece placement 3 rows of them
                    {
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.WHITE));//fills the blank space with a white piece
                    }
                    else if(row < 3){//for red piece placement 3 rows of them
                        space.setPiece(new Piece(Piece.Type.SINGLE, Piece.Color.RED));//fills the blank space with a red piece
                    }

                }
                curRow.add(space);//puts space into the collection holding all spaces in row
            }
            this.board.add(new Row(row, curRow));//creates a new row and adds it to the board
        }

        System.out.println(toString());
        boardFlip();
        System.out.println(toString());
    }
    public void validateMove(ArrayList<Space> moves){
        //row = cellIdx
        //col = location in
        Space startSpace;
        Space endSpace;
        if(moves.size() > 1) {
            Iterator<Space> moveIterator = moves.iterator();//moves can be made up of 2+ spaces
            startSpace = moveIterator.next();
            while (moveIterator.hasNext()) {
                endSpace = moveIterator.next();
                //can eather be a move or a jump

                startSpace = endSpace;
            }
        }

    }
    public void makeMove(){


    }

    public void copyBoard (ArrayList<Row> other){
        this.board = other;
    }

    public void boardFlip(){
        ArrayList<Row> flipedBoard = new ArrayList<Row>();
        for(int row = BOARD_DIM - 1; row >= 0; row--){
            Row curRow = board.get(row);
            ArrayList<Space> flipedRow = new ArrayList<Space>();
            for(int col = BOARD_DIM - 1; col >= 0; col--){
                flipedRow.add(curRow.getSpaces().get(col));
            }
            curRow.setSpaces(flipedRow);
            flipedBoard.add(curRow);
        }
        copyBoard(flipedBoard);
    }



    /**
     * puts the board in text format is used for debugging
     * @return
     */
    @Override
    public String toString(){
        StringBuilder textBoard = new StringBuilder();
        Iterator<Row> rowIterator = board.iterator();
        while(rowIterator.hasNext()){

            Row curRow = rowIterator.next();
            Iterator<Space> colIterator = curRow.iterator();

            while(colIterator.hasNext()){
                Space curSpace = colIterator.next();

                if(!curSpace.isValid()){
                    textBoard.append("*");//none playable spot
                }
                else if(curSpace.getPiece() == null){//playable spot
                    textBoard.append("_");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.RED && curSpace.getPiece().getType() == Piece.Type.SINGLE){//single red
                    textBoard.append("r");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.WHITE && curSpace.getPiece().getType() == Piece.Type.SINGLE){//single white
                    textBoard.append("w");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.RED && curSpace.getPiece().getType() == Piece.Type.KING){//king white
                    textBoard.append("R");
                }
                else if(curSpace.getPiece().getColor() == Piece.Color.WHITE && curSpace.getPiece().getType() == Piece.Type.KING){//king red
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
