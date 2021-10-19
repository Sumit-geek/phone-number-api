

create EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	created_time timestamp NULL DEFAULT now(),
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE phone_number (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	customer_id varchar NOT NULL,
	phone_no varchar NOT NULL,
	status varchar NULL DEFAULT "active",
	created_time timestamp NULL DEFAULT now(),
	CONSTRAINT phone_number_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX id_idx ON customer USING btree (id);
CREATE UNIQUE INDEX phone_number_id_idx ON phone_number USING btree (id);