package com.webcheckers.model;

import java.util.ArrayList;

/**
 * This class is responsible for creating a piece.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

public abstract class Piece {


//    private int row;
//    private int col;

    /**
     * Enum for the type of pieces.
     */
    public enum Type {SINGLE, KING}

    /**
     * Enum for the color of pieces.
     */
    public enum Color {RED, WHITE}

    // Type of piece.
    private final Type type;

    // Color of piece.
    private final Color color;

    /**
     * Constructor for a chess piece.
     *
     * @param type
     *     The type of piece.
     *
     * @param color
     *     The color of the piece.
     */
    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract ArrayList<Move> allSingleMoves(int row, int col);

    //this will be overridden by the 2 different types of pieces
    public abstract ArrayList<Move> allJumps();

    /**
     * A getter method for the type of piece.
     *
     * @return
     *     The type of piece.
     */
    public Type getType () {
        return type;
    }

    /**
     * A getter method for the color of piece.
     *
     * @return
     *     The color of piece.
     */
    public Color getColor() {
        return color;
    }

}
