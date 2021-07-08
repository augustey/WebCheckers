package com.webcheckers.application;

import com.webcheckers.model.Game;

public class GameWin
{
    private GameCenter gameCenter;
    private Game game;

    private String gameOverMessage;
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

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getGameOverMessage() {
        return gameOverMessage;
    }
}
