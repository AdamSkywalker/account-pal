/*
 * Copyright (c) 2015 com.company.account.gui.bill
 */
package com.company.account.gui.bill;

import com.company.account.entity.BillItem;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.account.entity.Bill;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.CollectionDatasourceListener;
import com.haulmont.cuba.gui.data.impl.CollectionDsListenerAdapter;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * @author Sergey42
 */
public class BillEditor extends AbstractEditor<Bill> {

    @Inject
    protected CollectionDatasource<BillItem, UUID> itemsDs;

    @Override
    protected void postInit() {
        itemsDs.addListener(new CollectionDsListenerAdapter<BillItem>() {
            @Override
            public void collectionChanged(CollectionDatasource ds, Operation operation, List<BillItem> items) {
                getItem().recalculateAmount();
            }
        });
    }
}