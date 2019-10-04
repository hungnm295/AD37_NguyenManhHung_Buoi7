package com.example.ad37_nguyenmanhhung_buoi7;

import java.io.Serializable;

public class Friend implements Serializable {
    String name;
    boolean icon;

    public Friend(String name, boolean icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
