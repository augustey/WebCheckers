package com.webcheckers.model;

import com.webcheckers.application.GameWin;
import com.webcheckers.util.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The board class is responsible for handling all functionality
 * related to the board during a game.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Board implements Iterable<Row> {

    // The length and width of a checkers board.
    public final int BOARD_DIM = 8;

    // 2D Array of Spaces that form the board.
    private Space[][] board;

    // The color of the player whose turn it is.
    private Piece.Color activePlayerColor;

    // The game win object
    private GameWin gameWin;

    /**
     * Enum that shows the different types of moves.
     */
    enum MoveType {Jump, Single, Blocked}

    // The current move type in the board.
    private MoveType moveType;

    /**
     * Constructor for the Board.
     */
    public Board(GameWin gameWin) {
        this.gameWin = gameWin;
        this.activePlayerColor = Piece.Color.RED;
        this.board = new Space[BOARD_DIM][BOARD_DIM];
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Space space;
                if (col % 2 + row % 2 == 1) {
                    space = new Space(row, col, null, true);
                    if (row > BOARD_DIM - 4) {
                        space.setPiece(new SinglePiece(Piece.Color.RED));
                    }
                    else if (row < 3) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                }
                else {
                    space = new Space(row, col, null, false);
                }
                this.board[row][col] = space;
            }
        }
