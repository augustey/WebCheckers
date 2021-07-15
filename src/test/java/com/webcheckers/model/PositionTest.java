package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link Position} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")

public class PositionTest {

    // Constants for testing.
    private static final int ROW_VALUE = 2;
    private static final int COL_VALUE = 5;

    /**
     * The component-under-test (CuT).
     */
    private Position CuT;

    /**
     * Setup the new mock object for the unit tests.
     */
    @BeforeEach
    public void testSetup() {
        // Creating the CuT.
        CuT = new Position(ROW_VALUE, COL_VALUE);
    }

    /**
     * Test that the Position constructor works without failing.
     */
    @Test
    public void arg_test() {
        new Position(1, 2);
    }

    /**
     * Test the cell getter in Position.
     */
    @Test
    public void get_cell_test() {
        assertEquals(COL_VALUE, CuT.getCell());
    }

    /**
     * Test the row getter in Position.
     */
    @Test
    public void get_row_test() {
        assertEquals(ROW_VALUE, CuT.getRow());
    }

    /**
     * Test the Position equals method.
     */
    @Test
    public void position_equals_test() {
        assertTrue(CuT.equals(CuT));
        // Test equals with a different position object
        Position position = new Position(1, 2);
        assertFalse(CuT.equals(position));
        // Test equals with a different object
        GameCenter gameCenter = new GameCenter();
        assertFalse(CuT.equals(gameCenter));
    }

    /**
     * Test the Position toString method
     */
    @Test
    public void to_string_test() {
        String expected = "Position{row=2, cell=5}";

        String actual = CuT.toString();

        assertEquals(expected, actual);
    }
}
