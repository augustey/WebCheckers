package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Testing suite for JumpMove
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")
public class JumpMoveTest {
    JumpMove jumpMove;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setUp() {
        jumpMove = new JumpMove(new Position(0, 0), new Position(-2, -2));
    }

    /**
     * Test that JumpMove is generated with out failure.
     */
    @Test
    public void ctor_jumpMove() {
        assertEquals(new Position(-1 , -1), jumpMove.getJumpedPosition());
    }

    /**
     * Test that the jumped position getter is working
     */
    @Test
    public void ctor_getJumpedPosition() {
        assertEquals(new Position(-1 , -1), jumpMove.getJumpedPosition());

    }
}
