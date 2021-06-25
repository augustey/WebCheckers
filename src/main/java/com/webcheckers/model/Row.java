package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class is responsible for creating a row.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Row implements Iterable<Space> {

    private int index;//what row it is 0 - 7

    private ArrayList<Space> spaces;//array of the spaces that make up the row

    public Row(int index, ArrayList<Space> spaces) {
        this.index = index;
        this.spaces = spaces;
    }

    public void changeSpace(int col, Space space) {
        spaces.set(col, space);
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator(){
        return spaces.iterator();
    }
}
