/*
 * Copyright (c) 2015 com.company.account.gui.bill
 */
package com.company.account.gui.bill;

import com.company.account.entity.Bill;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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

        billsDs.addCollectionChangeListener(e -> updateStats());
    }

    private void updateStats() {
        BigDecimal weekSum = new BigDecimal(0);
        BigDecimal monthSum = new BigDecimal(0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date monthStart = calendar.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, c2.getFirstDayOfWeek());
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        Date weekStart = c2.getTime();

        for (Bill bill : billsDs.getItems()) {
            if (before(monthStart, bill.getDate())) {
                monthSum = monthSum.add(bill.getAmount());
            }
            if (before(weekStart, bill.getDate())) {
                weekSum = weekSum.add(bill.getAmount());
            }
        }

        month.setValue(messages.formatMessage(getClass(), "thisMonth", monthSum.toString()));
        week.setValue(messages.formatMessage(getClass(), "thisWeek", weekSum.toString()));
    }

    private boolean before(Date d1, Date d2) {
        return d1.before(d2) || DateUtils.isSameDay(d1, d2);
    }
}