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
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Sergey42
 */
public class BillBrowse extends AbstractLookup {

    @Inject
    protected Label currentMonth;
    @Inject
    protected Label currentWeek;
    @Inject
    protected Label previousMonth;
    @Inject
    protected Label previousWeek;
    @Inject
    protected Label screen;
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

        currentMonth.setValue(formatMessage("thisMonth", statistics.getCurrentMonthTotal()));
        currentWeek.setValue(formatMessage("thisWeek", statistics.getCurrentWeekTotal()));
        previousMonth.setValue(formatMessage("prevMonth", statistics.getPreviousMonthTotal()));
        previousWeek.setValue(formatMessage("prevWeek", statistics.getPreviousWeekTotal()));

        long screenSum = billsDs.getItems().stream().map(Bill::getAmount).mapToLong(BigDecimal::longValue).sum();
        screen.setValue(formatMessage("thisScreen", screenSum));
    }
}