package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.webcheckers.application.GameWin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

/**
 * The unit test suite for the {@link Turn} component.
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */

@Tag("Model-tier")
public class TurnTest {

    private Board CuTGeneralBoard;
    private Board CuTJump;

    private GameWin gameWin;

    @BeforeEach
    public void setUp() {
        //this sets up the normal starting board
        gameWin = mock(GameWin.class);
        CuTGeneralBoard = new Board(gameWin);
        CuTJump = new Board(gameWin);
        generateJumpableBoard();
    }

    /**
     * Changes a new board to one that jumps are possible on first move
     */
    private void generateJumpableBoard() {
        for (int row = 0; row < CuTJump.BOARD_DIM; row++) {
            for (int col = 0; col < CuTJump.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = CuTJump.getSpace(new Position(row, col), CuTJump);
                    if (row == 4) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if (row < 3) {
                        space.setPiece(null);
                    }
                }
            }
        }
    }

    /**
     *
     */
    @Test
    private void addSingleMoveTest(){
        Turn turn = new Turn(CuTGeneralBoard);

    }

    /**
     *
     */
    @Test
    private void addJumpMoveTest(){
        Turn turn = new Turn(CuTGeneralBoard);

    }

    /**
     *
     */
    @Test
    private void removeMoveTest() {

    }

    /**
     *
     */
    @Test
    private void getMovesTest() {

    }
}
