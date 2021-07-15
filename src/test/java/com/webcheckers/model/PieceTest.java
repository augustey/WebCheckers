package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Testing suite for Piece
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")

public class PieceTest {
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
     * Test that shows that the color can be gotten
     */
    @Test
    public void ctor_getColor() {
        King piece = new King(Piece.Color.RED);

        assertEquals(Piece.Color.RED, piece.getColor());
    }

    /**
     * Test that shows that the Type can be gotten correctly
     */
    @Test
    public void ctor_getType() {
        King piece = new King(Piece.Color.RED);

        assertEquals(Piece.Type.KING, piece.getType());
    }
}
