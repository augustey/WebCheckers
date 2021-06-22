package com.webcheckers.model;

/**
 * this class handles moves
 */
public class Move {
    private final Space start;
    private final Space end;
    private boolean valid;
//    public enum TypeOfMove{SINGLE_MOVE, JUMP};
//
//    private TypeOfMove typeOfMove;


    public Move(Space start, Space end) {
        this.start = start;
        this.end = end;
//        isJump();
//        isSingleMove();
    }
    public boolean isJump(){
        if(Math.abs(start.getRowIdx() - end.getRowIdx()) == 2 &&
                Math.abs(start.getCellIdx() - end.getCellIdx()) == 2)//for jump the the distance in the diagonal must be 2
        {
            //need to check validity
            return true;
        }
        return false;
    }
    public boolean isSingleMove(){
        if(Math.abs(start.getRowIdx() - end.getRowIdx()) == 1 &&
                Math.abs(start.getCellIdx() - end.getCellIdx()) == 1)//for singleMove the distance in the diagonal must be 1
        {
            //need to check validity(in the case of single jump only direction of the jump is needed)
            return true;
        }
        return false;
    }
//    public TypeOfMove getTypeOfMove(){
//        return typeOfMove;
//    }

    //check if land is empty
    //check if move
    //check if jump
        //check if the jumped is correct
}
