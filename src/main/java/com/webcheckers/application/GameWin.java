package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;

/**
 * The GameWin class is responsible for the game win conditions and ends the game.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class GameWin {

    // Game Over Message Strings.
    private final String GAME_OVER = "%s has lost, %s is the winner!";
    private final String PLAYER_RESIGNED = "%s has resigned. %s is the winner!";

    // The GameCenter object managing the game.
    private final GameCenter gameCenter;

    // The game that has been completed.
    private final Game game;

    // The game over message string.
    private String gameOverMessage;

    //Winner of the game
    private Player winner;

    // The game over flag from the game object.
    private boolean isGameOver;

    /**
     * Constructor for GameWin that gets the game over information from game.
     */
    public GameWin(GameCenter gameCenter, Game game) {
        this.gameCenter = gameCenter;
        this.game = game;
        this.isGameOver = game.isGameOver();
    }

    /**
     * Set the game's game over flag to true and remove the game from the GameCenter.
     *
     * @return True if the game was removed, else, false.
     */
    public boolean triggerGameOver() {
        game.setGameOver(true, winner);
        isGameOver = true;
        return gameCenter.removeGame(game);
    }

    /**
     * Determines the number of a specific color of pieces on a board.
     *
     * @param board
     *         The game's board.
     * @param color
     *         The piece color to count.
     *
     * @return True if the number of pieces is zero, else, false.
     */
    public int checkPieceCount(Board board, Piece.Color color) {
        int count = 0;
        for (int i = 0; i < board.BOARD_DIM; i++) {
            for (int j = 0; j < board.BOARD_DIM; j++) {
                Piece piece = board.getBoard()[i][j].getPiece();
                try {
                    if (piece.getColor() == color) {
                        count++;
                    }
                }
                catch (NullPointerException npe) {
                    continue;
                }
            }
        }
        return count;
    }

    /**
     * Trigger the game over functionality with a message for when the active player's pieces have all been captured.
     *
     * @param board
     *         The game's board.
     * @param activePlayerColor
     *         The active player's color.
     *
     * @return True if game over was triggered, else, false.
     */
    public boolean checkPieceGameOver(Board board, Piece.Color activePlayerColor) {
        if (activePlayerColor == Piece.Color.RED) {
            this.gameOverMessage = String.format(GAME_OVER, game.getWhitePlayer(), game.getWhitePlayer());
            winner = game.getWhitePlayer();
        }
        else {
            this.gameOverMessage = String.format(GAME_OVER, game.getRedPlayer(), game.getRedPlayer());
            winner = game.getRedPlayer();
        }
        if (checkPieceCount(board, activePlayerColor) == 0) {
            return triggerGameOver();
        }
        return false;
    }

    /**
     * Trigger the game over functionality with a message for when the active player's pieces are blocked from moving.
     *
     * @param activePlayerColor
     *         The active player's color.
     *
     * @return True if game over was triggered, else, false.
     */
    public boolean checkBlockedGameOver(Piece.Color activePlayerColor) {
        if (activePlayerColor == Piece.Color.RED) {
            this.gameOverMessage = String.format(GAME_OVER, game.getRedPlayer(), game.getWhitePlayer());
            winner = game.getWhitePlayer();
        }
        else {
            this.gameOverMessage = String.format(GAME_OVER, game.getWhitePlayer(), game.getRedPlayer());
            winner = game.getRedPlayer();
        }
        return triggerGameOver();
    }

    /**
     * Trigger the game over functionality with a message for when a given player has resigned from the game.
     *
     * @param player
     *         The player that resigned.
     *
     * @return True if game over was triggered, else, false.
     */
    public boolean checkResignGameOver(Player player) {
        if (player.equals(game.getRedPlayer())) {
            this.gameOverMessage = String.format(PLAYER_RESIGNED, player, game.getWhitePlayer());
            winner = game.getWhitePlayer();
        }
        else {
            this.gameOverMessage = String.format(PLAYER_RESIGNED, player, game.getRedPlayer());
            winner = game.getRedPlayer();
        }
        return triggerGameOver();
    }

    /**
     * A getter method for isGameOver boolean flag.
     *
     * @return The isGameOver boolean flag.
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * A getter method for game over message.
     *
     * @return The game over message.
     */
    public String getGameOverMessage() {
        return gameOverMessage;
    }
}