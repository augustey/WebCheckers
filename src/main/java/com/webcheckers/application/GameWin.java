package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;

/**
 * This class checks for the game win conditions and ends the game
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class GameWin
{
    private GameCenter gameCenter;
    private Game game;

    private String gameOverMessage;
    private String winner;
    private boolean isGameOver;

    public GameWin(GameCenter gameCenter, Game game) {
        this.gameCenter = gameCenter;
        this.game = game;
        isGameOver = game.isGameOver();
    }

    public boolean triggerGameOver(String gameOverMessage) {
        game.setGameOver(true);
        isGameOver = true;
        this.gameOverMessage = gameOverMessage;
        return gameCenter.removeGame(game);
    }

    public int checkPieceCount(Board board, Piece.Color color) {
        int count = 0;
        for(int i = 0; i < board.BOARD_DIM; i++) {
            for(int j = 0; j < board.BOARD_DIM; j++) {
                Piece piece = board.getBoard()[i][j].getPiece();
                try {
                    if(piece.getColor() == color) {
                        count++;
                    }
                } catch (NullPointerException npe) {
                    continue;
                }
            }
        }
        return count;
    }

    public boolean checkPieceGameOver(Board board, Piece.Color activePlayerColor) {
        String gameOverString = "%s has captured all pieces!";
        if(activePlayerColor == Piece.Color.RED) {
            gameOverString = String.format(gameOverString, game.getWhitePlayer());
        } else
        {
            gameOverString = String.format(gameOverString, game.getRedPlayer());
        }
        if(checkPieceCount(board, activePlayerColor) == 0)
        {
            return triggerGameOver(gameOverString);
        }
        return false;
    }

    public boolean checkBlockedGameOver(Piece.Color activePlayerColor) {
        String gameOverString = "%s has all of their pieces blocked!";
        if(activePlayerColor == Piece.Color.RED) {
            gameOverString = String.format(gameOverString, game.getRedPlayer());
        } else
        {
            gameOverString = String.format(gameOverString, game.getWhitePlayer());
        }
        return triggerGameOver(gameOverString);
    }

    public boolean checkResignGameOver(Player player) {
        String gameOverString = player + " has resigned.";
        return triggerGameOver(gameOverString);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getGameOverMessage() {
        return gameOverMessage;
    }
}
