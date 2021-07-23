package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameWin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

/**
 * The unit test suite for the {@link Board} component.
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("Model-tier")
public class BoardTest {

    private Board CuTGeneralBoard;
    private Board CuTJump;
    private Board CuTChainJump;
    private Board CuTBlocked;
    private Board CuTKing;

    private GameWin gameWin;

    @BeforeEach
    public void setUp() {
        //this sets up the normal starting board
        gameWin = mock(GameWin.class);
        CuTGeneralBoard = new Board(gameWin);
        CuTJump = new Board(gameWin);
        generateJumpableBoard();
        CuTChainJump = new Board(gameWin);
        generateChainJumpableBoard();
        CuTBlocked = new Board(gameWin);
        generateBlockedBoard();
        CuTKing = new Board(gameWin);
        generateKingingBoard();
    }

    /**
     * Changes a new board to one that is can be kinged on first move
     */
    private void generateKingingBoard() {
        for (int row = 0; row < CuTKing.BOARD_DIM; row++) {
            for (int col = 0; col < CuTKing.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = CuTKing.getSpace(new Position(row, col), CuTKing);
                    if (row > 0) {
                        space.setPiece(new SinglePiece(Piece.Color.RED));
                    }
                    else {
                        space.setPiece(null);
                    }
                }
            }
        }
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
     * Changes a new board to one that chain jumps are possible on first move
     */
    private void generateChainJumpableBoard() {
        for (int row = 0; row < CuTChainJump.BOARD_DIM; row++) {
            for (int col = 0; col < CuTChainJump.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = CuTChainJump.getSpace(new Position(row, col), CuTChainJump);
                    if (row == 4) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if (row == 2) {
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
     * Changes a new board to one that is blocked on first move
     */
    private void generateBlockedBoard() {
        for (int row = 0; row < CuTBlocked.BOARD_DIM; row++) {
            for (int col = 0; col < CuTBlocked.BOARD_DIM; col++) {
                if (col % 2 + row % 2 == 1) {
                    Space space = CuTBlocked.getSpace(new Position(row, col), CuTBlocked);
                    if (row == 4 || row == 3) {
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
        System.out.println(CuTGeneralBoard);
        assertEquals(Board.MoveType.Single, CuTGeneralBoard.getMoveType());
    }

    /**
     * Check if the move type can be correctly evaluated to jump
     */
    @Test
    public void ctor_determineMoveTypeJump() {
        System.out.println(CuTJump);
        assertEquals(Board.MoveType.Jump, CuTJump.getMoveType());
    }

    /**
     * Check if the move type can be correctly evaluated to blocked
     */
    @Test
    public void ctor_determineMoveTypeBlocked() {
        assertEquals(Board.MoveType.Blocked, CuTBlocked.getMoveType());
    }

    /**
     * Checks if the flip method is working properly
     */
    @Test
    public void ctor_flipAndCopy() {
        Board copy = new Board(CuTGeneralBoard);
        copy.flip();
        copy.flip();
        assertEquals(CuTGeneralBoard.toString(), copy.toString());
    }

    /**
     * succesfully exicutes a single move
     */
    @Test
    public void ctor_makeMoveSingleMove() {
        //Single move
//        Board startBoard = new Board(CuTGeneralBoard);
//        Position end = new Position(4, 1);
//        Move singleMove = new Move(new Position(5, 0), end);
//        ArrayList<Move> singleMoves = new ArrayList<>();
//        singleMoves.add(singleMove);
//        CuTGeneralBoard.makeMove(singleMoves);
//        System.out.println(CuTGeneralBoard.toString());
//        assertNotEquals(startBoard.toString(), board.toString());
//        CuTGeneralBoard.flip();
//        assertSame(CuTGeneralBoard.getSpace(end, CuTGeneralBoard).getPiece().getColor(), Piece.Color.RED);
    }

    /**
     * checks if a jumpMove with no chain jump is exicuted succesfully
     */
    @Test
    public void ctor_makeMoveJump() {
        //JumpMove
        //TODO need to create a board that has a singleJump avalible
//        Position end = new Position(3, 2);
//        Move jumpMove = new Move(new Position(5, 0), end);
//        ArrayList<Move> jumpMoves = new ArrayList<>();
//        jumpMoves.add(jumpMove);
//        CuTJump.makeMove(jumpMoves);
//
//        System.out.println(CuTJump);
//        CuTJump.flip();
//        assertSame(CuTJump.getSpace(end, CuTJump).getPiece().getColor(), Piece.Color.RED);
        //TODO show the it was exicuted
    }

    /**
     * Checks if you try to jump with another jump avalible it will reject the move and if you then all further jumps it
     * will then succesfully exicute turn
     */
    @Test
    public void ctor_makeMoveChainJump() {
        //TODO need to create a board that a chain jump is avalible
//        Position end = new Position(3, 2);
//        Position start = new Position(5, 0);
//        Move jumpMove = new Move(start, end);
//        ArrayList<Move> jumpMoves = new ArrayList<>();
//        jumpMoves.add(jumpMove);
//        CuTChainJump.makeMove(jumpMoves);//should fail
//        System.out.println(CuTChainJump);
//        assertSame(CuTChainJump.getSpace(start, CuTChainJump).getPiece().getColor(), Piece.Color.RED);//another jump is avalible
//        end = new Position(1, 4);
        //TODO show that it failed
//        Move jumpMove1 = new Move(new Position(3, 2), end);
//        jumpMoves.add(jumpMove1);
//        CuTChainJump.makeMove(jumpMoves);
//        CuTChainJump.flip();
//        assertSame(CuTChainJump.getSpace(end, CuTChainJump).getPiece().getColor(), Piece.Color.RED);
    }

    /**
     * Checks that if the player is determined to blocked no move is made and game is over.
     */
    @Test
    public void ctor_MakeMoveBlocked() {
        //TODO need to create a board that is blocked
//        String startString = CuTBlocked.toString();
//        Position end = new Position(4, 1);
//        Position start = new Position(5, 0);
//        Move move = new Move(start, end);
//        ArrayList<Move> moves = new ArrayList<>();
//        CuTBlocked.makeMove(moves);//caught by first if statement
//        System.out.println(CuTBlocked);
//
//        assertEquals(startString, CuTBlocked.toString());
        //TODO show that it did not exicute
    }

    /**
     *
     */
    @Test
    public void ctor_KingingPiece() {
        System.out.println(CuTKing);
        Position start = new Position(1, 0);
        Position end = new Position(0, 1);
        SingleMove move = new SingleMove(start, end);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);
        CuTKing.determineMoveType();
        CuTKing.makeMove(moves);//caught by first if statement
        CuTKing.flip();
        assertEquals(Piece.Type.KING, CuTKing.getSpace(end, CuTKing).getPiece().getType());
    }

    /**
     * Checks that the getActiveColor works
     */
    @Test
    public void ctor_getActiveColor() {
        assertEquals(Piece.Color.RED, CuTGeneralBoard.getActivePlayerColor());
    }

    /**
     * Checks that setting the active color works
     */
    @Test
    public void ctor_setActiveColor() {
        CuTGeneralBoard.setActivePlayerColor(Piece.Color.WHITE);
        assertEquals(Piece.Color.WHITE, CuTGeneralBoard.getActivePlayerColor());
    }

    /**
     * Checks that getting the board works
     */
    @Test
    public void ctor_getBoard() {
        CuTGeneralBoard.getBoard();
    }


}