package com.webcheckers.model;

import java.util.Objects;

/**
 * This class is responsible for representing a Player.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class Player {

    // The Player's name.
    private final String name;

    /**
     * Creates a new player data entity using a unique string.
     *
     * @param name
     *     The name for the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * A getter method for the name of the player.
     *
     * @return
     *     The name of the player.
     */
    public String getName() {
        return name;
    }

    //Object methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
