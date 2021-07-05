package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * The unit test suite for the {@link Space} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")

public class SpaceTest {

    // Constants for testing.
    private static final int ROW_VALUE = 2;
    private static final int COL_VALUE = 5;
    private static final boolean IS_VALID = true;

    // Mock objects.
    private Piece piece;

    /**
     * The component-under-test (CuT).
     */
    private Space CuT;

    /**
     * Setup the new mock object for the unit tests.
     */
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
     * Test the rowIdx getter in Space.
     */
    @Test
    public void ctor_get_row_test() {
        assertEquals(ROW_VALUE, CuT.getRowIdx());
    }

    /**
     * Test the colIdx getter in Space.
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
        CuT.setPiece(piece);
        assertNotNull(CuT.getPiece());
    }

    /**
     * Test the piece setter in Space.
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
