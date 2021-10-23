

create EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customers (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	created_time timestamp NULL DEFAULT now(),
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE phone_numbers (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	customer_id uuid NOT NULL,
	phone_no varchar NOT NULL,
	status varchar DEFAULT 'Active',
	created_time timestamp NULL DEFAULT now(),
	CONSTRAINT phone_number_pkey PRIMARY KEY (id),
	CONSTRAINT phone_number_fkey FOREIGN KEY (customer_id) REFERENCES customers(id)
);
CREATE UNIQUE INDEX id_idx ON customers USING btree (id);
CREATE UNIQUE INDEX phone_number_id_idx ON phone_numbers USING btree (id);