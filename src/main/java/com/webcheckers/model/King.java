package com.webcheckers.model;

import java.util.ArrayList;

public class King extends Piece{
    /**
     * Constructor for a chess piece.
     *
     * @param color
     */
    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    public ArrayList<SingleMove> allSingleMoves(int row, int col) {
        ArrayList<SingleMove> moves = new ArrayList<>();
        Position start = new Position(row , col);
        //can move 1 in positive and negative direction
        for(int i = -1 ; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                Position end = new Position(row + i, col + j);
                SingleMove move = new SingleMove(start, end);
                moves.add(move);
            }
        }
        return moves;
    }

    @Override
    public ArrayList<JumpMove> allJumps(int row, int col) {
        ArrayList<JumpMove> moves = new ArrayList<>();
        Position start = new Position(row , col);
        //can move 2 the positive/negative direction
        for(int i = -2 ; i <= 2; i += 4) {
            for (int j = -2; j <= 2; j += 4) {
                Position end = new Position(row + i, col + j);
                JumpMove move = new JumpMove(start, end);
                moves.add(move);
            }
        }
        return moves;
    }
}