package com.webcheckers.model;

import java.util.Objects;

/**
 * The Space class is responsible for handling piece placement.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Space {

    // The row index a space is in.
    private final int rowIdx;

    // The cell index a space is in a row.
    private final int colIdx;

    // If a valid space for pieces.
    private final boolean isValid;

    // A piece that is on that space, if any.
    private Piece piece;

    /**
     * Constructor for creating a Space object.
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
     * Copy constructor for space.
     *
     * @param copy
     *         A Space to copy.
     */
    public Space(Space copy) {
        this.rowIdx = copy.rowIdx;
        this.colIdx = copy.colIdx;
        if (copy.piece instanceof SinglePiece) {
            this.piece = new SinglePiece(copy.piece.getColor());
        }
        else if (copy.piece instanceof King) {
            this.piece = new King(copy.piece.getColor());
        }
        this.isValid = copy.isValid;
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
     * Equals method that compares two spaces together.
     *
     * @param other
     *         The other space object.
     *
     * @return True if the space objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof Space) {
            Space otherSpace = (Space) other;
            if (otherSpace.rowIdx == rowIdx && otherSpace.colIdx == colIdx && otherSpace.isValid == isValid) {
                return (otherSpace.piece == null && this.piece == null) || (Objects.equals(otherSpace.piece, this.piece));
            }
        }
        return false;
    }
}