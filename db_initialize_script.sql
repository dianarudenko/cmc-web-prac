CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    phone_number NUMERIC(11, 0),
    personal_info json,
    bill bigint --REFERENCES bills(number)
);

CREATE TABLE bills (
    number bigint PRIMARY KEY,
    client_id integer REFERENCES clients(id) ON DELETE CASCADE,
    funds money,
    active boolean
);

CREATE TABLE services (
    id SERIAL PRIMARY KEY,
    name text,
    description text,
    cost money,
    active boolean,
    creation_date date,
    calls_min integer CHECK (calls_min >= 0),
    internet_gb integer CHECK (internet_gb >= 0),
    sms_number integer CHECK (sms_number >= 0)
);

CREATE TYPE trans_type AS ENUM ('incoming', 'outgoing');

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    bill bigint REFERENCES bills(number),
    type trans_type,
    date timestamp,
    sum money
);

CREATE TABLE services_clients (
    client_id integer REFERENCES clients(id),
    service_id integer REFERENCES services(id),
    start_date date,
    end_date date
);

INSERT INTO clients (phone_number, personal_info, bill) VALUES
(   89055907863,
    '{"surname": "Иванов", "name": "Иван", "middlename": "Иванович"}',
    10007856001
),
(   89257003401,
    '{"surname": "Пирогов", "name": "Алексей", "middlename": "Николаевич"}',
    10007856002
),
(   89184872672,
    '{"surname": "Дубенко", "name": "Марина", "middlename": "Дмитриевна"}',
    10007856003
),
(   89067773214,
    '{"surname": "Кулакова", "name": "Александра", "middlename": "Николаевна"}',
    10007856004
),
(   89254895732,
    '{"surname": "Смирнов", "name": "Михаил", "middlename": "Валерьевич"}',
    10007856005
);

INSERT INTO bills (number, funds, active) VALUES
(   10007856001,
    583.00,
    true
),
(   10007856002,
    1000.00,
    true
),
(   10007856003,
    243.00,
    true
),
(   10007856004,
    771.80,
    true
),
(   10007856005,
    0.00,
    false
);

ALTER TABLE clients ADD FOREIGN KEY (bill) REFERENCES bills(number);

UPDATE bills SET client_id = clients.id FROM clients WHERE clients.bill = number;

INSERT INTO services (name, description, cost, active, creation_date, calls_min, internet_gb, sms_number) VALUES
(   'Тариф "Малый"',
    'Небольшие пакеты минут, интернета и смс для тех, кто редко пользуется телефоном',
    250.00,
 	true,
    '01.03.2020',
    100,
    2,
    50
),
(   'Тариф "Средний"',
    'Средние пакеты минут, интернета и смс для тех, кто привык быть всегда на связи, но не хочет переплачивать',
    400.00,
 	true,
    '01.03.2020',
    300,
    5,
    300
),
(   'Тариф "Максимальный"',
    'Максимальные пакеты минут, интернета и смс для тех, кто ни в чем не привык себе отказывать, особенно когда дело касается связи',
    700.00,
 	true,
    '01.03.2020',
    700,
    15,
    500
);

INSERT INTO transactions (bill, type, date, sum) VALUES
(   10007856001,
    'incoming',
   ' 14.04.2020',
    500.00
),
(   10007856003,
    'incoming',
    '10.10.2020',
    400.00
),
(   10007856004,
    'outgoing',
    '23.10.2020',
    700.00
),
(   10007856002,
    'incoming',
    '02.02.2021',
    300.00
);

INSERT INTO services_clients (client_id, service_id, start_date, end_date) VALUES
(   1,
    2,
    '14.04.2020',
    '14.05.2020'
),
(   3,
    1,
    '10.10.2020',
    '10.11.2020'
),
(   4,
    3,
    '23.10.2020',
    '23.11.2020'
),
(   2,
    1,
    '02.02.2021',
    '02.03.2021'
);
