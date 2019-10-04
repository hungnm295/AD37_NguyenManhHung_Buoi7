package com.example.ad37_nguyenmanhhung_buoi7;

public class Message {
    String name;
    String message;
    boolean icon;

    public Message(String name, String message, boolean icon) {
        this.name = name;
        this.message = message;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
