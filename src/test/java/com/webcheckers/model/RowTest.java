package com.webcheckers.model;


import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link Row} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")

public class RowTest {

    // Constants for testing.
    private static final int ROW_INDEX = 4;
    private static final boolean IS_VALID = true;

    // Friendly Objects
    private ArrayList<Space> spaces;

    /**
     * The component-under-test (CuT).
     */
    private Row CuT;

    /**
     * Setup for the unit tests.
     */
    @BeforeEach
    public void testSetup() {
        spaces = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            Space space = new Space(ROW_INDEX, i, null, IS_VALID);
            spaces.add(space);
        }

        // Creating the CuT.
        CuT = new Row(ROW_INDEX, spaces);
    }

    /**
     * Test that the Row constructor works without failing.
     */
    @Test
    public void arg_test() {
        ArrayList<Space> spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            Space space = new Space(1, i, null, IS_VALID);
            spaceList.add(space);
        }
        new Row(1, spaceList);
    }

    /**
     * Test the row index getter in Row.
     */
    @Test
    public void get_row_index_test() {
        assertEquals(ROW_INDEX, CuT.getIndex());
    }

    /**
     * Test the spaces list getter in Row.
     */
    @Test
    public void get_space_list_test() {
        assertEquals(spaces, CuT.getSpaces());
    }

    /**
     * Test the spaces setter in Row.
     */
    @Test
    public void set_space_list_test() {
        ArrayList<Space> spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            Space space = new Space(1, i, null, IS_VALID);
            spaceList.add(space);
        }
        // Setting the row's spaces to spaceList
        CuT.setSpaces(spaceList);
        assertEquals(spaceList, CuT.getSpaces());
    }

    /**
     * Test the Row equals method.
     */
    @Test
    public void row_equals_test() {
        assertTrue(CuT.equals(CuT));
        // Test equals with a different row.
        ArrayList<Space> spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            Space space = new Space(1, i, null, IS_VALID);
            spaceList.add(space);
        }
        assertFalse(CuT.equals(new Row(1, spaceList)));
        // Test with a differing object
        GameCenter gameCenter = new GameCenter();
        assertFalse(CuT.equals(gameCenter));
    }

    /**
     * Test the Row hashcode method.
     */
    @Test
    public void row_hashcode_test() {
        // Testing equals through hashcode.
        assertEquals(CuT.hashCode(), CuT.hashCode());
        // Testing not equals through hashcode.
        ArrayList<Space> spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            Space space = new Space(1, i, null, IS_VALID);
            spaceList.add(space);
        }
        Row other = new Row(1, spaceList);
        assertNotEquals(CuT.hashCode(), other.hashCode());
    }
}