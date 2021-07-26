package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.JumpMove;
import com.webcheckers.model.Move;
import com.webcheckers.model.SingleMove;

import java.util.ArrayList;

/**
 * The Turn class is responsible for managing a player's turn.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Turn {

    // List of moves for a turn.
    private ArrayList<Move> moves;

    // The move type for the player's turn.
    private Board.MoveType moveType;

    public Turn(Board.MoveType moveType) {
        this.moveType = moveType;
        this.moves = new ArrayList<>();
    }

    /**
     * Adds a move to the turn if it is found to not be jumping over what is already jumped.
     *
     * @param move
     *         The move under consideration.
     */
    public boolean addMove(Move move) {
        if (moveType == Board.MoveType.Jump) {
            JumpMove jumpMove = new JumpMove(move);
            //This uses the custom equals method in JumpMove to make sure that no piece is jumped twice
            if (!moves.contains(jumpMove)) {
                moves.add(jumpMove);
                return true;
            }
        }
        else if (moveType == Board.MoveType.Single) {
            SingleMove singleMove = new SingleMove(move);
            moves.add(singleMove);
            return true;
        }
        return false;
    }

    /**
     * Removes the last move in the turn.
     */
    public Move removeMove() {
        try {
            int i = moves.size() - 1;
            return moves.remove(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * A getter method for collection of moves that make up the turn.
     *
     * @return The list of moves.
     */
    public ArrayList<Move> getMoves() {
        return moves;
    }

    /**
     * Resets the collection of moves to empty.
     */
    public void clearTurnMoves() {
        moves.clear();
    }

    /**
     * Sets the move type.
     *
     * @param moveType
     *         A Board.MoveType moveType.
     */
    public void setMoveType(Board.MoveType moveType) {
        this.moveType = moveType;
    }
}