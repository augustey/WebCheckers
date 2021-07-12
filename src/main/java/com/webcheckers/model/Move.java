package com.webcheckers.model;

import java.util.Objects;

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

//    public boolean validateMove(){
//        //Stub
//        return true;
//    }

    @Override
    public String toString()
    {
        return "Move{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(start, move.start) &&
                Objects.equals(end, move.end);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(start, end);
    }
}
