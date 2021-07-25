package com.webcheckers.model;

/**
 * The SingleMove class is responsible for handling the functionality of a single move and extending the functionality
 * of a Move object.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class SingleMove extends Move {

    /**
     * Constructor for SingleMove.
     *
     * @param start
     *         The start position.
     * @param end
     *         The end position.
     */
    public SingleMove(Position start, Position end) {
        super(start, end);
    }

    /**
     * Constructor for SingleMove used to convert generic Move object to SingleMove.
     *
     * @param move
     *         A generic move.
     */
    public SingleMove(Move move) {
        super(move.getStart(), move.getEnd());
    }
}