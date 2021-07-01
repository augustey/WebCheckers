package com.webcheckers.model;

public class SingleMove {

    private final Space start;
    private final Space end;

//    public SingleMove(int startRow, int startCol, int endRow, int endCol) {
//        Space[][] board = Board.getBoard();
//        this.spaceStart = this.board[startRow][startCol];
//        this.spaceEnd = this.board[4][2];
//    }

    public SingleMove(Space start, Space end) {
        this.start = start;
        this.end = end;

    }

    private boolean validateMove() {
        //start - end = 1 row
        //abs(start - end) = 1 col
        if(start.getRowIdx() - end.getRowIdx() == 1) {
            return Math.abs(start.getColIdx() - end.getColIdx()) == 1;
        }
        return false;
    }

    public boolean executeMove() {
        if (validateMove()) {
            this.end.setPiece(this.start.getPiece());
            this.start.setPiece(null);
            return true;
        }
        return false;
    }



}
