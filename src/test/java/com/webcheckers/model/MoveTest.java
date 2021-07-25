package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * The unit test suite for the {@link Move} component.
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")

public class MoveTest {

    /**
     * The component-under-test (CuT).
     */
    Move CuT;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setUp() {
        CuT = new Move(new Position(0, 0), new Position(-2, -2));
    }

    /**
     * Test the getStart
     */
    @Test
    public void ctor_getStart() {
        assertEquals(new Position(0, 0), CuT.getStart());
    }

    /**
     * Test getEnd
     */
    @Test
    public void ctor_getEnd() {
        assertEquals(new Position(-2, -2), CuT.getEnd());
    }

    /**
     * Tests the Move equals method.
     */
    @Test
    public void ctor_equals() {
        assertEquals(CuT, CuT);
        // Test that a jumpMove can be found equal to a move object
        JumpMove jm1 = new JumpMove(new Position(0, 0), new Position(-2, -2));
        assertEquals(CuT, jm1);
        // Test that a jumpMove can be found unequal to a move object or other object
        JumpMove jm2 = new JumpMove(new Position(0, 1), new Position(-2, -2));
        assertNotEquals(CuT, jm2);
        // Test equals with a different object
        GameCenter gameCenter = new GameCenter();
        assertNotEquals(CuT, gameCenter);
    }

    /**
     * Test that the hashCode is generated correctly
     */
    @Test
    public void ctor_hashCode() {
        Move move1 = new Move(new Position(0, 0), new Position(2, -2));
        assertEquals(CuT.hashCode(), CuT.hashCode());
        assertNotEquals(CuT.hashCode(), move1.hashCode());
    }

    /**
     * Test that toString is generated correctly
     */
    @Test
    public void ctor_toString() {
        String expected = "Move{start=Position{row=0, cell=0}, end=Position{row=-2, cell=-2}}";

        String actual = CuT.toString();

        assertEquals(expected, actual);
    }
}