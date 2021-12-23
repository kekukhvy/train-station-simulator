CREATE TABLE IF NOT EXISTS public.addresses
(
    id bigserial,
    country character varying(255),
    zip_code character varying(255),
    city character varying(255),
    street character varying(255),
    house_no character varying(255),
    flat character varying(255),
    CONSTRAINT address_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.addresses OWNER to dbregio;


CREATE TABLE IF NOT EXISTS public.customers
(
    id bigserial,
    login character varying(255) not null,
    full_name character varying(255),
    discount integer NOT NULL,
    address_id bigint,
    CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT address_fkey FOREIGN KEY (address_id)
    REFERENCES public.addresses (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

ALTER TABLE IF EXISTS public.customers OWNER to dbregio;
