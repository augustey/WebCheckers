package com.webcheckers.model;

import java.util.*;

/**
 * The BoardView class is the wrapper for Board class's iterator.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class BoardView implements Iterable<Row> {

    // BoardView's iterator.
    private final Iterator<Row> iterator;

    /**
     * Constructor for BoardView that initializes an iterator.
     *
     * @param iterator
     *         The iterator from the Board class
     */
    public BoardView(Iterator<Row> iterator) {
        this.iterator = iterator;
    }

    /**
     * Return the iterator from the Board class
     *
     * @return The board class iterator
     */
    @Override
    public Iterator<Row> iterator() {
        return iterator;
    }

    /**
     * Equals method that compares two boardViews together.
     *
     * @param other
     *         The other boardview object.
     *
     * @return True if the boardview objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof BoardView) {
            BoardView boardView = (BoardView) other;
            List<Row> list1 = new ArrayList<>();
            List<Row> list2 = new ArrayList<>();
            boardView.forEach(list1::add);
            this.forEach(list2::add);
            return list1.equals(list2);
        }
        return false;
    }

    /**
     * Creates a hashcode due to overriding equals method.
     *
     * @return A unique hashcode for this iterator.
     */
    @Override
    public int hashCode() {
        return Objects.hash(iterator);
    }
}