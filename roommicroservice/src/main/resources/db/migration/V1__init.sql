CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS player;
CREATE TABLE public.player
(
    id     serial NOT NULL,
    userid bigint NOT NULL,
    cardid bigint NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.room;
CREATE TABLE public.room
(
    id        serial NOT NULL,
    player1id bigint,
    player2id bigint,
    state     character varying(255),
    bet       bigint,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_playerd1Id FOREIGN KEY (player1id)
        REFERENCES public.player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_playerd2Id FOREIGN KEY (player2id)
        REFERENCES public.player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ON "room" ("id");
CREATE INDEX ON "player" ("id");
