CREATE TABLE customer (
    id                      BIGINT PRIMARY KEY,
    first_name              VARCHAR(45),
    middle_name             VARCHAR(45),
    last_name               VARCHAR(45),
    ssn                     VARCHAR(45),
    email_address           VARCHAR(45),
    home_phone              CHAR(10),
    cell_phone              CHAR(10),
    work_phone              CHAR(10),
    notification_preference CHAR(1)
);

CREATE TABLE account (
    id                      BIGINT PRIMARY KEY,
    balance                 FLOAT,
    last_statement_date     TIMESTAMP
);

CREATE TABLE customer_account (
    customer_id             BIGINT,
    account_id              BIGINT,
    PRIMARY KEY (customer_id, account_id),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE transaction (
    id                      BIGINT PRIMARY KEY,
    account_id              BIGINT,
    credit                  FLOAT,
    debit                   FLOAT,
    transaction_date        TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(id)
);