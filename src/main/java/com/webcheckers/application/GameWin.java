package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;

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

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getGameOverMessage() {
        return gameOverMessage;
    }
}
