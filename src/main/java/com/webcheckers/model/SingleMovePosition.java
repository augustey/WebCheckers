package com.webcheckers.model;

public class SingleMovePosition
{

    private final Position start;
    private final Position end;

    public SingleMovePosition(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public boolean validateMove() {
        //start - end = 1 row
        //abs(start - end) = 1 col
        if(start.getRow() - end.getRow() == 1) {
            return Math.abs(start.getCell() - end.getCell()) == 1;
        }
        return false;
    }



}
