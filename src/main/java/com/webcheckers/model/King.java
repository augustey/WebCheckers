package com.webcheckers.model;

import java.util.ArrayList;

public class King extends Piece{
    /**
     * Constructor for a chess piece.
     *
     * @param type  The type of piece.
     * @param color
     */
    public King(Type type, Color color) {
        super(type, color);
    }

    @Override
    public ArrayList<SingleMove> allSingleMoves(int row, int col) {
        ArrayList<SingleMove> moves = new ArrayList<>();
        Position start = new Position(row , col);
        //can move 1 in positive and negative direction
        for(int i = -1 ; i <= 1; i+=1) {
            for (int j = -1; j <= 1; j += 1) {
                Position end = new Position(row + i, col + j);
                SingleMove move = new SingleMove(start, end);
                moves.add(move);
            }
        }
        return moves;
    }

    @Override
    public ArrayList<JumpMove> allJumps(int row, int col) {
        return null;
    }
}