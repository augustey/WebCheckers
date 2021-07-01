package com.webcheckers.model;

public class SingleMove {

    private final Space start;
    private final Space end;


    public SingleMove(Space start, Space end) {
        this.start = start;
        this.end = end;

    }

    public boolean validateMove() {
        return false;
    }

    public void executeMove() {
        this.end.setPiece(this.start.getPiece());
        this.start.setPiece(null);
    }

}
