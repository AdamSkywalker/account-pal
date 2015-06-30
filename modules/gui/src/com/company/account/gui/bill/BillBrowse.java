/*
 * Copyright (c) 2015 com.company.account.gui.bill
 */
package com.company.account.gui.bill;

import com.company.account.entity.Bill;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Sergey42
 */
public class BillBrowse extends AbstractLookup {

    @Inject
    protected Table billsTable;

    public void copy() {
        Bill bill = billsTable.getSingleSelected();
        if (bill == null) throw new IllegalStateException("Bill cant be null");

        Bill copy = bill.copy();
        Window window = openEditor("account$Bill.edit", copy, WindowManager.OpenType.DIALOG);
        window.addListener(new CloseListener() {
            @Override
            public void windowClosed(String actionId) {
                billsTable.refresh();
            }
        });
    }
}