package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing suite for BoardView
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
@Tag("Model-tier")
public class BoardViewTest {
    private BoardView CuT;
    private Board board;
    private GameWin gameWin;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void testSetup() {
        gameWin= new GameWin(new GameCenter(), new Game(new Player("1"), new Player("2"), new GameCenter()));
        board = new Board(gameWin);
    }

    /**
     * Test if boardView equals method works
     */
    @Test
    public void test_board_equals() {
        CuT = new BoardView(board.iterator());
        Board otherBoard = new Board(gameWin);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(new SingleMove(new Position(5, 0), new Position(4, 1)));
        otherBoard.makeMove(moves);
        otherBoard.flip();
        BoardView other1 = new BoardView(board.iterator());
        BoardView other2 = new BoardView(otherBoard.iterator());

        assertEquals(CuT, other1);
        assertEquals(CuT, CuT);
        assertNotEquals(CuT, other2);
        assertNotEquals(CuT, gameWin);
        assertEquals(CuT.hashCode(), CuT.hashCode());
    }
}