package com.webcheckers.model;

import java.util.ArrayList;

/**
 * This class is for storing the moves that make up a turn
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
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
    public boolean addMove(Move move) {
        //System.out.println("Moves: " + moves);
        System.out.println(moveType);
        //If a JumpMove is required then the move is converted to a JumpMove
        if(moveType == Board.MoveType.Jump) {
            JumpMove jumpMove = new JumpMove(move);
            //This uses the custom equals method in JumpMove to make sure that no piece is jumped twice

            if(!moves.contains(move)) {
                //System.out.println("not contained");
                moves.add(jumpMove);
//                System.out.println(moves);
//                System.out.println("Size: " + moves.size());


                return true;
            }

        }
        //If a SingleMove is required then the move is converted to a SingleMove
        else if(moveType == Board.MoveType.Single){
            //TODO create new Single Move
            SingleMove singleMove = new SingleMove(move);
            moves.add(singleMove);
            return true;
        }
        return false;
    }

    /**
     * Removes the last move in the turn
     */
        public void removeMove() {
            int i = moves.size() - 1;
            moves.remove(i);
        }

    /**
     * Getter method for collection of moves that make up the turn
     * @return
     */
    public ArrayList<Move> getMoves() {
        return moves;
    }

}



