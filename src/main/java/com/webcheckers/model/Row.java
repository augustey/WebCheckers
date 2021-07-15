package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * The Row class is responsible for handling its spaces iterator.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Row implements Iterable<Space> {

    // Index of row on a board (between 0 and 7).
    private final int index;

    // An arraylist of the spaces that are in a row.
    private ArrayList<Space> spaces;

    /**
     * Constructor for a row.
     *
     * @param index
     *         The index of the row on a board.
     * @param spaces
     *         An arraylist of spaces in this row.
     */
    public Row(int index, ArrayList<Space> spaces) {
        this.index = index;
        this.spaces = spaces;
    }

    /**
     * A getter method for the index of this row.
     *
     * @return The index of this row.
     */
    public int getIndex() {
        return index;
    }

    /**
     * A getter method for the spaces in this row.
     *
     * @return The spaces in this row.
     */
    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    /**
     * A setter method for changing the spaces in a row. NOTE: This method is only used for changing a board's
     * orientation.
     *
     * @param spaces
     *         The spaces for this row to become.
     */
    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    /**
     * A getter method for the spaces list iterator.
     *
     * @return The spaces iterator.
     */
    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    /**
     * Equals method that compares two Rows together.
     *
     * @param other
     *         The other row object.
     *
     * @return True if the row objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof Row) {
            Row row = (Row) other;
            List<Space> list1 = new ArrayList<>();
            List<Space> list2 = new ArrayList<>();
            row.forEach(list1::add);
            this.forEach(list2::add);
            return list1.equals(list2);
        }
        return false;
    }

    /**
     * Creates a hashcode due to overriding equals method from the index and spaces.
     *
     * @return A unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(index, spaces);
    }
}