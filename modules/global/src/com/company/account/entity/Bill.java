/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sergey42
 */
@NamePattern("#getInstName|date")
@Table(name = "ACCOUNT_BILL")
@Entity(name = "account$Bill")
public class Bill extends StandardEntity {

    @PostConstruct
    protected void init() {
        setDate(new Date());
    }

    @Column(name = "AMOUNT", nullable = false)
    protected BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @Column(name = "DESCRIPTION")
    protected String description;

    private static final long serialVersionUID = -4700688709890728340L;

    public String getInstName() {
        return new SimpleDateFormat("dd.MM.yyyy").format(getDate());
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}