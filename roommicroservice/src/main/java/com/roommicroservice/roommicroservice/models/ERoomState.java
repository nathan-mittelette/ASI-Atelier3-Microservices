package com.roommicroservice.roommicroservice.models;

public enum ERoomState {
    CREATED("CREATED"),
    JOINED("JOINED"),
    CARDSSETTED("CARDSSETTED"),
    DONE("DONE");

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
            case "CARDSETTED":
                state = ERoomState.CARDSSETTED;
                break;
            case "DONE":
                state = ERoomState.DONE;
                break;
        }

        return state;
    }

    @Override
    public String toString() {
        return text;
    }
}
