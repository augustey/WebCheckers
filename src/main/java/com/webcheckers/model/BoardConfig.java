package com.webcheckers.model;

import com.webcheckers.application.GameWin;

/**
 * Used to set backdoors for different board configurations.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class BoardConfig {
    //Different usernames for redPlayer that correspond to different board configurations
    public static final String KING = "king";
    public static final String CHAIN_JUMP = "chainjump";
    public static final String SINGLE_JUMP = "singlejump";
    public static final String NO_PIECES = "nopieces";
    public static final String BLOCKED = "blocked";

    /**
     * Generate a board configuration based on a string.
     *
     * @param gameWin The GameWin object used to create a new board.
     * @param config The type of configuration to generate.
     * @return The new board with a specific game scenario.
     */
    public static Board newBoard(GameWin gameWin, String config) {
        Board board = new Board(gameWin);

        switch(config) {
            case KING:
                generateKing(board);
                break;
            case CHAIN_JUMP:
                generateChainJump(board);
                break;
            case SINGLE_JUMP:
                generateSingleJump(board);
                break;
            case NO_PIECES:
                generateNoPieces(board);
                break;
            case BLOCKED:
                generateBlocked(board);
                break;
        }

        return board;
    }

    /**
     * Alter an existing board to a configuration where red has a piece one move away
     * from becoming a king.
     *
     * @param board The board to change (Assumed to be in initial configuration)
     */
    public static void generateKing(Board board) {
        Space[][] boardArr = board.getBoard();

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < boardArr[r].length; c++) {
                if(r != 0 || c != 5)
                    boardArr[r][c].setPiece(null);
            }
        }

        boardArr[1][2].setPiece(boardArr[5][6].getPiece());
        boardArr[5][6].setPiece(null);
    }

    /**
     * Alter a board to a configuration where red has a chain jump on their turn.
     * @param board The board to change (Assumed to be in initial configuration)
     */
    public static void generateChainJump(Board board) {
        Space[][] boardArr = board.getBoard();

        for(int i = 2; i >= 0; i--) {
            for (Space space : boardArr[i]) {
                if(space.getPiece() != null) {
                    boardArr[2 * i + 1][space.getColIdx()+(i%2 == 0 ? -1:0)].setPiece(space.getPiece());
                    space.setPiece(null);
                }
            }
        }
    }

    /**
     * Alter a board to a configuration where red has multiple single jumps o their turn.
     */
    public static void generateSingleJump(Board board) {
        Space[][] boardArr = board.getBoard();

        for(Space space: boardArr[2]) {
            boardArr[4][space.getColIdx()].setPiece(space.getPiece());
            space.setPiece(null);
        }
    }

    /**
     * Alter a board to a configuration where white has one piece left.
     * @param board The board to change (Assumed to be in initial configuration)
     */
    public static void generateNoPieces(Board board) {
        Space[][] boardArr = board.getBoard();

        for(int r = 0; r < 3; r++) {
            for(Space space: boardArr[r]) {
                space.setPiece(null);
            }
        }

        boardArr[4][3].setPiece(new SinglePiece(Piece.Color.WHITE));
    }

    /**
     * Alter a board to a configuration where white cannot make any valid moves.
     * @param board The board to change (Assumed to be in initial configuration)
     */
    public static void generateBlocked(Board board) {
        Space[][] boardArr = board.getBoard();

        for(Space[] row: boardArr) {
            for(Space space: row) {
                space.setPiece(null);
            }
        }

        boardArr[0][7].setPiece(new SinglePiece(Piece.Color.WHITE));
        boardArr[1][6].setPiece(new King(Piece.Color.RED));
        boardArr[2][5].setPiece(new King(Piece.Color.RED));
        boardArr[0][3].setPiece(new King(Piece.Color.RED));
    }
}
