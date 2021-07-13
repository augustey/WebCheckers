package com.webcheckers.model;

import java.util.Objects;

/**
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class Position {

    // The position row value.
    private int row;

    // The position cell value.
    private int cell;

    /**
     * Constructor for Position.
     *
     * @param row
     *         The row value.
     * @param cell
     *         The cell value.
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * A getter method for the row value.
     *
     * @return The row value.
     */
    public int getRow() {
        return row;
    }

    /**
     * A getter method for the cell value.
     *
     * @return The cell value.
     */
    public int getCell() {
        return cell;
    }

    /**
     * Equals method that compares two Positions together.
     *
     * @param other
     *         The other position object.
     *
     * @return True if the position objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof Position) {
            Position position = (Position) other;
            return position.row == row && position.cell == cell;
        }
        return false;
    }

    /**
     * Creates a hashcode due to overriding equals method from the row and cell values.
     *
     * @return The hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, cell);
    }

    /**
     * String representation of a Position object.
     */
    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", cell=" + cell + "}";
    }
}