//        ptuiDebug();
    }

    public Board(Board copy) {
        this.board = new Space[BOARD_DIM][BOARD_DIM];
        for (int row = 0; row < BOARD_DIM; row++) {
            System.arraycopy(copy.board[row], 0, this.board[row], 0, BOARD_DIM);
        }
        this.moveType = copy.moveType;
        this.activePlayerColor = copy.getActivePlayerColor();
    }

    public void determineMoveType() throws ArrayIndexOutOfBoundsException {
        moveType = MoveType.Blocked;
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Piece piece = this.board[row][col].getPiece();
                if (piece != null && piece.getColor() == activePlayerColor) {

                    ArrayList<JumpMove> jumpMoves = new ArrayList<>(piece.allJumps(row, col));
                    if (validateJumpMoves(jumpMoves)) {
                        moveType = MoveType.Jump;
                        return;
                    }

                    ArrayList<SingleMove> singleMoves = new ArrayList<>(piece.allSingleMoves(row, col));
                    if (validateSingleMoves(singleMoves)) {
                        moveType = MoveType.Single;

                    }

                }
            }
        }
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMove = new ArrayList<>();

        determineMoveType();
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Piece piece = this.board[row][col].getPiece();
                if (piece != null && piece.getColor() == activePlayerColor) {

                    if (moveType == MoveType.Jump) {
                        ArrayList<JumpMove> jumpMoves = new ArrayList<>(piece.allJumps(row, col));
                        if (validateJumpMoves(jumpMoves)) {
                            possibleMove.addAll(jumpMoves);
                        }
                    } else if (moveType == MoveType.Single) {
                        ArrayList<SingleMove> singleMoves = new ArrayList<>(piece.allSingleMoves(row, col));
                        if (validateSingleMoves(singleMoves)) {
                            possibleMove.addAll(singleMoves);
                        }
                    }

                }
            }
        }
        return possibleMove;
    }


    /**
     * This method look at all generated singleMoves for one piece and if it is found valid it adds the move to possible moves
     *
     * @param moves
     */
    public boolean validateSingleMoves(ArrayList<SingleMove> moves) {
        for (int i = 0; i < moves.size(); i++) {
            SingleMove move = moves.get(i);

            if (validateSingleMove(move)) {
                return true;
            }
        }
        return false;

    }

    /**
     * This method looks at an individual single move and checks if it is valid
     *
     * @param move
     * @return
     */
    private boolean validateSingleMove(SingleMove move) {
        Position end = move.getEnd();
        int row = end.getRow();
        int col = end.getCell();
        try {
            return this.board[row][col].isValid();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }


    /**
     * This method look at all generated jumpMoves for one piece and if it is found valid it adds the move to possible moves
     *
     * @param moves
     */
    public boolean validateJumpMoves(ArrayList<JumpMove> moves) {
        for (int i = 0; i < moves.size(); i++) {
            JumpMove move = moves.get(i);

            if (validateJumpMove(move)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method looks at an individual jumpMove and checks if it is valid
     *
     * @param move
     * @return
     */
    private boolean validateJumpMove(JumpMove move) {
        Position end = move.getEnd();
        int endRow = end.getRow();
        int endCol = end.getCell();
        Position jumped = move.getJumpedPosition();
        try {
            if (getSpace(jumped, this.board).getPiece().getColor() != activePlayerColor) {
                return this.board[endRow][endCol].isValid();
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Space[][] getBoard() {
        return board;
    }

    public Piece.Color getActivePlayerColor() {
        return activePlayerColor;
    }

    public void setActivePlayerColor(Piece.Color activePlayerColor) {
        this.activePlayerColor = activePlayerColor;
    }

    /**
     * This method is used to convert a position to what space it represents
     *
     * @param position
     * @return
     */
    public Space getSpace(Position position, Space[][] board) {
        int row = position.getRow();
        int col = position.getCell();
        return board[row][col];
    }

    /**
     * This is called to make a move
     *
     * @param moves
     */
    public Message makeMove(ArrayList<Move> moves) {
//        System.out.println(this);
//        moveType = MoveType.Single;
        determineMoveType();
        System.out.println("board: " + moveType);
        if (moveType == MoveType.Blocked) {
            gameWin.checkBlockedGameOver(activePlayerColor);
        } else if (moveType == MoveType.Single && moves.size() != 1) {
            //TODO: throws error singleMoves can only be one in magnitude
        }

        Board copy = new Board(this);

        Position endPos = null;
        Space endSpace = null;
        Boolean madeMove;

        //Loops though all moves
        for (int i = 0; i < moves.size(); i++) {
            madeMove = false;
            Move curMove = moves.get(i);
            System.out.println(curMove);

            Position startPos = curMove.getStart();
            endPos = curMove.getEnd();

            Space startSpace = getSpace(startPos, copy.board);
            endSpace = getSpace(endPos, copy.board);

            Piece piece = startSpace.getPiece();

            int row = startPos.getRow();
            int col = startPos.getCell();

            if (moveType == MoveType.Single) {
                ArrayList<SingleMove> singleMoves = new ArrayList<>(piece.allSingleMoves(row, col));
                if (singleMoves.contains(curMove)) {
                    int index = singleMoves.indexOf(curMove);
                    SingleMove singleMove = singleMoves.get(index);
                    if (validateSingleMove(singleMove)) {
                        madeMove = true;
                        executeSingleMove(startSpace, endSpace);
                    }
                }
            } else {//Jump move
                ArrayList<JumpMove> jumpMoves = new ArrayList<>();
                jumpMoves.addAll(piece.allJumps(row, col));
                if (jumpMoves.contains(curMove)) {
                    int index = jumpMoves.indexOf(curMove);
                    JumpMove jumpMove = jumpMoves.get(index);
                    if (validateJumpMove(jumpMove)) {
                        madeMove = true;
                        Space jumpedSpace = getSpace(jumpMove.getJumpedPosition(), board);
                        executeJumpMove(startSpace, jumpedSpace, endSpace);
                    }
                }
            }
            //TODO if move not executed error
        }
        if (moveType == MoveType.Jump) {

            ArrayList<JumpMove> jumpMoves = new ArrayList<>();
            Piece piece = endSpace.getPiece();
            int row = endPos.getRow();
            int col = endPos.getCell();

            jumpMoves.addAll(piece.allJumps(row, col));

            if (validateJumpMoves(jumpMoves)) {
                return Message.error("Another jump move is possible!");
            }
        }

        kingPiece(endSpace);

        if (this.activePlayerColor == Piece.Color.RED) {
            this.activePlayerColor = Piece.Color.WHITE;
        } else {
            this.activePlayerColor = Piece.Color.RED;
        }
        for (int row = 0; row < BOARD_DIM; row++) {
            System.arraycopy(copy.board[row], 0, this.board[row], 0, BOARD_DIM);
        }
        flip();

        determineMoveType();
        if (moveType == MoveType.Blocked) {
            gameWin.checkBlockedGameOver(activePlayerColor);
        }

        gameWin.checkPieceGameOver(this, activePlayerColor);

        return Message.info("Move is valid!");
    }

    private void kingPiece(Space endSpace) {
        int row = endSpace.getRowIdx();
        Piece piece = endSpace.getPiece();
        if (row == 0 && piece instanceof SinglePiece) {
            endSpace.setPiece(new King(activePlayerColor));
        }
    }

    public void executeSingleMove(Space start, Space end) {
        end.setPiece(start.getPiece());
        start.setPiece(null);
    }

    public void executeJumpMove(Space start, Space jumped, Space end) {
        end.setPiece(start.getPiece());
        jumped.setPiece(null);
        start.setPiece(null);
    }

    /**
     * This method flips the board to provide the proper orientation for a player.
     */
    public void flip() {
        Space[][] flippedBoard = new Space[BOARD_DIM][BOARD_DIM];
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                int flippedRow = BOARD_DIM - row - 1;
                int flippedCol = BOARD_DIM - col - 1;
                Piece piece = this.board[row][col].getPiece();
                boolean isValid = this.board[row][col].getIsValid();
                Space space = new Space(flippedRow, flippedCol, piece, isValid);
                flippedBoard[BOARD_DIM - row - 1][BOARD_DIM - col - 1] = space;
            }
        }
        this.board = flippedBoard;
    }

    public void ptuiDebug() {
        System.out.println("Enter in start pos and end pos on separate lines");
        System.out.println("Start:row col");
        System.out.println("End:row col");
        Scanner scan = new Scanner(System.in);
        String start;
        String end;

        int startRow;
        int startCol;
        int endRow;
        int endCol;
//        while(true){
        System.out.println(this);
//            start = scan.nextLine();
//            end = scan.nextLine();
//
//            String[] startCords = start.split(" ");
//            startRow = Integer.parseInt(startCords[0]);
//            startCol = Integer.parseInt(startCords[1]);
//            String[] endCords = end.split(" ");
//            endRow = Integer.parseInt(endCords[0]);
//            endCol = Integer.parseInt(endCords[1]);
//
//            Move move = new Move(new Position(startRow, startCol), new Position(endRow, endCol));

        Move move = new Move(new Position(5, 0), new Position(3, 2));

        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);

        Move move1 = new Move(new Position(3, 2), new Position(1, 4));
        moves.add(move1);
        makeMove(moves);
//        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Row> iterator() {
        ArrayList<Row> board = new ArrayList<>();
        for (int row = 0; row < BOARD_DIM; row++) {
            ArrayList<Space> spaces = new ArrayList<>();
            for (int col = 0; col < BOARD_DIM; col++) {
                spaces.add(this.board[row][col]);
            }
            Row curRow = new Row(row, spaces);
            board.add(curRow);
        }
        return board.iterator();
    }

    /**
     * String representation of board used for debugging.
     *
     * @return A string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder textBoard = new StringBuilder();
        for (Space[] spaces : board) {
            for (Space curSpace : spaces) {
                if (!curSpace.isValid() && curSpace.getPiece() == null) {
                    textBoard.append("*");//none playable spot
                } else if (curSpace.getPiece() == null) {//playable spot
                    textBoard.append("_");
                } else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece() instanceof SinglePiece) {//single red
                    textBoard.append("r");
                } else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece() instanceof SinglePiece) {//single white
                    textBoard.append("w");
                } else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece() instanceof King) {//king white
                    textBoard.append("R");
                } else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece() instanceof King) {//king red
                    textBoard.append("W");
                }
            }
            textBoard.append("\n");
        }
        return textBoard.toString();
    }
}
