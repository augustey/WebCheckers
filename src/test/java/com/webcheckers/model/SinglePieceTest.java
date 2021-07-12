package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Testing suite for SinglePiece
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")

public class SinglePieceTest {
    private SinglePiece singlePiece;



    /**
     * Test that red singlePiece is generated with out failure.
     */
    @Test
    public void ctor_redPiece(){
        new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED);
    }

    /**
     * Test that white singlePiece is generated with out failure.
     */
    @Test
    public void ctor_whitePiece(){
        new SinglePiece(Piece.Type.SINGLE, Piece.Color.WHITE);
    }

    /**
     *Will check if the single moves are generated correctly
     */
    @Test
    public void ctor_allSingleMoves(){

        final SinglePiece piece = new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED);

        ArrayList<SingleMove> moves = new ArrayList<>();

        moves.addAll(piece.allSingleMoves(0, 0));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated

        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(-1, -1))));
        assertTrue(moves.contains(new SingleMove(new Position(0, 0) , new Position(-1, 1))));

    }

    /**
     *will check if the jumps are generated correctly
     */
    @Test
    public void ctor_allJumps(){
        final SinglePiece piece = new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED);

        ArrayList<JumpMove> moves = new ArrayList<>();

        moves.addAll(piece.allJumps(0, 0));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated

        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(-2, -2))));
        assertTrue(moves.contains(new JumpMove(new Position(0, 0) , new Position(-2, 2))));
    }

}
