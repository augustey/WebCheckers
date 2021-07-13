package com.webcheckers.model;

import java.util.ArrayList;

/**
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class SinglePiece extends Piece {

    /**
     * Constructor for a chess piece.
     *
     * @param color
     */
    public SinglePiece(Color color) {
        super(Type.SINGLE, color);
    }

    /**
     * @param row
     * @param col
     *
     * @return
     */
    @Override
    public ArrayList<SingleMove> allSingleMoves(int row, int col) {
        ArrayList<SingleMove> moves = new ArrayList<>();
        Position start = new Position(row, col);
        //can move 1 the positive direction
        for (int i = -1; i <= 1; i += 2) {
            Position end = new Position(row - 1, col + i);
            SingleMove move = new SingleMove(start, end);
            moves.add(move);
        }
        return moves;
    }

    /**
     * @param row
     * @param col
     *
     * @return
     */
    @Override
    //this will be overridden by the 2 different types of pieces
    public ArrayList<JumpMove> allJumps(int row, int col) {
        ArrayList<JumpMove> moves = new ArrayList<>();
        Position start = new Position(row, col);
        //can move 2 the positive direction
        for (int i = -2; i <= 2; i += 4) {
            Position end = new Position(row - 2, col + i);
            JumpMove move = new JumpMove(start, end);
            moves.add(move);
        }
        return moves;
    }
}