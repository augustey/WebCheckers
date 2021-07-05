package com.webcheckers.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.application.PlayerService;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

@Tag("Application-tier")
public class PlayerServiceTest
{
    //Component under Test
    private PlayerService CuT;

    //friendly objects
    //Board
    //BoardView
    private Game game;
    private Player redPlayer;
    private Player whitePlayer;

    @BeforeEach
    public void testSetup()
    {
        redPlayer = new Player("red");
        whitePlayer = new Player("white");
        game = new Game(redPlayer, whitePlayer);

        //Setup CuT
        CuT = new PlayerService(redPlayer, game);
    }

    @Test
    public void test_create_service()
    {
        new PlayerService(redPlayer, game);
    }

    @Test
    public void test_getRedPlayer()
    {
        Player expected = CuT.getRedPlayer();

        assertSame(expected, redPlayer);
    }

    @Test
    public void test_getWhitePlayer()
    {
        Player expected = CuT.getWhitePlayer();

        assertSame(expected, whitePlayer);
    }

    @Test
    public void test_getPlayer()
    {
        Player expected = CuT.getPlayer();

        assertSame(expected, redPlayer);
    }

    @Test
    public void test_getBoardView()
    {
        Board board = game.getBoard();
        Iterator<Row> bv = board.iterator();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }

    @Test
    public void test_getBoardViewFlipped()
    {
        CuT = new PlayerService(whitePlayer, game);

        Board board = game.getBoard();
        board.flip();
        Iterator<Row> bv = board.iterator();
        board.flip();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }


}
