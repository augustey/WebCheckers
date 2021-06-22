package com.webcheckers.model;

/**
 * this class handles moves
 */
public class Move {

    private final Space start;
    private final Space end;
    private static Space jumped;
    private final Piece.Color activeColor;
    private final boolean isValid;

    public enum TypeOfMove{SINGLE_MOVE, JUMP};
    private TypeOfMove typeOfMove;
//
//    private TypeOfMove typeOfMove;


    public Move(Space start, Space end, Piece.Color activeColor) {
        this.start = start;
        this.end = end;
        this.activeColor = activeColor;
        this.isValid = validateMove();
    }

    public boolean validateMove(){
        if(start.isValid() && end.isValid() &&
                start.getPiece().getColor() == activeColor && end.getPiece() == null){
            return ((isJump() || isSingleMove()) && direction());
        }
        return false;
    }

    /**
     * determines if the move was a singleMove
     * @return
     */
    public boolean isSingleMove(){
        if(Math.abs(start.getRowIdx() - end.getRowIdx()) == 1 &&
                Math.abs(start.getCellIdx() - end.getCellIdx()) == 1)//for singleMove the distance in the diagonal must be 1
        {
            typeOfMove = TypeOfMove.SINGLE_MOVE;
            return true;
        }
        return false;
    }

    /**
     * Determines if the move was a jump
     * @return
     */
    public boolean isJump(){
        if(Math.abs(start.getRowIdx() - end.getRowIdx()) == 2 &&
                Math.abs(start.getCellIdx() - end.getCellIdx()) == 2)//for jump the the distance in the diagonal must be 2
        {
//            if()
            //need to check validity
//            return true;
        }
        return false;
    }
    public boolean isValid(){
        return isValid;
    }

    /**
     * This checks that the peice is going in the proper direction Singles can only go forward
     * @return
     */
    public boolean direction(){
        return start.getPiece().getType() == Piece.Type.SINGLE &&
                (start.getRowIdx() - end.getRowIdx()) == -1;
    }


    //check if land is empty
    //check if move
    //check if jump
        //check if the jumped is correct
}
