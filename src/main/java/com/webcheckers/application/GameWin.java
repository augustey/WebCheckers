package com.webcheckers.application;

import com.webcheckers.model.Game;

public class GameWin
{
    private GameCenter gameCenter;
    private Game game;

    private String gameOverMessage;
    private boolean isGameOver;

    public GameWin(GameCenter gameCenter, Game game)
    {
        this.gameCenter = gameCenter;
        this.game = game;
    }

    public boolean triggerGameOver(String gameOverMessage)
    {
        isGameOver = true;
        this.gameOverMessage = gameOverMessage;
        boolean result = gameCenter.removeGame(game);
        return result;
    }

    public boolean isGameOver()
    {
        return isGameOver;
    }

    public String getGameOverMessage()
    {
        return gameOverMessage;
    }
}
