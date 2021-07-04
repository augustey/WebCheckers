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
    private static final int ROW_VALUE = 3;
    private static final int COL_VALUE = 5;
    private Piece piece;
    private static final boolean IS_VALID = false;

    private Space CuT;

    @BeforeEach
    public void testSetup() {
        piece = mock(Piece.class);
        when(piece.getColor()).thenReturn(Piece.Color.RED);
        when(piece.getType()).thenReturn(Piece.Type.SINGLE);

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
}
