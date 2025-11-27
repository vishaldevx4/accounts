Create  table if not exists customer (
    customer_id long auto_increment primary key,
    customer_name varchar(50) not null unique,
    email varchar(100) not null unique,
    mobile_number varchar(15) not null unique,
    created_at timestamp default current_timestamp,
    created_by varchar(50) null,
    updated_at timestamp default current_timestamp on update current_timestamp,
    updated_by varchar(50) null
);


Create table if not exists accounts (
    customer_id int not null,
    account_type varchar(50) not null,
    account_number long primary key,
    branch_address varchar(255) not null,
    created_at timestamp default current_timestamp,
    created_by varchar(50) null,
    updated_at timestamp default current_timestamp on update current_timestamp,
    updated_by varchar(50) null
);