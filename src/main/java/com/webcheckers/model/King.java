package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The king class is responsible for handling the functionality of a King piece and extending the functionality of a
 * Move object.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class King extends Piece {

    /**
     * Constructor for King piece.
     *
     * @param color
     *         The color of the piece.
     */
    public King(Color color) {
        super(Type.KING, color);
    }

    /**
     * Determines all single moves for a King piece.
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
        // A king can single move only 1 space in both the positive and negative diagonal direction.
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                Position end = new Position(row + i, col + j);
                SingleMove move = new SingleMove(start, end);
                moves.add(move);
            }
        }
        return moves;
    }

    /**
     * Determines all jump moves for a King piece.
     *
     * @param row
     *         The start row position value.
     * @param col
     *         The start col position value.
     *
     * @return A list of jump moves that this piece can make.
     */
    @Override
    public ArrayList<JumpMove> allJumps(int row, int col) {
        ArrayList<JumpMove> moves = new ArrayList<>();
        Position start = new Position(row, col);
        // A king can jump move only 2 spaces in both the positive and negative diagonal direction.
        for (int i = -2; i <= 2; i += 4) {
            for (int j = -2; j <= 2; j += 4) {
                Position end = new Position(row + i, col + j);
                JumpMove move = new JumpMove(start, end);
                moves.add(move);
            }
        }
        return moves;
    }
}