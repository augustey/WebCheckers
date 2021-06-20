package com.webcheckers.model;

public class Row {
    private int index;//what row it is 0 - 7
    private Space[] spaces;//array of the spaces that make up the row

    public Row(int index, Space[] spaces){
        this.index = index;
        this.spaces = spaces;
    }

    public int getIndex(){
        return index;
    }
}
