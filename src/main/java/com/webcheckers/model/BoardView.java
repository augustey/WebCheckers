package com.webcheckers.model;

import com.webcheckers.model.Row;

import java.util.Iterator;

/**
 * Wrapper class for board iterator.
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class BoardView implements Iterable<Row>{
    private final Iterator<Row> iterator;

    /**
     * Constructor
     * @param iterator iterator from the Board class
     */
    public BoardView(Iterator<Row> iterator) {
        this.iterator = iterator;
    }

    /**
     * Return the iterator from the Board class
     * @return board class iterator
     */
    @Override
    public Iterator<Row> iterator() {
        return iterator;
    }
}
