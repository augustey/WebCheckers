package com.webcheckers.model;

import java.util.Objects;

/**
 * Value class, holds the unique name for the player
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class Player
{
    private final String name; //name of the player will not change

    /**
     * Creates a new player data entity using a unique string
     * @param name
     */
    public Player(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
