package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Position;
import com.webcheckers.model.SinglePiece;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@Tag("Model-tier")

public class SinglePieceTest {
    /**
     * Test that red singlePiece is generated with out failure.
     */
    @Test
    public void ctor_redPiece(){
        new SinglePiece(Piece.Color.RED);
    }

    /**
     * Test that white singlePiece is generated with out failure.
     */
    @Test
    public void ctor_whitePiece(){
        new SinglePiece(Piece.Color.WHITE);
    }

    /**
     *
     */
    @Test
    public void ctor_allSingleMoves(){
        final SinglePiece piece = new SinglePiece(Piece.Color.RED);
        ArrayList<Move> moves = new ArrayList<>();
        Position start = new Position(2, 1);
        Position end_left = new Position(1, 0);
        Position end_right = new Position(1, 2);

        moves.addAll(piece.allSingleMoves(2, 1));

        assertEquals(2, moves.size());//that the correct number of possible moves was generated


//        assertTrue(moves.containsAll(new Move(start, end_left)));//checks that the generated moves is same as the expected moves



    }

    /**This method has not been written yet in the code
     *
     */
    @Test
    public void ctor_allJumps(){

    }

}
