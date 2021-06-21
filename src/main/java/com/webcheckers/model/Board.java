package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Row>{

    private final int BOARD_DIM = 8;//the size of the board
//    private ArrayList<Row> board;
//    private Piece.Color m
    private Space [][] board;

    @Override
    public Iterator<Row> iterator() {
        return null;
    }

    /**
     * Makes the starting board
     */
    public Board(){
        Space space;
        Piece piece;
        board = new Space[BOARD_DIM][BOARD_DIM];
        for(int row = 0; row < BOARD_DIM; row++){
            //create new row
            for(int col = 0; col < BOARD_DIM; col++){
                space = board[row][col] = new Space(col);
                if(col % 2 + row % 2 == 1){//is on red if only row or col is even but not both
                    if(row > BOARD_DIM - 2)//for white piece placement
                    {
                        piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
                    }
                    else if(row > 2){//for red piece placement
                        piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
                    }
                }
            }

        }

    }

    public static void main(String[] args) {//for debugging purposes only
        new Board();

    }
}
