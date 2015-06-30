-- begin ACCOUNT_CATEGORY
create table ACCOUNT_CATEGORY (
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
-- end ACCOUNT_CATEGORY
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
    CATEGORY_ID uuid not null,
    --
    primary key (ID)
)^
-- end ACCOUNT_PRODUCT
-- begin ACCOUNT_SHOP
create table ACCOUNT_SHOP (
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
    ADDRESS varchar(255) not null,
    --
    primary key (ID)
)^
-- end ACCOUNT_SHOP
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
    SHOP_ID uuid not null,
    --
    primary key (ID)
)^
-- end ACCOUNT_BILL
-- begin ACCOUNT_BILL_ITEM
create table ACCOUNT_BILL_ITEM (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BILL_ID uuid not null,
    PRODUCT_ID uuid not null,
    BRAND_ID uuid,
    DESCRIPTION varchar(255),
    PRICE decimal(19, 2) not null,
    COUNT_ decimal(19, 2) not null,
    PRICE_PER_ITEM decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end ACCOUNT_BILL_ITEM
-- begin ACCOUNT_BRAND
create table ACCOUNT_BRAND (
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
-- end ACCOUNT_BRAND
