-- begin ACCOUNT_PRODUCT
create unique index IDX_ACCOUNT_PRODUCT_UNIQ_NAME on ACCOUNT_PRODUCT (NAME) where DELETE_TS is null ^
-- end ACCOUNT_PRODUCT

create unique index IDX_ACCOUNT_SHOP_UNIQ_NAME on ACCOUNT_SHOP (NAME) where DELETE_TS is null ^


-- begin ACCOUNT_BILL
alter table ACCOUNT_BILL add constraint FK_ACCOUNT_BILL_PRODUCT_ID foreign key (PRODUCT_ID) references ACCOUNT_PRODUCT(ID)^
alter table ACCOUNT_BILL add constraint FK_ACCOUNT_BILL_SHOP_ID foreign key (SHOP_ID) references ACCOUNT_SHOP(ID)^

create index IDX_ACCOUNT_BILL_PRODUCT on ACCOUNT_BILL (PRODUCT_ID)^
create index IDX_ACCOUNT_BILL_SHOP on ACCOUNT_BILL (SHOP_ID)^

-- end ACCOUNT_BILL
