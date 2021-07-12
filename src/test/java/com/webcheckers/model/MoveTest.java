package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import java.util.Objects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Testing suite for JumpMove
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")
public class MoveTest {
    Move move;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setUp() {
        move = new Move(new Position(0, 0), new Position(-2, -2));
    }

    /**
     * Test the getStart
     */
    @Test
    public void ctor_getStart() {
        assertEquals(new Position(0 , 0), move.getStart());
    }

    /**
     * Test getEnd
     */
    @Test
    public void ctor_getEnd() {
        assertEquals(new Position(-2 , -2), move.getEnd());
    }

    /**
     * Test that a jumpMove can be found equal to a move object
     */
    @Test
    public void ctor_equals() {
        JumpMove jumpMove = new JumpMove(new Position(0, 0), new Position(-2, -2));
        assertTrue(move.equals(jumpMove));
    }

    /**
     * Test that the hashCode is generated correctly
     */
    @Test
    public void ctor_hashCode() {
        Move move1 = new Move(new Position(0, 0), new Position(2, -2));
        assertEquals(move.hashCode(), move.hashCode());
        assertNotEquals(move.hashCode(), move1.hashCode());

    }
}
