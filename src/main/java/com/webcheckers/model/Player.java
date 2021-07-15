package com.webcheckers.model;

import java.util.Objects;

/**
 * The Player class is responsible for representing a player.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class Player {

    // The Player's name.
    private final String name;

    /**
     * Constructor for Player using a unique string for a name.
     *
     * @param name
     *         The name for the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * A getter method for the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Equals method that compares two Player objects together.
     *
     * @param other
     *         The other player object.
     *
     * @return True if the player objects are equal, else, false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other instanceof Player) {
            Player player = (Player) other;
            return Objects.equals(name, player.name);
        }
        return false;
    }

    /**
     * Creates a hashcode due to overriding equals method from the player's name.
     *
     * @return A unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * String representation of a Player object.
     *
     * @return The Player's name string.
     */
    @Override
    public String toString() {
        return name;
    }
}