package com.webcheckers.model;

import java.util.Objects;

/**
 * The JumpMove class is responsible for handling the logic for a jump move and extending the functionality of a Move
 * object.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class JumpMove extends Move {

    // The jumped over position.
    private Position jumped;

    /**
     * Constructor for JumpMove that determines the jumped over position.
     *
     * @param start
     *         The start position.
     * @param end
     *         The end position.
     */
    public JumpMove(Position start, Position end) {
        super(start, end);
        this.createJumpPosition();
    }


    /**
     * Constructor for JumpMove that is used to convert a generic Move to JumpMove
     * @param move Generic Move object
     */
    public JumpMove(Move move) {
        super(move.getStart(), move.getEnd());
        this.createJumpPosition();
    }

    /**
     * Calculates the jumped over position by performing a midpoint calculation.
     */
    private void createJumpPosition() {
        int row1 = super.getStart().getRow();
        int row2 = super.getEnd().getRow();
        int col1 = super.getStart().getCell();
        int col2 = super.getEnd().getCell();

        int r = (row1 + row2) / 2;
        int c = (col1 + col2) / 2;

        this.jumped = new Position(r, c);
    }

    /**
     * A getter method for the jumped over position.
     *
     * @return The jumped over position.
     */
    public Position getJumpedPosition() {
        return this.jumped;
    }

    /**
     * Equals method that compares two Move objects together
     * Used to compare move, simpleMove and jumpMove
     * @param other
     *         The other move object.
     *
     * @return True if the move objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof JumpMove) {
            JumpMove move = (JumpMove) other;
            return Objects.equals(jumped, move.jumped) || (Objects.equals(getStart(), move.getStart()) &&
                    Objects.equals(getEnd(), move.getStart()));
        }
        return false;
    }
}