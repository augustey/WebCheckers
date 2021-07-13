package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameWin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private Board jump;
    private Board chainJump;
    private Board blocked;
    private Board kinging;

    private GameWin gameWin;

    @BeforeEach
    public void setUp() {
        //this sets up the normal starting board
        gameWin = mock(GameWin.class);
        board = new Board(gameWin);
        jump = new Board(gameWin);
        generateJumpableBoard();
        chainJump = new Board(gameWin);
        generateChainJumpableBoard();
        blocked = new Board(gameWin);
        generateBlockedBoard();
        kinging = new Board(gameWin);
        generateKingingBoard();
        System.out.println(chainJump.toString());

    }

    /**
     * Changes a new board to one that is can be kinged on first move
     */
    private void generateKingingBoard() {
        for (int row = 0; row < kinging.BOARD_DIM; row++) {
            for (int col = 0; col < kinging.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = kinging.getSpace(new Position(row, col), kinging.getBoard());
                    if(row > 0) {
                        space.setPiece(new SinglePiece(Piece.Color.RED));
                    }


                }
            }
        }
    }

    /**
     * Changes a new board to one that jumps are possible on first move
     */
    private void generateJumpableBoard() {
        for (int row = 0; row < jump.BOARD_DIM; row++) {
            for (int col = 0; col < jump.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = jump.getSpace(new Position(row, col), jump.getBoard());
                    if (row == 4) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    } else if(row < 3) {
                        space.setPiece(null);
                    }
                }
            }
        }
    }

    /**
     * Changes a new board to one that chain jumps are possible on first move
     */
    private void generateChainJumpableBoard() {
        for (int row = 0; row < chainJump.BOARD_DIM; row++) {
            for (int col = 0; col < chainJump.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = chainJump.getSpace(new Position(row, col), chainJump.getBoard());
                    if (row == 4) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if(row == 2) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if(row < 3) {
                        space.setPiece(null);
                    }
                }
            }
        }
    }

    /**
     * Changes a new board to one that is blocked on first move
     */
    private void generateBlockedBoard() {
        for (int row = 0; row < blocked.BOARD_DIM; row++) {
            for (int col = 0; col < blocked.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = blocked.getSpace(new Position(row, col), blocked.getBoard());
                    if(row < 5) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }


                }
            }
        }
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
        assertEquals(8, board.getPossibleMoves().size());
    }
    //TODO get board to a point where it's move type is Jump

    /**
     * Check if the move type can be correctly evaluated to jump
     */
    @Test
    public void ctor_determineMoveTypeJump() {
        jump.determineMoveType();
        assertEquals(Board.MoveType.Jump, jump.getMoveType());
    }
    /**
     * Check that when determining posible moves when taking jump it goes succesfully
     */
    @Test
    public void ctor_getPossibleMovesJumpMove() {

        assertEquals(8, jump.getPossibleMoves().size());
    }
    //TODO get board to a point where it's move type is Blocked
    /**
     * Check if the move type can be correctly evaluated to blocked
     */
    @Test
    public void ctor_determineMoveTypeBlocked() {
        blocked.determineMoveType();
        assertEquals(Board.MoveType.Blocked, blocked.getMoveType());
    }
    /**
     * Check that when determining possible moves when taking blocked it goes sucesfully
     * this is the win condition so it should be zero
     */
    @Test
    public void ctor_getPossibleMovesBlocked() {

        assertEquals(0, blocked.getPossibleMoves().size());
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
//        assertNotEquals(startBoard.toString(), board.toString());
        board.flip();
        assertSame(board.getSpace(end, board.getBoard()).getPiece().getColor(), Piece.Color.RED);


    }

    /**
     * checks if a jumpMove with no chain jump is exicuted succesfully
     */
    @Test
    public void ctor_makeMoveJump(){
        //JumpMove
        //TODO need to create a board that has a singleJump avalible
        Position end = new Position(3, 2);
        Move jumpMove = new Move(new Position(5, 0),end);
        ArrayList<Move> jumpMoves = new ArrayList<>();
        jumpMoves.add(jumpMove);
        jump.makeMove(jumpMoves);

        System.out.println(jump);
        jump.flip();
        assertSame(jump.getSpace(end, jump.getBoard()).getPiece().getColor(), Piece.Color.RED);
        //TODO show the it was exicuted
    }

    /**
     * Checks if you try to jump with another jump avalible it will reject the move
     * and if you then all further jumps it will then succesfully exicute turn
     */
    @Test
    public void ctor_makeMoveChainJump(){
        //TODO need to create a board that a chain jump is avalible
        Position end = new Position(3, 2);
        Move jumpMove = new Move(new Position(5, 0), end);
        ArrayList<Move> jumpMoves = new ArrayList<>();
        jumpMoves.add(jumpMove);
        chainJump.makeMove(jumpMoves);//should fail
        assertNull(chainJump.getSpace(end, chainJump.getBoard()).getPiece());//another jump is avalible
        end = new Position(1, 4);
        //TODO show that it failed
        Move jumpMove1 = new Move(new Position(3, 2), end);
        jumpMoves.add(jumpMove1);
        chainJump.makeMove(jumpMoves);
        chainJump.flip();
        assertSame(chainJump.getSpace(end, chainJump.getBoard()).getPiece().getColor(), Piece.Color.RED);

        //TODO show that it was exicuted
    }

    /**
     * Checks that if the player is determined to blocked no move is made and game is over.
     */
    @Test
    public void ctor_MakeMoveBlocked(){
        //TODO need to create a board that is blocked
        Position end = new Position(4, 1);
        Move move = new Move(new Position(5, 0), end);
        ArrayList<Move> moves = new ArrayList<>();
        blocked.makeMove(moves);//caught by first if statement
        assertEquals(blocked.getSpace(end, blocked.getBoard()).getPiece().getColor(), Piece.Color.RED);

        //TODO show that it did not exicute
    }

    /**
     *
     */
    @Test
    public void ctor_KingingPeice() {
        Position end = new Position(0, 1);
        Move move = new Move(new Position(1, 0), end);
        ArrayList<Move> moves = new ArrayList<>();
        kinging.makeMove(moves);//caught by first if statement
        assertTrue(kinging.getSpace(end, kinging.getBoard()).getPiece() instanceof King);
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
