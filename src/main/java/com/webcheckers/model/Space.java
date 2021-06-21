package com.webcheckers.model;

public class Space {

    private int cellIdx;//the cells in a row 0 - 7
    private boolean isValid;//if the landing spot is valid
    private Piece piece;


    /**
     * constructor
     * @param cellIdx
     */
    public Space(int cellIdx){
        this.cellIdx = cellIdx;
        this.piece = null;
        this.isValid = false;
    }

    public void setPiece( Piece piece){
        this.piece = piece;
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
    public Piece getPiece(){
        return piece;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
