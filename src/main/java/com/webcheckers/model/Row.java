package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for creating a row.
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
     *     The index of the row on a board.
     *
     * @param spaces
     *     An arraylist of spaces in this row.
     */
    public Row(int index, ArrayList<Space> spaces) {
        this.index = index;
        this.spaces = spaces;
    }

    /**
     * A getter method for the spaces in this row.
     *
     * @return
     *     The spaces in this row.
     */
    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    /**
     * A getter method for the index of this row.
     *
     * @return
     *     The index of this row.
     */
    public int getIndex() {
        return index;
    }

    /**
     * A setter method for changing the spaces in a row.
     * NOTE: This method is only used for changing a board's orientation.
     *
     * @param spaces
     *     The spaces for this row to become.
     */
    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
