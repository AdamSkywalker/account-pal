/*
 * Copyright (c) 2015 com.company.account.gui.billitem
 */
package com.company.account.gui.billitem;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.account.entity.BillItem;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.impl.DsListenerAdapter;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * @author Sergey42
 */
public class BillItemEdit extends AbstractEditor<BillItem> {

    @Inject
    protected Datasource<BillItem> billItemDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        billItemDs.addListener(new DsListenerAdapter<BillItem>() {
            @Override
            public void valueChanged(BillItem source, String property, @Nullable Object prevValue, @Nullable Object value) {
                if ("count".equals(property)) {
                    BigDecimal price = source.getPrice();
                    BigDecimal count = (BigDecimal) value;
                    if (count != null && !BigDecimal.ZERO.equals(count)) {
                        source.setPricePerItem(price.divide(count, 2, RoundingMode.HALF_UP));
                    }
                }
            }
        });
    }
}