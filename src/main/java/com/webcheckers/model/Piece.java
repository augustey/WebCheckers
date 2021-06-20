package com.webcheckers.model;

public class Piece {

    public enum Type{SINGLE, KING};
    public enum Color{RED, WHITE};

    private Type type;
    private Color color;

    /**
     * constructor
     * @param type
     * @param color
     */
    public Piece(Type type, Color color){
        this.type = type;
        this.color = color;
    }

    /**
     * returns the type of piece it is (king or regular)
     * @return
     */
    public Type getType (){
        return type;
    }

    /**
     * returns the color of the piece (red or white)
     * @return
     */
    public Color getColor(){
        return color;
    }

}
