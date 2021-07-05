package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Space;

/**
 * The unit test suite for the Space component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")

public class SpaceTest {
    private static final int ROW_VALUE = 2;
    private static final int COL_VALUE = 5;
    private Piece piece;
    private static final boolean IS_VALID = true;
    private Space CuT;

    @BeforeEach
    public void testSetup() {
        piece = mock(Piece.class);
        CuT = new Space(ROW_VALUE, COL_VALUE, null, IS_VALID);
    }

    /**
     * Test that the Space constructor works without failing.
     */
    @Test
    public void ctor_arg_test() {
        new Space(ROW_VALUE, COL_VALUE, piece, IS_VALID);
    }

    /**
     * Test the row getter in Space
     */
    @Test
    public void ctor_get_row_test() {
        assertEquals(ROW_VALUE, CuT.getRowIdx());
    }

    /**
     * Test the col getter in Space
     */
    @Test
    public void ctor_get_col_test() {
        assertEquals(COL_VALUE, CuT.getColIdx());
    }

    /**
     * Test the piece getter in Space where the piece is null.
     */
    @Test
    public void ctor_get_piece_test() {
        assertNull(CuT.getPiece());
    }

    /**
     * Test the piece setter in Space
     */
    @Test
    public void ctor_set_piece_test() {
        CuT.setPiece(piece);
        assertEquals(piece, CuT.getPiece());
    }

    /**
     * Test the Space's validity.
     */
    @Test
    public void ctor_space_validity_test() {
        assertTrue(CuT.isValid());
        CuT.setPiece(piece);
        assertFalse(CuT.isValid());
    }

    /**
     * Test the Space if it has a piece.
     */
    @Test
    public void ctor_space_has_piece_test() {
        assertFalse(CuT.hasPiece());
        CuT.setPiece(piece);
        assertTrue(CuT.hasPiece());
    }
}
