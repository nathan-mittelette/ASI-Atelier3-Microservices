CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS player;
CREATE TABLE public.player
(
    id     serial NOT NULL,
    userId bigint NOT NULL,
    cardId bigint NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.room;
CREATE TABLE public.room
(
    id        serial NOT NULL,
    player1Id bigint,
    player2Id bigint,
    state     bigint,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_playerd1Id FOREIGN KEY (player1Id)
        REFERENCES public.player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_playerd2Id FOREIGN KEY (player2Id)
        REFERENCES public.player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ON "room" ("id");
CREATE INDEX ON "player" ("id");
