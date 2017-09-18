package com.company.account.service;

import com.company.account.entity.Bill;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at 18/09/2017.
 */
@Service(BillService.NAME)
public class BillServiceBean implements BillService {

    @Inject
    private DataManager dataManager;
    @Inject
    private UserSessionSource userSessionSource;

    @Override
    public BillStatistics calculateStats() {
        LoadContext<Bill> lc = LoadContext.create(Bill.class);
        lc.setQuery(LoadContext.createQuery("select e from account$Bill e where e.createdBy = :authorId")
                .setParameter("authorId", userSessionSource.getUserSession().getUser().getLoginLowerCase())
        );

        List<Bill> bills = dataManager.loadList(lc);

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

        for (Bill bill : bills) {
            if (before(monthStart, bill.getDate())) {
                monthSum = monthSum.add(bill.getAmount());
            }
            if (before(weekStart, bill.getDate())) {
                weekSum = weekSum.add(bill.getAmount());
            }
        }

        BillStatistics statistics = new BillStatistics();
        statistics.setCurrentMonthTotal(monthSum.intValue());
        statistics.setCurrentWeekTotal(weekSum.intValue());

        return statistics;
    }

    private boolean before(Date d1, Date d2) {
        return d1.before(d2) || DateUtils.isSameDay(d1, d2);
    }
}
