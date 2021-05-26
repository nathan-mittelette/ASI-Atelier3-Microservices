CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS card;
CREATE TABLE public.card
(
    id       serial NOT NULL,
    name     character varying(255),
    description   character varying(255),
    imageUrl character varying(255),
    family character varying(255),
    affinity character varying(255),
    hp bigint,
    energy bigint,
    attack bigint,
    defense bigint,
    price bigint,
    userId bigint,
    CONSTRAINT pk_card PRIMARY KEY (id)
);

CREATE INDEX ON "card" ("id");