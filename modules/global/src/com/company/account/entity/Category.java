/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author Sergey42
 */
@NamePattern("%s|name")
@Table(name = "ACCOUNT_CATEGORY")
@Entity(name = "account$Category")
public class Category extends StandardEntity {
    private static final long serialVersionUID = -3449511733663675366L;

    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}