/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author Sergey42
 */
@NamePattern("%s|name")
@Table(name = "ACCOUNT_PRODUCT")
@Entity(name = "account$Product")
public class Product extends StandardEntity {
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    private static final long serialVersionUID = -1924839225709184642L;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }


}