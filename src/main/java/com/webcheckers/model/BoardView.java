package com.webcheckers.model;

import java.util.Iterator;

public class BoardView implements Iterable<Row>{

    private final int BOARD_DIM = 8;//the size of the board

    @Override
    public Iterator<Row> iterator() {
        return null;
    }
    public BoardView(){
        //new row
        //new spaces
        //fill spaces with pieces

    }

    public static void main(String[] args) {//for debugging purposes only

    }
}
