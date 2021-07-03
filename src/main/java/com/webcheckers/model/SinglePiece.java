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
//    @Override
//    public ArrayList<Move> allSingleMoves(int row, int col){
//        ArrayList<Move> moves = new ArrayList<Move>();
//        Position start = new Position(row , col);
//        //can move 1 the positive direction
//        for(int i = -1 ; i <= 1; i+=2) {
//            Position end = new Position(row + 1, col + i);
//            Move move = new Move(start, end);
//            moves.add(move);
//        }
//        return moves;
//    }
    @Override
    public ArrayList<SingleMove> allSingleMoves(Space start){
        ArrayList<SingleMove> moves = new ArrayList<SingleMove>();
        //get cur space
        //can move 1 the positive direction
        for(int i = -1 ; i <= 1; i+=2) {
            //get end space
            SingleMove move = new SingleMove(start, end);
            moves.add(move);
        }
        return moves;
    }
    @Override
    //this will be overridden by the 2 different types of pieces
    public ArrayList<Move> allJumps(){
        return new ArrayList<Move>(null);

    }
}
