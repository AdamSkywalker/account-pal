/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author Sergey42
 */
@NamePattern("%s|name")
@Table(name = "ACCOUNT_SHOP")
@Entity(name = "account$Shop")
public class Shop extends StandardEntity {
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "ADDRESS", nullable = false)
    protected String address;

    private static final long serialVersionUID = 4280289287700031994L;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


}