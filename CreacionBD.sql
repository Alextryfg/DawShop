
CREATE USER alex WITH PASSWORD '12345';

GRANT ALL PRIVILEGES ON DATABASE "tiendaDAW" to alex;


CREATE TABLE IF NOT EXISTS public.pedidos
(
    correouser character varying COLLATE pg_catalog."default" NOT NULL,
    preciototal character varying COLLATE pg_catalog."default",
    identificador numeric NOT NULL,
    CONSTRAINT primaria2 PRIMARY KEY (identificador),
    CONSTRAINT fk1 FOREIGN KEY (correouser)
        REFERENCES public.usuarios (direccioncorreo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pedidos
    OWNER to alex;

CREATE INDEX IF NOT EXISTS fki_fk1
    ON public.pedidos USING btree
    (correouser COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.usuarios
(
    nombre character varying COLLATE pg_catalog."default" NOT NULL,
    direccioncorreo character varying COLLATE pg_catalog."default" NOT NULL,
    numerotarjeta character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    tipotarjeta character varying COLLATE pg_catalog."default",
    CONSTRAINT primaria PRIMARY KEY (direccioncorreo)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuarios
    OWNER to alex;