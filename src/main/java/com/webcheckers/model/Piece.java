package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The Piece class is responsible for containing the outlined functionality for any Piece object that extends.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public abstract class Piece {

    /**
     * Enum for the type of pieces.
     */
    public enum Type {SINGLE, KING}

    /**
     * Enum for the color of pieces.
     */
    public enum Color {RED, WHITE}

    // Color of piece.
    private final Color color;

    // Type of piece.
    private final Type type;

    /**
     * Constructor for Piece.
     *
     * @param type
     *         The type of piece.
     * @param color
     *         The color of the piece.
     */
    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Abstract method that will determine all possible single moves for this piece.
     *
     * @param row
     *         The start row position value.
     * @param col
     *         The start col position value.
     *
     * @return A list of single moves that this piece can make.
     */
    public abstract ArrayList<SingleMove> allSingleMoves(int row, int col);

    /**
     * Abstract method that will determine all possible jump moves for this piece.
     *
     * @param row
     *         The start row position value.
     * @param col
     *         The start col position value.
     *
     * @return A list of jump moves that this piece can make.
     */
    public abstract ArrayList<JumpMove> allJumps(int row, int col);

    /**
     * A getter method for the color of piece.
     *
     * @return The color of piece.
     */
    public Color getColor() {
        return color;
    }

    /**
     * A getter method for the type of piece.
     *
     * @return The type of piece.
     */
    public Type getType() {
        return type;
    }

    /**
     * String representation of a Piece object.
     *
     * @return The Piece string.
     */
    @Override
    public String toString() {
        return "Piece{" + "type=" + type + ", color=" + color + "}";
    }
}