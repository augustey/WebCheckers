package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    /**
     * Test that shows a peice can be compared to another
     */
    @Test
    public void ctor_equals() {
        King piece = new King(Piece.Color.RED);
        King piece1 = new King(Piece.Color.RED);
        King piece2 = new King(Piece.Color.WHITE);

        assertEquals(piece, piece1);
        assertEquals(piece, piece);
        assertNotEquals(piece, piece2);
        assertNotEquals(piece, new Player("fail"));
    }

    /**
     * Test that shows a piece can generate a hashcode
     */
    @Test
    public void ctor_hash() {
        King piece = new King(Piece.Color.RED);
        int expected = Objects.hash(piece.getColor(), piece.getType());

        int actual = piece.hashCode();

        assertEquals(expected, actual);
    }

    /**
     * Test that the toSting is working correctly
     */
    @Test
    public void toStingTest() {
        King piece = new King(Piece.Color.RED);
        assertEquals("Piece{type=KING, color=RED}", piece.toString());
    }

}
