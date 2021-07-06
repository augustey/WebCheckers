package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

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

        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(piece.allSingleMoves(2, 1));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated

    }

    /**
     *will check if the jumps are generated correctly
     */
    @Test
    public void ctor_allJumps(){
        final SinglePiece piece = new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED);

        ArrayList<JumpMove> moves = new ArrayList<>();

//        moves.addAll(piece.allJumps(2, 1));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated
    }

}
