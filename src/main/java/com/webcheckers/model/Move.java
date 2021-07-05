package com.webcheckers.model;

public class Move
{
    private Position start;
    private Position end;

    //TODO make into abstact class with singleMove and jump subclasses
    public Move(Position start, Position end)
    {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public boolean validateMove(){
        //Stub
        return true;
    }

    @Override
    public String toString()
    {
        return "Move{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
