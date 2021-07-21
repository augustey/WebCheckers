package com.webcheckers.model;

import java.util.Objects;

/**
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public  class Move {

    // The start position.
    private final Position start;

    // The end position
    private final Position end;

    /**
     * Constructor for Move.
     *
     * @param start
     *         The start position.
     * @param end
     *         The end position.
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A getter method for the start position.
     *
     * @return The start position.
     */
    public Position getStart() {
        return start;
    }

    /**
     * A getter method for the end position.
     *
     * @return The end position.
     */
    public Position getEnd() {
        return end;
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
        System.out.println("Move equals");
        if (this == other) {
            return true;
        }
        else if (other instanceof Move) {
            Move move = (Move) other;
            return Objects.equals(start, move.start) &&
                    Objects.equals(end, move.end);
        }

        return false;
    }

    /**
     * Creates a hashcode due to overriding equals method from the row and cell values.
     *
     * @return A unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     * String representation of a Move object.
     *
     * @return The Move string.
     */
    @Override
    public String toString() {
        return "Move{" + "start=" + start + ", end=" + end + "}";
    }
}