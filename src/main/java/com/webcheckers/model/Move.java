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

    public Move(Position start) {
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public void execute() {

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
