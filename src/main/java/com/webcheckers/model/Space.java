package com.webcheckers.model;

public class Space {

    // The cell index a space is in a row
    private int cellIdx;
    // The row index the space is in.
    private int rowIdx;
    // If a valid space for pieces
    private boolean isValid;
    // A piece that is on that space, if any
    private Piece piece;
    /**
     * Enum for the color of spaces
     */
    public enum Color{WHITE, BLACK};
    // The color of this space
    private Space.Color color;

    /**
     * Constructor for creating a Space object
     *
     * @param cellIdx
     *     The index of this space (a cell within a row) within the board.
     *
     * @param rowIdx
     *
     */
    public Space(int rowIdx, int cellIdx) {
        this.color = Color.WHITE;
        this.cellIdx = cellIdx;
        this.rowIdx = rowIdx;
        this.piece = null;
        this.isValid = false;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     *
     * @return
     */
    public int getCellIdx() {
        return cellIdx;
    }
    public int getRowIdx() {
        return rowIdx;
    }

    public void setColor(Space.Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    public boolean isValid() {
        return piece == null && color == Color.BLACK;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
