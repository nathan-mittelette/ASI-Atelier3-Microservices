package com.asi.lib.enums;

public enum ERoomState {
    CREATED("CREATED"),
    JOINED("JOINED"),
    FINISHED("FINISHED");

    private final String text;

    ERoomState(final String text) {
        this.text = text;
    }

    public static ERoomState fromString(String type) {
        ERoomState state = ERoomState.CREATED;

        switch (type) {
            case "CREATED":
                state = ERoomState.CREATED;
                break;
            case "JOINED":
                state = ERoomState.JOINED;
                break;
            case "FINISHED":
                state = ERoomState.FINISHED;
                break;
        }

        return state;
    }

    @Override
    public String toString() {
        return text;
    }
}
