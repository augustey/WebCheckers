package com.webcheckers.model;

import com.webcheckers.application.GameWin;

/**
 * Used to set backdoors for different board configurations.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class BoardConfig {
    //Different usernames for redPlayer that correspond to different board configurations
    //Feel free to change them to more creative ones
    public static final String KING = "king";
    public static final String CHAIN_JUMP = "chainjump";
    public static final String SINGLE_JUMP = "singlejump";

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
                break;
            case SINGLE_JUMP:
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

        for(int i = 0; i < 2; i++) {
            for(Space space: boardArr[i]) {
                space.setPiece(null);
            }
        }

        boardArr[1][2].setPiece(boardArr[5][6].getPiece());
        boardArr[5][6].setPiece(null);
    }
}
