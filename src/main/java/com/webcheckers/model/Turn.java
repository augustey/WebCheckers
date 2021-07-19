package com.webcheckers.model;

import java.util.ArrayList;

public class Turn {
    private ArrayList<Move> moves;


    public Turn(){
        this.moves = new ArrayList<Move>();

    }

    public void addMove(Move move) {
        if(move instanceof JumpMove) {//Or get the moveType
            if(moves.contains(move)){
                return;
            }
        }
        moves.add(move);
    }

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
