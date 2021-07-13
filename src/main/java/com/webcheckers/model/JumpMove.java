package com.webcheckers.model;

/**
 *
 *
 * @author <a href = 'mailto:whd8254@rit.edu'>William Dabney</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class JumpMove extends Move {

    //
    private Position jumped;

    /**
     *
     * @param start
     * @param end
     */
    public JumpMove(Position start, Position end) {
        super(start, end);
        this.createJumpPosition();
    }

    /**
     *
     */
    private void createJumpPosition() {
        int row1 = super.getStart().getRow();
        int row2 = super.getEnd().getRow();
        int col1 = super.getStart().getCell();
        int col2 = super.getEnd().getCell();

        int r = (row1 + row2) / 2;
        int c = (col1 + col2) / 2;

        this.jumped = new Position(r, c);
    }

    /**
     *
     * @return
     */
    public Position getJumpedPosition() {
        return this.jumped;
    }
}