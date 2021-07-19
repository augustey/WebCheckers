package com.webcheckers.model;

import java.util.ArrayList;

/**
 * This class is for storing the moves that make up a turn
 */
public class Turn {
    private ArrayList<Move> moves;


    public Turn(){
        this.moves = new ArrayList<Move>();

    }

    /**
     * Adds a move to the turn if it is found to not be jumping over what is already jumped
     * @param move The move under consideration
     */
    public void addMove(Move move) {
        if(move instanceof JumpMove) {//Or get the moveType
            if(moves.contains(move)){
                return;
            }
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

//    @Override
//    public boolean equals(Object other) {
//        if(other.equals())
//    return true;
//    }




}
