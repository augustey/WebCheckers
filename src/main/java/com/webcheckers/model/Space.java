package com.webcheckers.model;

/**
 * This class is responsible for creating a space.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Space {

    // The cell index a space is in a row
    private int cellIdx;
    // If a valid space for pieces
    private boolean isValid;
    // A piece that is on that space, if any
    private Piece piece;
    // The color of this space
    private Board.Color color;

    /**
     * Constructor for creating a Space object
     *
     * @param cellIdx
     *     The index of this space (a cell within a row) within the board.
     */
    public Space(int cellIdx, Board.Color color, Piece piece, boolean isValid) {
        this.color = color;
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.isValid = isValid;
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

    /**
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    public boolean isValid() {
        return piece == null && isValid;
    }
}
