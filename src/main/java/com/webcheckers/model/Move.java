package com.webcheckers.model;

/**
 * this class handles moves
 */
public class Move {
    private final Space start;
    private final Space end;
    public enum TypeOfMove{SINGLE_MOVE, JUMP};

    private TypeOfMove typeOfMove;


    public Move(Space start, Space end) {
        this.start = start;
        this.end = end;
        isJump();
        isSingleMove();
    }
    public void isJump(){

    }
    public void isSingleMove(){

    }
    public TypeOfMove getTypeOfMove(){
        return typeOfMove;
    }

    //check if land is empty
    //check if move
    //check if jump
        //check if the jumped is correct
}
