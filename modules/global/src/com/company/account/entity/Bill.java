/*
 * Copyright (c) 2015 com.company.account.entity
 */
package com.company.account.entity;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.Metadata;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 * @author Sergey42
 */
@NamePattern("#getInstName|shop,date")
@Table(name = "ACCOUNT_BILL")
@Entity(name = "account$Bill")
public class Bill extends StandardEntity {

    @PostConstruct
    protected void init() {
        setItems(new HashSet<BillItem>());
    }

    @Column(name = "AMOUNT", nullable = false)
    protected BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHOP_ID")
    protected Shop shop;

    private static final long serialVersionUID = -4700688709890728340L;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "bill")
    protected Set<BillItem> items;
    
    public String getInstName() {
         return shop.getName() + " - " + new SimpleDateFormat("dd.MM.yyyy").format(getDate());
    }

    public void recalculateAmount() {
        BigDecimal total = new BigDecimal(0);
        for (BillItem item : items) {
            total = total.add(item.getPrice());
        }
        setAmount(total);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setItems(Set<BillItem> items) {
        this.items = items;
    }

    public Set<BillItem> getItems() {
        return items;
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

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }


    public Bill copy() {
        Bill bill = AppBeans.get(Metadata.class).create(Bill.class);
        bill.setDate(getDate());
        bill.setShop(getShop());

        for (BillItem item : items) {
           item.copy(bill);
        }
        bill.recalculateAmount();
        return bill;
    }
}