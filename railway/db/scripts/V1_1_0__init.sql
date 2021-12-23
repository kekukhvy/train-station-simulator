CREATE TABLE IF NOT EXISTS public.stations
(
    id bigserial,
    city character varying(255),
    name character varying(255),
    CONSTRAINT stations_pkey PRIMARY KEY (id),
    UNIQUE (name, city)
);

ALTER TABLE IF EXISTS public.stations
    OWNER to railway;

CREATE TABLE IF NOT EXISTS public.connections
(
    id bigserial,
    stationa_id bigint,
    stationb_id bigint,
    average_speed real NOT NULL,
    distance integer NOT NULL,

    CONSTRAINT connections_pkey PRIMARY KEY (id),
    CONSTRAINT stationa_FK FOREIGN KEY (stationa_id)
        REFERENCES public.stations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT stationb_FK FOREIGN KEY (stationb_id)
        REFERENCES public.stations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    UNIQUE (stationa_id, stationb_id)
);

ALTER TABLE IF EXISTS public.connections
    OWNER to railway;

CREATE TABLE IF NOT EXISTS public.routes
(
    id bigserial,
    name character varying(255),
    route_number character varying(255) UNIQUE,
    CONSTRAINT routes_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.routes
    OWNER to railway;


CREATE TABLE IF NOT EXISTS public.routes_connections
(
    route_id bigint NOT NULL,
    connections_id bigint NOT NULL,
    CONSTRAINT connections_uk UNIQUE (connections_id),
    CONSTRAINT connections_fk FOREIGN KEY (connections_id)
        REFERENCES public.connections (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT route_fk FOREIGN KEY (route_id)
        REFERENCES public.routes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.routes_connections
    OWNER to railway;
