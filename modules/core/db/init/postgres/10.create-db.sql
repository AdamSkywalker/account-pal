
-- begin ACCOUNT_PRODUCT
create table ACCOUNT_PRODUCT (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ACCOUNT_PRODUCT

-- begin ACCOUNT_BILL
create table ACCOUNT_BILL (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    AMOUNT decimal(19, 2) not null,
    DATE_ date not null,
    PRODUCT_ID uuid not null,
    DESCRIPTION varchar(256),
    --
    primary key (ID)
)^
-- end ACCOUNT_BILL
