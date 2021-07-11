package com.webcheckers.model;

import java.util.ArrayList;

/**
 * This class is responsible for creating a piece.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

public abstract class Piece {

    /**
     * Enum for the color of pieces.
     */
    public enum Type {SINGLE, KING}

    /**
     * Enum for the color of pieces.
     */
    public enum Color {RED, WHITE}

    // Color of piece.
    private final Color color;

    // Color of piece.
    private final Type type;

    /**
     * Constructor for a chess piece.
     *
     * @param color
     *     The color of the piece.
     */
    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract ArrayList<SingleMove> allSingleMoves(int row, int col);

    //this will be overridden by the 2 different types of pieces
    public abstract ArrayList<JumpMove> allJumps(int row, int col);

    /**
     * A getter method for the color of piece.
     *
     * @return
     *     The color of piece.
     */
    public Color getColor() {
        return color;
    }

    /**
     * A getter method for the type of piece.
     *
     * @return
     *     The type of piece.
     */
    public Type getType () {
        return type;
    }
}