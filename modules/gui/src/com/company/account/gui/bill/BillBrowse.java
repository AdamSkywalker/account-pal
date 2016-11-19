/*
 * Copyright (c) 2015 com.company.account.gui.bill
 */
package com.company.account.gui.bill;

import com.company.account.entity.Bill;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.impl.CollectionDsListenerAdapter;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    @Override
    public void ready() {
        super.ready();
        updateStats();

        billsDs.addListener(new CollectionDsListenerAdapter<Bill>() {
            @Override
            public void collectionChanged(CollectionDatasource ds, Operation operation, List<Bill> items) {
                super.collectionChanged(ds, operation, items);
                updateStats();
            }
        });
    }

    private void updateStats() {
        BigDecimal weekSum = new BigDecimal(0);
        BigDecimal monthSum = new BigDecimal(0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = calendar.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, c2.getFirstDayOfWeek());
        Date weekStart = c2.getTime();

        for (Bill bill : billsDs.getItems()) {
            if (monthStart.before(bill.getDate())) {
                monthSum = monthSum.add(bill.getAmount());
            }
            if (weekStart.before(bill.getDate())) {
                weekSum = weekSum.add(bill.getAmount());
            }
        }

        month.setValue("This month: " + monthSum.toString());
        week.setValue("This week: " + weekSum.toString());
    }
}