CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user
(
    id        serial NOT NULL,
    lastname  character varying(255),
    firstname character varying(255),
    email     character varying(255),
    password  character varying(255),
    money     bigint,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE INDEX ON "user" ("id");
