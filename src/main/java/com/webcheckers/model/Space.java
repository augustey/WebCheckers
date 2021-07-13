package com.webcheckers.model;

/**
 * This class is responsible for creating a space.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Space {

    // The row index a space is in
    private final int rowIdx;

    // The cell index a space is in a row
    private final int colIdx;

    // If a valid space for pieces
    private final boolean isValid;

    // A piece that is on that space, if any
    private Piece piece;

    /**
     * Constructor for creating a Space object
     *
     * @param colIdx
     *         The index of this space (a cell within a row) within the board.
     * @param piece
     *         The piece, if any, on this space.
     * @param isValid
     *         True, if this space is allowed to have a piece on it, else, false.
     */
    public Space(int rowIdx, int colIdx, Piece piece, boolean isValid) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.piece = piece;
        this.isValid = isValid;
    }

    /**
     * A getter method for the index of this space in a row.
     *
     * @return The index of this space in a row.
     */
    public int getColIdx() {
        return colIdx;
    }

    /**
     * A getter method for the index of this space's row.
     *
     * @return The index of this space's row.
     */
    public int getRowIdx() {
        return rowIdx;
    }

    /**
     * A getter method for the piece on this space.
     *
     * @return The piece on this space, or null.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * A getter method for the validness of a space (a black space or not).
     *
     * @return The validness of a space (a black space or not).
     */
    public boolean getIsValid() {
        return isValid;
    }

    /**
     * Set the piece on this space.
     *
     * @param piece
     *         The piece to placed on this space.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Determine if this is a valid space for a piece.
     *
     * @return True if there is no piece and a valid space, else, false.
     */
    public boolean isValid() {
        return piece == null && isValid;
    }

    /**
     * @param o
     *
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o instanceof Space) {
            Space otherSpace = (Space) o;
            return otherSpace.rowIdx == rowIdx &&
                   otherSpace.colIdx == colIdx &&
                   otherSpace.piece == piece &&
                   otherSpace.isValid == isValid;
        }
        return false;
    }
}