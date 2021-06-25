package com.webcheckers.model;

public class Piece {
    /**
     * Enum for the type of pieces.
     */
    public enum Type{SINGLE, KING};
    /**
     * Enum for the color of pieces.
     */
    public enum Color{RED, WHITE};

    // Type of piece
    private Type type;
    // Color of piece
    private Color color;

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
