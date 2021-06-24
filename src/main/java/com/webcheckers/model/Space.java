package com.webcheckers.model;

public class Space {

    private int cellIdx;//the cells in a row 0 - 7
    private int rowIdx;
    private boolean isValid;//if the landing spot is valid
    private Piece piece;
    public enum Color{WHITE, BLACK};
    private Space.Color color;



    /**
     * constructor
     * @param cellIdx
     */
    public Space(int rowIdx, int cellIdx){
        this.color = Color.WHITE;
        this.cellIdx = cellIdx;
        this.rowIdx = rowIdx;
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
    public int getRowIdx(){
        return rowIdx;
    }

    public void setColor(Space.Color color){
        this.color = color;
    }




    /**
     *
     * @return
     */
    public Piece getPiece(){
        return piece;
    }

    public boolean isValid() {
        return piece == null && color == Color.BLACK;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
