package com.webcheckers.model;

import com.webcheckers.model.Row;

import java.util.Iterator;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardView rows = (BoardView) o;
        for (Row row : rows)
        {
            for(Row row2 : this)
            {
                if(row.equals(row2))
                {
                    break;
                } else
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(iterator);
    }
}
