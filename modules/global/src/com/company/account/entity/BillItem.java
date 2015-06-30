/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.Metadata;

/**
 * @author Sergey42
 */
@NamePattern("%s|product")
@Table(name = "ACCOUNT_BILL_ITEM")
@Entity(name = "account$BillItem")
public class BillItem extends StandardEntity {

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BILL_ID")
    protected Bill bill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    protected Brand brand;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "PRICE", nullable = false)
    protected BigDecimal price;

    @Column(name = "COUNT_", nullable = false)
    protected BigDecimal count;

    @Column(name = "PRICE_PER_ITEM", nullable = false)
    protected BigDecimal pricePerItem;

    private static final long serialVersionUID = 8200268540056739109L;

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }


    public BillItem copy(Bill bill) {
        BillItem copy = AppBeans.get(Metadata.class).create(BillItem.class);
        copy.setCount(getCount());
        copy.setPrice(getPrice());
        copy.setDescription(getDescription());
        copy.setProduct(getProduct());
        copy.setPricePerItem(getPricePerItem());
        copy.setBill(bill);
        bill.getItems().add(copy);
        return copy;
    }
}