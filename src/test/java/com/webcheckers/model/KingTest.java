package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Testing suite for King
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")
public class KingTest {
    private King king;



    /**
     * Test that red king is generated with out failure.
     */
    @Test
    public void ctor_redPiece(){
        new King(Piece.Color.RED);
    }

    /**
     * Test that white king is generated with out failure.
     */
    @Test
    public void ctor_whitePiece(){
        new King(Piece.Color.WHITE);
    }

    /**
     *Will check if the king moves are generated correctly
     */
    @Test
    public void ctor_allSingleMoves(){

        final King piece = new King(Piece.Color.RED);

        ArrayList<SingleMove> moves = new ArrayList<>();

        moves.addAll(piece.allSingleMoves(0, 0));

        assertEquals(4, moves.size());//that the correct number of possible moves was generated

        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(-1, -1))));
        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(-1, 1))));
        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(1, -1))));
        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(1, 1))));

    }

    /**
     *will check if the jumps are generated correctly
     */
    @Test
    public void ctor_allJumps(){
        final King piece = new King(Piece.Color.RED);

        ArrayList<JumpMove> moves = new ArrayList<>();

        moves.addAll(piece.allJumps(0, 0));

        assertEquals(4, moves.size());//that the correct number of possible moves was generated

        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(-2, -2))));
        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(-2, 2))));
        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(2, -2))));
        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(2, 2))));
    }

}

