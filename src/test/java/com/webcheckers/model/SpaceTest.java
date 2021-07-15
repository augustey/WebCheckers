package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameCenter;
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
        // Mock object for Piece.
        piece = mock(Piece.class);

        // Creating the CuT.
        CuT = new Space(ROW_VALUE, COL_VALUE, null, IS_VALID);
    }

    /**
     * Test that the Space constructor works without failing.
     */
    @Test
    public void arg_test() {
        new Space(ROW_VALUE, COL_VALUE, piece, IS_VALID);
    }

    /**
     * Test the colIdx getter in Space.
     */
    @Test
    public void get_col_test() {
        assertEquals(COL_VALUE, CuT.getColIdx());
    }

    /**
     * Test the rowIdx getter in Space.
     */
    @Test
    public void get_row_test() {
        assertEquals(ROW_VALUE, CuT.getRowIdx());
    }

    /**
     * Test the piece getter in Space where the piece is null.
     */
    @Test
    public void get_piece_test() {
        assertNull(CuT.getPiece());
        // Setting the piece to null to test that the piece is actually placed.
        CuT.setPiece(piece);
        assertNotNull(CuT.getPiece());
    }

    /**
     * Test the validity getter in Space where the piece is null.
     */
    @Test
    public void get_isvalid_test() {
        assertNull(CuT.getPiece());
        // Setting the piece to null to test that the piece is actually placed.
        CuT.setPiece(piece);
        assertNotNull(CuT.getPiece());
    }

    /**
     * Test the piece setter in Space.
     */
    @Test
    public void set_piece_test() {
        CuT.setPiece(piece);
        // Testing that piece set is the same piece created.
        assertEquals(piece, CuT.getPiece());
    }

    /**
     * Test the Space's validity for placing a piece.
     */
    @Test
    public void space_validity_test() {
        // Testing that space is empty and a black/true space.
        assertTrue(CuT.isValid());
        // Testing that space is now filled and no longer valid.
        CuT.setPiece(piece);
        assertFalse(CuT.isValid());
    }

    /**
     * Test the Space equals method.
     */
    @Test
    public void space_equals_test() {
        assertTrue(CuT.equals(CuT));
        // Test for differing pieces
        Space other = new Space(ROW_VALUE, COL_VALUE, piece, IS_VALID);
        assertFalse(CuT.equals(other));
        // Test for differing validity
        other = new Space(ROW_VALUE, COL_VALUE, null, !IS_VALID);
        assertFalse(CuT.equals(other));
        // Test for differing row values
        other = new Space(1, COL_VALUE, piece, IS_VALID);
        assertFalse(CuT.equals(other));
        // Test for differing col values
        other = new Space(ROW_VALUE, 2, piece, IS_VALID);
        assertFalse(CuT.equals(other));
        // Test if the other object is not a space.
        GameCenter gameCenter = new GameCenter();
        assertFalse(CuT.equals(gameCenter));
    }
}