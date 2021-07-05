package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

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
     *
     */
    @Test
    public void ctor_allSingleMoves(){

        final SinglePiece piece = new SinglePiece(Piece.Type.SINGLE, Piece.Color.RED);

        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(piece.allSingleMoves(2, 1));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated

    }

    /**This method has not been written yet in the code
     *
     */
    @Test
    public void ctor_allJumps(){
    //TODO: fill out this test
    }

}
