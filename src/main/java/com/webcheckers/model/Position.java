package com.webcheckers.model;

import java.util.Objects;

/**
 *
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class Position {

    //
    private int row;

    //
    private int cell;

    /**
     *
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     *
     */
    public int getCell() {
        return cell;
    }

    /**
     *
     */
    public int getRow() {
        return row;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Position) {
            Position position = (Position) o;
            return row == position.row &&
                    cell == position.cell;
        }
        else {
            return false;
        }
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, cell);
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", cell=" + cell +
                '}';
    }
}