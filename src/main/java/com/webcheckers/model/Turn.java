package com.webcheckers.model;

import java.util.ArrayList;

/**
 * This class is for storing the moves that make up a turn
 */
public class Turn {
    private ArrayList<Move> moves;
    private final Board.MoveType moveType;


    public Turn(Board.MoveType moveType){
        //TODO get move type
        this.moveType = moveType;
        this.moves = new ArrayList<Move>();

    }

    /**
     * Adds a move to the turn if it is found to not be jumping over what is already jumped
     * @param move The move under consideration
     */
    public void addMove(Move move) {
        if(moveType == Board.MoveType.Jump) {
            //TODO create new jumpMove
            if(moves.contains(move)){
                return;
            }
        }
        else{
            //TODO create new Single Move
        }
        moves.add(move);
    }

    /**
     * Removes the last move in the turn
     */
    public void removeMove() {
        int i = moves.size() - 1;
        moves.remove(i);

    }

}
