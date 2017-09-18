/*
 * Copyright (c) 2015 com.company.account.gui.bill
 */
package com.company.account.gui.bill;

import com.company.account.entity.Bill;
import com.company.account.service.BillService;
import com.company.account.service.BillStatistics;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.UUID;

/**
 * @author Sergey42
 */
public class BillBrowse extends AbstractLookup {

    @Inject
    protected Label month;
    @Inject
    protected Label week;
    @Inject
    protected CollectionDatasource<Bill, UUID> billsDs;
    @Inject
    private BillService billService;

    @Override
    public void ready() {
        super.ready();
        updateStats();

        billsDs.addCollectionChangeListener(e -> updateStats());
    }

    private void updateStats() {
        BillStatistics statistics = billService.calculateStats();

        month.setValue(messages.formatMessage(getClass(), "thisMonth", statistics.getCurrentMonthTotal()));
        week.setValue(messages.formatMessage(getClass(), "thisWeek", statistics.getCurrentWeekTotal()));
    }
}