package com.webcheckers.model;

import java.util.Objects;

/**
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class Move {

    //
    private Position start;

    //
    private Position end;

    /**
     *
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     *
     */
    public Position getStart() {
        return start;
    }

    /**
     *
     */
    public Position getEnd() {
        return end;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Move) {
            Move move = (Move) o;
            return Objects.equals(start, move.start) &&
                    Objects.equals(end, move.end);
        }
        return false;
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Move{" + "start=" + start + ", end=" + end + "}";
    }
}
