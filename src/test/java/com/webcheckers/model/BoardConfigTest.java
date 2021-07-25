package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Testing suite for BoardConfig
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */

@Tag("Model-tier")
public class BoardConfigTest {
    private BoardConfig CuT;
    Player opponent;
    GameWin gameWin;
    Game refGame;
    GameCenter gameCenter = new GameCenter();
    Board board;
    Set<String> backdoors;

    @BeforeEach
    public void setup() {
        CuT = mock(BoardConfig.class);
        opponent = new Player("Opponent");
        refGame = new Game(new Player("!"), new Player("?"), gameCenter);
        gameWin = new GameWin(new GameCenter(), refGame);
        board = new Board(gameWin);
        backdoors = new HashSet<>();
        backdoors.add(BoardConfig.KING);
        backdoors.add(BoardConfig.SINGLE_JUMP);
        backdoors.add(BoardConfig.NO_PIECES);
        backdoors.add(BoardConfig.CHAIN_JUMP);
        backdoors.add(BoardConfig.BLOCKED);
    }

    /**
     * Test different backdoor setups
     */
    @Test
    public void test_generate_backdoors() {
        for(String backdoor: backdoors) {
            Player player = new Player(backdoor);
            Board newBoard = (new Game(player, opponent, gameCenter)).getBoard();

            assertTrue(boardsAreEqual(CuT.newBoard(gameWin, backdoor), newBoard));
        }
    }

    /**
     * Check if two boards are equal
     * @param board1 first board
     * @param board2 other board
     * @return Whether they are equal
     */
    public boolean boardsAreEqual(Board board1, Board board2) {
        return board1.toString().equals(board2.toString());
    }
}
