/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergey42
 */
@NamePattern("%s|name")
@Table(name = "ACCOUNT_PRODUCT")
@Entity(name = "account$Product")
public class Product extends StandardEntity {
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    private static final long serialVersionUID = -1924839225709184642L;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}