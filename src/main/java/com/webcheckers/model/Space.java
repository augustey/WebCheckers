package com.webcheckers.model;

public class Space {

    private int cellIdx;//the cells in a row 0 - 7
    private boolean valid;//if the landing spot is valid
    private Piece piece;


    /**
     * constructor
     * @param cellIdx
     */
    public Space(int cellIdx){
        this.cellIdx = cellIdx;
        this.piece = null;
        this.valid = false;
    }

    /**
     *
     * @return
     */
    public int getCellIdx(){
        return cellIdx;
    }

    /**
     *
     * @return
     */
    public boolean isValid(){
        return valid;
    }

    /**
     *
     * @return
     */
    public Piece getPiece(){
        return piece;
    }

}
