package com.webcheckers.model;

import java.util.ArrayList;

public class SinglePiece extends Piece {
    /**
     * Constructor for a chess piece.
     *
     * @param type  The type of piece.
     * @param color
     */
    public SinglePiece(Type type, Color color) {
        super(type, color);
    }

    @Override
    public ArrayList<SingleMove> allSingleMoves(int row, int col){
        ArrayList<SingleMove> moves = new ArrayList<>();
        Position start = new Position(row , col);
        //can move 1 the positive direction
        for(int i = -1 ; i <= 1; i+=2) {
            Position end = new Position(row - 1, col + i);
            SingleMove move = new SingleMove(start, end);
            moves.add(move);
        }
        return moves;
    }
    //    @Override
//    public ArrayList<SingleMove> allSingleMoves(Space start){
//        ArrayList<SingleMove> moves = new ArrayList<SingleMove>();
//        //get cur space
//        //can move 1 the positive direction
//        for(int i = -1 ; i <= 1; i+=2) {
//            //get end space
//            SingleMove move = new SingleMove(start, end);
//            moves.add(move);
//        }
//        return moves;
//    }
    @Override
    //this will be overridden by the 2 different types of pieces
    public ArrayList<JumpMove> allJumps(int row, int col){
        ArrayList<JumpMove> moves = new ArrayList<>();
        Position start = new Position(row , col);
        //can move 2 the positive direction
        for(int i = -2 ; i <= 2; i+=4) {
            Position end = new Position(row - 2, col + i);
            JumpMove move = new JumpMove(start, end);
            moves.add(move);
        }
        return moves;
    }
}