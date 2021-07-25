package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * Testing suite for JumpMove
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")
public class JumpMoveTest {

    /**
     * The component-under-test (CuT).
     */
    JumpMove CuT;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setUp() {
        CuT = new JumpMove(new Position(0, 1), new Position(2, 3));
    }

    /**
     * Test that JumpMove is generated with out failure.
     */
    @Test
    public void jumpMoveTest() {
        assertEquals(new Position(1, 2), CuT.getJumpedPosition());
    }

    /**
     * Test that the jumped position getter is working
     */
    @Test
    public void getJumpedPositionTest() {
        assertEquals(new Position(1, 2), CuT.getJumpedPosition());

    }

    /**
     * Test that the toString is working correctly
     */
    @Test
    public void toStringTest() {
        String expected = "JumpMove{start=Position{row=0, cell=1}, jumped=Position{row=1, cell=2}, end=Position{row=2, cell=3}}";
        String actual = CuT.toString();
        assertEquals(expected, actual);
    }

    /**
     * Test the equals method.
     */
    @Test
    public void equalsTest() {
        // Test the same object.
        assertEquals(CuT, CuT);
        // Test equals with a different JumpMove object
        Position start = new Position(6, 1);
        Position end = new Position(4, 3);
        JumpMove jm1 = new JumpMove(start, end);
        assertNotEquals(CuT, jm1);
        // Test with the same jumped position
        JumpMove jm2 = new JumpMove(new Position(0, 3), new Position(2, 1));
        System.out.println(jm2.getJumpedPosition());
        assertEquals(CuT, jm2);
        // Test equals with a different object
        GameCenter gameCenter = new GameCenter();
        assertNotEquals(CuT, gameCenter);
    }
}