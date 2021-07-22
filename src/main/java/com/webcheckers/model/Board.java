package com.webcheckers.model;

import com.webcheckers.application.GameWin;
import com.webcheckers.util.Message;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The board class is responsible for handling all functionality related to the board during a game.
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Board implements Iterable<Row> {


    // The length and width of a checkers board.
    public final int BOARD_DIM = 8;

    // The game win object
    private final GameWin gameWin;

    // 2D Array of Spaces that form the board.
    private Space[][] board;

    // The color of the player whose turn it is.
    private Piece.Color activePlayerColor;

    /**
     * Enum that shows the different types of moves.
     */
    enum MoveType {Jump, Single, Blocked}

    // The current move type in the board.
    private MoveType moveType;

    // The current move type in the board.
    private ArrayList<Move> possibleMoves;

    // Starting position for a possible multiple jumps
    private Position startJumpPos;

    /**
     * Constructor for the board class that builds the board and initializes the starting player color.
     *
     * @param gameWin
     *         The gameWin object responsible for handling the win condition.
     */
    public Board(GameWin gameWin) {
        this.gameWin = gameWin;
        this.activePlayerColor = Piece.Color.RED;
        this.possibleMoves = new ArrayList<>();
        this.board = new Space[BOARD_DIM][BOARD_DIM];
        generateBoard();
        //ptuiDebug();
    }

    /**
     * Constructor for the board class that builds the board
     */
    public Board() {
        gameWin = null;
        this.activePlayerColor = Piece.Color.RED;
        this.board = new Space[BOARD_DIM][BOARD_DIM];
        generateBoard();
        //ptuiDebug();
    }

    public void generateBoard() {
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Space space;
                if (col % 2 + row % 2 == 1) {
                    space = new Space(row, col, null, true);
//                    if (row > BOARD_DIM - 4) {
//                        space.setPiece(new SinglePiece(Piece.Color.RED));
//                    }
//                    else if (row < 3) {
//                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
//                    }
                    if (row > BOARD_DIM - 3) {
                        space.setPiece(new King(Piece.Color.RED));
                    }
                    else if (row == 5) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if(row == 3) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                    else if(row == 1) {
                        space.setPiece(new SinglePiece(Piece.Color.WHITE));
                    }
                }
                else {
                    space = new Space(row, col, null, false);
                }
                this.board[row][col] = space;
            }
        }
    }

    /**
     * Copy constructor used for performing moves and for communicating the board to the UI.
     *
     * @param copy
     *         The other board to copy from.
     */
    public Board(Board copy) {
        this.gameWin = copy.gameWin;
        this.startJumpPos = null;
        this.possibleMoves = new ArrayList<>(copy.possibleMoves);
        this.board = new Space[BOARD_DIM][BOARD_DIM];
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Space space = new Space(copy.board[row][col]);
                this.board[row][col] = space;
            }
        }
        this.moveType = copy.getMoveType();
        this.activePlayerColor = copy.getActivePlayerColor();
    }

    /**
     * Determines what the current possible move type the active player can make.
     *
     * @throws ArrayIndexOutOfBoundsException
     *         Throws if the array is indexed out of bounds
     */
    public void determineMoveType() throws ArrayIndexOutOfBoundsException {
        moveType = MoveType.Blocked;
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Piece piece = this.board[row][col].getPiece();
                if (piece != null && piece.getColor() == activePlayerColor) {
                    // Find the jump moves if possible and return if found.
                    ArrayList<JumpMove> jumpMoves = new ArrayList<>(piece.allJumps(row, col));
                    if (validateJumpMoves(jumpMoves)) {
                        moveType = MoveType.Jump;
                        return;
                    }
                    // Find the single moves if there are any.
                    ArrayList<SingleMove> singleMoves = new ArrayList<>(piece.allSingleMoves(row, col));
                    if (validateSingleMoves(singleMoves)) {
                        moveType = MoveType.Single;
                    }
                }
            }
        }
    }

    /**
     * Generates all the possible moves that a user can make when trying to make a move on the UI.
     */
    private void generatePossibleMoves() {
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                Piece piece = this.board[row][col].getPiece();
                if (piece != null && piece.getColor() == activePlayerColor) {
                    if (moveType == MoveType.Jump) {
                        ArrayList<JumpMove> jumpMoves = new ArrayList<>(piece.allJumps(row, col));
                        jumpMoves.removeIf(jumpMove -> !validateJumpMove(jumpMove));
                        possibleMoves.addAll(jumpMoves);
                    }
                    else if (moveType == MoveType.Single) {
                        ArrayList<SingleMove> singleMoves = new ArrayList<>(piece.allSingleMoves(row, col));
                        singleMoves.removeIf(singleMove -> !validateSingleMove(singleMove));
                        possibleMoves.addAll(singleMoves);
                    }
                }
            }
        }
    }

    /**
     * Verifies if a move is valid and could be executed.
     *
     * @return True if a move is valid, else, false.
     */
    public boolean isPossibleMove(Move move) {
        determineMoveType();
        if (possibleMoves.isEmpty()) {
            generatePossibleMoves();
        }
        if (!possibleMoves.contains(move)) {
            if (moveType == MoveType.Jump && this.startJumpPos != null) {
                JumpMove jm = new JumpMove(move);
                if (getSpace(startJumpPos, this).getPiece() instanceof SinglePiece) {
                    if (validateJumpMove(jm)) {
                        return jm.getStart().getRow() != 0;
                    }
                }
                return validateJumpMove(jm);
            }
        }
        if (possibleMoves.contains(move)) {
            if (moveType == MoveType.Jump) {
                this.startJumpPos = move.getStart();
            }
            return true;
        }
        return false;
    }

    /**
     * A getter method for the 2D space array that represents the board of spaces and pieces.
     *
     * @return The board.
     */
    public Space[][] getBoard() {
        return board;
    }

    /**
     * A getter method for the active player color.
     *
     * @return The active player color.
     */
    public Piece.Color getActivePlayerColor() {
        return activePlayerColor;
    }

    /**
     * A getter method for the a space on the board.
     *
     * @param position
     *         The position object containing the row and col location.
     *
     * @return The space at the position's location.
     */
    public Space getSpace(Position position, Board someBoard) {
        int row = position.getRow();
        int col = position.getCell();
        return someBoard.board[row][col];
    }

    /**
     * A getter method for the move type.
     *
     * @return The move type.
     */
    public MoveType getMoveType() {
        determineMoveType();
        return moveType;
    }

    /**
     * A setter method used only for testing purposes.
     *
     * @param activePlayerColor
     *         A Piece.Color color.
     */
    public void setActivePlayerColor(Piece.Color activePlayerColor) {
        this.activePlayerColor = activePlayerColor;
    }

    /**
     * Validates the determined singlemoves to establish the active player's move type.
     *
     * @param moves
     *         A list of single moves.
     *
     * @return True if any move in the list is a valid single move, else, false.
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
     * Validates a singular single move.
     *
     * @param move
     *         A SingleMove object.
     *
     * @return True if the single move is valid, else, false.
     */
    private boolean validateSingleMove(SingleMove move) {
        Position end = move.getEnd();
        int row = end.getRow();
        int col = end.getCell();
        try {
            return this.board[row][col].isValid();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Validates the determined jumpMoves to establish the active player's move type.
     *
     * @param moves
     *         A list of jump moves.
     *
     * @return True if any move in the list is a valid jump move, else, false.
     */
    public boolean validateJumpMoves(ArrayList<JumpMove> moves) {
        for (JumpMove move : moves) {
            if (validateJumpMove(move)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates a singular jump move.
     *
     * @param move
     *         A JumpMove object.
     *
     * @return True if the jump move is valid, else, false.
     */
    private boolean validateJumpMove(JumpMove move) {
        Position end = move.getEnd();
        int endRow = end.getRow();
        int endCol = end.getCell();
        Position jumped = move.getJumpedPosition();
        try {
            if (getSpace(jumped, this).getPiece().getColor() != activePlayerColor) {
                return this.board[endRow][endCol].isValid();
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Kings a piece that ends up in the top most row.
     *
     * @param endSpace
     *         The space to see if a piece has landed on the top most row.
     */
    private void kingPiece(Space endSpace) {
        int row = endSpace.getRowIdx();
        Piece piece = endSpace.getPiece();
        if (row == 0 && piece instanceof SinglePiece) {
            endSpace.setPiece(new King(activePlayerColor));
        }
    }

    /**
     * Executes a single move.
     *
     * @param start
     *         The start space.
     * @param end
     *         The end space.
     */
    public void executeSingleMove(Space start, Space end) {
        end.setPiece(start.getPiece());
        start.setPiece(null);
    }

    /**
     * Executes a jump move.
     *
     * @param start
     *         The start space.
     * @param jumped
     *         The jumped over space.
     * @param end
     *         The end space.
     */
    public void executeJumpMove(Space start, Space jumped, Space end) {
        end.setPiece(start.getPiece());
        jumped.setPiece(null);
        start.setPiece(null);
    }

    /**
     * Flips the board to provide the proper orientation for the active player.
     */
    public void flip() {
        Space[][] flippedBoard = new Space[BOARD_DIM][BOARD_DIM];
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                int flippedRow = BOARD_DIM - row - 1;
                int flippedCol = BOARD_DIM - col - 1;
                Piece piece = this.board[row][col].getPiece();
                boolean isValid = this.board[row][col].getIsValid();
                Space newSpace = new Space(flippedRow, flippedCol, piece, isValid);
                flippedBoard[flippedRow][flippedCol] = newSpace;
            }
        }
        this.board = flippedBoard;
    }

    /**
     * Uses a list of moves to make moves on the board.
     *
     * @param moves
     *         A list of moves.
     *
     * @return A message if the moves were valid, or an error if there is another jump move possible.
     */
    public Message makeMove(ArrayList<Move> moves) {
        // Handles the win condition where there are no possible moves.

        System.out.println("makeMove: " + moves);

        if (moveType == MoveType.Blocked) {
            gameWin.checkBlockedGameOver(activePlayerColor);
        }
        else if (moveType == MoveType.Single && moves.size() != 1) {
            //TODO: throws error singleMoves can only be one in magnitude
        }
        // Create the board to make the moves on.
        Board copy = new Board(this);

        // Establish the end position and space.
        Position endPos = null;
        Space endSpace = null;
        // Loops though all moves.
        for (Move curMove : moves) {
            Position startPos = curMove.getStart();
            endPos = curMove.getEnd();
            Space startSpace = getSpace(startPos, copy);
            endSpace = getSpace(endPos, copy);

            // Perform a single move.
            if (curMove instanceof SingleMove) {
                copy.executeSingleMove(startSpace, endSpace);
            }
            // Perform jump move.
            else {
                Space jumpedSpace = getSpace(((JumpMove) curMove).getJumpedPosition(), copy);
                copy.executeJumpMove(startSpace, jumpedSpace, endSpace);
            }
        }
        // Check if another jump move is still possible.
        if (moveType == MoveType.Jump) {
            Piece piece = endSpace.getPiece();
            int row = endPos.getRow();
            int col = endPos.getCell();
            ArrayList<JumpMove> jumpMoves = new ArrayList<>(piece.allJumps(row, col));
            jumpMoves.removeIf(jumpMove -> !validateJumpMove(jumpMove));
            for (Move move : moves) {
                jumpMoves.removeIf(jumpMove -> move.getStart().equals(jumpMove.getEnd()));
            }
            if (jumpMoves.size() != 0) {
                return Message.error("Another jump move is possible!");
            }
        }
        // King the piece if its endspace is the top most row.
        kingPiece(endSpace);
        // Change the active player color to the next player.
        if (this.activePlayerColor == Piece.Color.RED) {
            this.activePlayerColor = Piece.Color.WHITE;
        }
        else {
            this.activePlayerColor = Piece.Color.RED;
        }
        // Copy the copy board over to the main board for the next turn.
        for (int row = 0; row < BOARD_DIM; row++) {
            System.arraycopy(copy.board[row], 0, this.board[row], 0, BOARD_DIM);
        }

        // Flip the board orientation for the next player.
        flip();
        // Check the win condition for blocked moves.
        determineMoveType();
        if (moveType == MoveType.Blocked) {
            gameWin.checkBlockedGameOver(activePlayerColor);
        }
        // Check the win condition if there are no more pieces.
        gameWin.checkPieceGameOver(this, activePlayerColor);
        possibleMoves.clear();
        this.startJumpPos = null;
        return Message.info("Move is valid!");
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
     * String representation of a board used for debugging.
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
                }
                else if (curSpace.getPiece() == null) {//playable spot
                    textBoard.append("_");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece() instanceof SinglePiece) {//single red
                    textBoard.append("r");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece() instanceof SinglePiece) {//single white
                    textBoard.append("w");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.RED &&
                        curSpace.getPiece() instanceof King) {//king white
                    textBoard.append("R");
                }
                else if (curSpace.getPiece().getColor() == Piece.Color.WHITE &&
                        curSpace.getPiece() instanceof King) {//king red
                    textBoard.append("W");
                }
            }
            textBoard.append("\n");
        }
        return textBoard.toString();
    }


    /**
     * equals method for board
     *
     * @param o
     *          the other object being compared to this board
     *
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Arrays.equals(board, board1.board);
    }

    /**
     * hash code method for board
     *
     * @return the hashcode generated by the Arrays class
     */
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(board);
    }

    /**
     * Creates a board from a string
     *
     * @param strBoard
     *          board String
     */
    public static Board fromString(String strBoard) {
        Board board = new Board();

        Space[][] arr = board.getBoard();

        String[] tokens = strBoard.split("\n");

        for(int i =0 ; i < board.BOARD_DIM; i++) {
            for(int j =0 ; j < board.BOARD_DIM; j++) {
                char[] cTokens = tokens[i].toCharArray();

                switch (cTokens[j]) {
                    case '_':
                        arr[i][j].setPiece(null);
                        break;
                    case 'r':
                        arr[i][j].setPiece(new SinglePiece(Piece.Color.RED));
                        break;
                    case 'w':
                        arr[i][j].setPiece(new SinglePiece(Piece.Color.WHITE));
                        break;
                    case 'R':
                        arr[i][j].setPiece(new King(Piece.Color.RED));
                        break;
                    case 'W':
                        arr[i][j].setPiece(new King(Piece.Color.WHITE));
                        break;
                }
            }
        }

        return board;
    }

    //    public void ptuiDebug() {
//        System.out.println("Enter in start pos and end pos on separate lines");
//        System.out.println("Start:row col");
//        System.out.println("End:row col");
    public void ptuiDebug() {
        System.out.println("Enter in start pos and end pos on separate lines");
        System.out.println("Start:row col");
        System.out.println("End:row col");
//        Scanner scan = new Scanner(System.in);
        String start;
        String end;

        int startRow;
        int startCol;
        int endRow;
        int endCol;
//        while(true){
//        System.out.println(this);
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

        Move move = new JumpMove(new Position(5, 0), new Position(3, 2));

        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move);

        Move move1 = new JumpMove(new Position(3, 2), new Position(1, 4));
        moves.add(move1);
        makeMove(moves);
            System.out.println(this);
//        }
    }

    public static void main(String[] args) {
        GameWin gameWin = mock(GameWin.class);
        new Board(gameWin);
    }
}