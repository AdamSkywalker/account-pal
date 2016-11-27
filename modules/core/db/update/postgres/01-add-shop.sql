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
    --
    primary key (ID)
);

alter table ACCOUNT_BILL add SHOP_ID uuid;
alter table ACCOUNT_BILL add constraint FK_ACCOUNT_BILL_SHOP_ID foreign key (SHOP_ID) references ACCOUNT_SHOP(ID);
create index IDX_ACCOUNT_BILL_SHOP on ACCOUNT_BILL (SHOP_ID);