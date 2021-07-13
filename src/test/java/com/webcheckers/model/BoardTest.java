package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameWin;
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
public class BoardTest {

    private Board board;

    private GameWin CuT;

    @BeforeEach
    public void setUp() {
        //this sets up the normal starting board
        board = new Board(CuT);


    }

    /**
     * Check if the move type can be correctly evaluated to singleMove
     */
    @Test
    public void ctor_determineMoveTypeSingle() {
        board.determineMoveType();
        assertEquals(Board.MoveType.Single, board.getMoveType());
    }

    /**
     * Check that when determining posible moves when taking singleMoves it goes succesfully
     */
    @Test
    public void ctor_getPossibleMovesSingleMove() {
        assertEquals(7, board.getPossibleMoves().size());
    }
    //TODO get board to a point where it's move type is Jump

    /**
     * Check if the move type can be correctly evaluated to jump
     */
    @Test
    public void ctor_determineMoveTypeJump() {
        board.determineMoveType();
        assertEquals(Board.MoveType.Jump, board.getMoveType());
    }
    /**
     * Check that when determining posible moves when taking jump it goes succesfully
     */
    @Test
    public void ctor_getPossibleMovesJumpMove() {

        assertEquals(0, board.getPossibleMoves().size());
    }
    //TODO get board to a point where it's move type is Blocked
    /**
     * Check if the move type can be correctly evaluated to blocked
     */
    @Test
    public void ctor_determineMoveTypeBlocked() {
        board.determineMoveType();
        assertEquals(Board.MoveType.Blocked, board.getMoveType());
    }
    /**
     * Check that when determining possible moves when taking blocked it goes sucesfully
     * this is the win condition so it should be zero
     */
    @Test
    public void ctor_getPossibleMovesBlocked() {

        assertEquals(0, board.getPossibleMoves().size());
    }

    /**
     * Checks if the flip method is working properly
     */
    @Test
    public void ctor_flipAndCopy() {
        Board copy = new Board(board);
        copy.flip();
        copy.flip();
        assertEquals(board.toString(), copy.toString());
    }

    /**
     * succesfully exicutes a single move
     */
    @Test
    public void ctor_makeMoveSingleMove(){
        //Single move
        Board startBoard = new Board(board);
        Position end = new Position(4, 1);
        Move singleMove = new Move(new Position(5, 0), end);
        ArrayList<Move> singleMoves = new ArrayList<>();
        singleMoves.add(singleMove);
        board.makeMove(singleMoves);
        System.out.println(board.toString());
        assertNotEquals(startBoard.toString(), board.toString());
        //TODO show the it was exicuted


    }

    /**
     * checks if a jumpMove with no chain jump is exicuted succesfully
     */
    @Test
    public void ctor_makeMoveJump(){
        //JumpMove
        //TODO need to create a board that has a singleJump avalible
        Move jumpMove = new Move(new Position(5, 0), new Position(3, 2));
        ArrayList<Move> jumpMoves = new ArrayList<>();
        jumpMoves.add(jumpMove);
        board.makeMove(jumpMoves);
        //TODO show the it was exicuted
    }

    /**
     * Checks if you try to jump with another jump avalible it will reject the move
     * and if you then all further jumps it will then succesfully exicute turn
     */
    @Test
    public void ctor_makeMoveChainJump(){
        //TODO need to create a board that a chain jump is avalible
        Move jumpMove = new Move(new Position(5, 0), new Position(3, 2));
        ArrayList<Move> jumpMoves = new ArrayList<>();
        jumpMoves.add(jumpMove);
        board.makeMove(jumpMoves);//should fail
        //TODO show that it failed
        Move jumpMove1 = new Move(new Position(3, 2), new Position(1, 4));
        jumpMoves.add(jumpMove1);
        board.makeMove(jumpMoves);
        //TODO show that it was exicuted
    }

    /**
     * Checks that if the player is determined to blocked no move is made and game is over.
     */
    @Test
    public void ctorMakeMoveBlocked(){
        //TODO need to create a board that is blocked
        Move move = new Move(new Position(5, 0), new Position(3, 2));
        ArrayList<Move> moves = new ArrayList<>();
        board.makeMove(moves);//caught by first if statement
        //TODO show that it did not exicute
    }

    /**
     * Checks that the getActiveColor works
     */
    @Test
    public void ctor_getActiveColor() {
        assertEquals(Piece.Color.RED , board.getActivePlayerColor());
    }

    /**
     * Checks that setting the active color works
     */
    @Test
    public void ctor_setActiveColor() {
        board.setActivePlayerColor(Piece.Color.WHITE);
        assertEquals(Piece.Color.WHITE , board.getActivePlayerColor());

    }

    /**
     * Checks that geting the board works
     */
    @Test
    public void ctor_getBoard() {
        board.getBoard();
    }
}
