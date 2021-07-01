package com.webcheckers.model;

public class SingleMove {

    private final Space start;
    private final Space end;
    private final Piece piece;

    public SingleMove(Space start, Space end, Piece piece) {
        this.start = start;
        this.end = end;
        this.piece = piece;
    }

    public boolean validateMove() {
        return false;
    }

    public void executeMove() {
        this.end.setPiece(this.piece);
        this.start.setPiece(null);
    }
}
