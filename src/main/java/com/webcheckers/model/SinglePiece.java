package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The SinglePiece class is responsible for handling the functionality of a single piece and extending the functionality
 * of a Piece object.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class SinglePiece extends Piece {

    /**
     * Constructor for SinglePiece.
     *
     * @param color
     *         The color of the piece.
     */
    public SinglePiece(Color color) {
        super(Type.SINGLE, color);
    }

    /**
     * Determines all single moves for a single piece.
     *
     * @param row
     *         The start row position value.
     * @param col
     *         The start col position value.
     *
     * @return A list of single moves that this piece can make.
     */
    @Override
    public ArrayList<SingleMove> allSingleMoves(int row, int col) {
        ArrayList<SingleMove> moves = new ArrayList<>();
        Position start = new Position(row, col);
        // A single piece can single move only 1 space in the positive diagonal direction.
        for (int i = -1; i <= 1; i += 2) {
            Position end = new Position(row - 1, col + i);
            SingleMove move = new SingleMove(start, end);
            moves.add(move);
        }
        return moves;
    }

    /**
     * Determines all single moves for a single piece.
     *
     * @param row
     *         The start row position value.
     * @param col
     *         The start col position value.
     *
     * @return A list of single moves that this piece can make.
     */
    @Override
    public ArrayList<JumpMove> allJumps(int row, int col) {
        ArrayList<JumpMove> moves = new ArrayList<>();
        Position start = new Position(row, col);
        // A single piece can jump move only 2 spaces in the positive diagonal direction.
        for (int i = -2; i <= 2; i += 4) {
            Position end = new Position(row - 2, col + i);
            JumpMove move = new JumpMove(start, end);
            moves.add(move);
        }
        return moves;
    }
}