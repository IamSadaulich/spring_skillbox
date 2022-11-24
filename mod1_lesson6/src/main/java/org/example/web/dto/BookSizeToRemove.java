package org.example.web.dto;

import javax.validation.constraints.Digits;

public class BookSizeToRemove {
    @Digits(integer = 4, fraction = 0)
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
