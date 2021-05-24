CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(20) UNIQUE,
    name text,
    surname text,
    middle_name text,
    bill bigint --REFERENCES bills(number)
);

CREATE TABLE IF NOT EXISTS bills (
    id BIGSERIAL PRIMARY KEY,
    client_id integer REFERENCES clients(id),
    funds numeric,
    active boolean
);

CREATE TABLE IF NOT EXISTS services (
    id SERIAL PRIMARY KEY,
    name text UNIQUE,
    description text,
    cost numeric,
    active boolean,
    creation_date date,
    calls_min integer CHECK (calls_min >= 0),
    internet_gb integer CHECK (internet_gb >= 0),
    sms_number integer CHECK (sms_number >= 0)
);

CREATE TYPE trans_type AS ENUM ('incoming', 'outgoing');

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    bill bigint REFERENCES bills(id),
    type trans_type,
    date timestamp,
    sum numeric
);

CREATE TABLE IF NOT EXISTS services_clients (
    id SERIAL PRIMARY KEY,
    client_id integer REFERENCES clients(id),
    service_id integer REFERENCES services(id),
    start_date date,
    end_date date
);
