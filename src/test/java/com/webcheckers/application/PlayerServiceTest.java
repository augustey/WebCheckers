package com.webcheckers.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.application.PlayerService;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Testing suite for PlayerService
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("Application-tier")
public class PlayerServiceTest
{
    //Component under Test
    private PlayerService CuT;

    //friendly objects
    //BoardView
    private GameCenter gameCenter;
    private Board board;
    private Player player;
    private Player opponent;
    private Move move;
    private Game game;

    /**
     * Setup objects for each test
     */
    @BeforeEach
    public void testSetup()
    {
        gameCenter = new GameCenter();
        player = new Player("Player");
        opponent = new Player("Opponent");
        gameCenter.requestNewGame(player, opponent);

        //Setup CuT
        CuT = gameCenter.getPlayerService(player);

        game = CuT.getGame();
        board = game.getBoard();
    }

    /**
     * Test creating a new object
     */
    @Test
    public void test_create_service()
    {
        new PlayerService(player, game);
    }

    /**
     * Test the getter for redPlayer
     */
    @Test
    public void test_getRedPlayer()
    {
        Player expected = CuT.getRedPlayer();

        System.out.println(expected);

        assertSame(expected, player);
    }

    /**
     * Test the getter for whitePlayer
     */
    @Test
    public void test_getWhitePlayer()
    {
        Player expected = CuT.getWhitePlayer();

        assertSame(expected, opponent);
    }

    /**
     * Test the getter for Player
     */
    @Test
    public void test_getPlayer()
    {
        Player expected = CuT.getPlayer();

        assertSame(expected, player);
    }

    /**
     * Test getter for turnMoves
     */
    @Test
    public void test_getTurnMoves()
    {
        List<Move> expected = new ArrayList<>();

        List<Move> actual = CuT.getTurnMoves();

        assertEquals(expected, actual);
    }

    /**
     * Test getter for game
     */
    @Test
    public void test_getGame()
    {
        Game expected = CuT.getGame();

        assertSame(expected, game);
    }

    /**
     * Test for getting the boardview
     */
    @Test
    public void test_getBoardView()
    {
        Iterator<Row> bv = board.iterator();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }

    /**
     * Test for getting the boardview when flipped
     */
    @Test
    public void test_getBoardView_flipped()
    {
        CuT = new PlayerService(opponent, game);

        board.flip();
        Iterator<Row> bv = board.iterator();
        board.flip();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }

    /**
     * Test for getting the boardview when activeplayer is not the player
     */
    @Test
    public void test_getBoardView_activeplayer_not_player()
    {
        board.setActivePlayerColor(Piece.Color.WHITE);
        board.flip();
        Iterator<Row> bv = board.iterator();
        board.flip();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }

    /**
     * Test for getting the boardview when activeplayer is not the player
     */
    @Test
    public void test_getBoardView_activeplayer_white_not_player()
    {
        CuT = new PlayerService(opponent, game);
        board.setActivePlayerColor(Piece.Color.WHITE);
        Iterator<Row> bv = board.iterator();
        BoardView expected = new BoardView(bv);

        BoardView actual = CuT.getBoardView();

        assertEquals(expected, actual);
    }

    /**
     * Test for adding a move
     */
    @Test
    public void test_addMove()
    {
        move = new Move(new Position(5,0), new Position(4,1));
        int expectedSize = 1;
        List<Move> moves = CuT.getTurnMoves();

        CuT.addMove(move);


        assertSame(expectedSize, moves.size());
        assertSame(move, moves.get(0));
    }

    /**
     * Test for adding multiple moves
     */
    @Test
    public void test_addMoveMultiple()
    {
        move = new Move(new Position(5,0), new Position(4,1));
        Move move1 = new Move(new Position(5,2), new Position(4,1));
        Move move2 = new Move(new Position(5,2), new Position(4,3));
        int expectedSize = 3;
        List<Move> moves = CuT.getTurnMoves();

        CuT.addMove(move);
        CuT.addMove(move1);
        CuT.addMove(move2);

        assertSame(expectedSize, moves.size());
        assertSame(move, moves.get(0));
        assertSame(move1, moves.get(1));
        assertSame(move2, moves.get(2));
    }

    /**
     * Test for removing a move
     */
    @Test
    public void test_removeMove()
    {
        move = new Move(new Position(5,0), new Position(4,1));
        int expectedSize = 0;
        List<Move> moves = CuT.getTurnMoves();
        CuT.addMove(move);

        Move actual = CuT.removeMove();

        assertSame(expectedSize, moves.size());
        assertSame(move, actual);
    }

    /**
     * Test for removing multiple moves
     */
    @Test
    public void test_removeMoveMultiple()
    {
        move = new Move(new Position(5,0), new Position(4,1));
        Move move1 = new Move(new Position(5,2), new Position(4,1));
        Move move2 = new Move(new Position(5,2), new Position(4,3));
        int expectedSize = 0;
        List<Move> moves = CuT.getTurnMoves();
        CuT.addMove(move);
        CuT.addMove(move1);
        CuT.addMove(move2);

        Move actual = CuT.removeMove();
        Move actual1 = CuT.removeMove();
        Move actual2 = CuT.removeMove();

        assertSame(expectedSize, moves.size());
        assertSame(move2, actual);
        assertSame(move1, actual1);
        assertSame(move, actual2);
    }

    /**
     * Test for removing moves from an empty list
     */
    @Test
    public void test_removeMoveEmpty()
    {
        int expectedSize = 0;
        List<Move> moves = CuT.getTurnMoves();

        Move actual = CuT.removeMove();

        assertSame(expectedSize, moves.size());
    }

    /**
     * Test for clearing all the moves
     */
    @Test
    public void test_clearMoves()
    {
        move = new Move(new Position(5,0), new Position(4,1));
        Move move1 = new Move(new Position(5,2), new Position(4,1));
        Move move2 = new Move(new Position(5,2), new Position(4,3));
        int expectedSize = 0;
        List<Move> moves = CuT.getTurnMoves();
        CuT.addMove(move);
        CuT.addMove(move1);
        CuT.addMove(move2);

        CuT.clearMoves();

        assertSame(expectedSize, moves.size());
    }
}
