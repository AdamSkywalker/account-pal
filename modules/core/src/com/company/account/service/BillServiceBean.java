package com.company.account.service;

import com.company.account.entity.Bill;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at 18/09/2017.
 */
@Service(BillService.NAME)
public class BillServiceBean implements BillService {

    public static void main(String[] args) {
        // tests
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy EEEE");

        System.out.println("today: " + df.format(new Date()));
        System.out.println("start week: " + df.format(getWeekStart()));
        System.out.println("start month: " + df.format(getMonthStart()));
        System.out.println("prev week" + df.format(getPrevWeekStart()));
        System.out.println("prev month" + df.format(getPrevMonthStart()));
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private UserSessionSource userSessionSource;

    @Override
    public BillStatistics calculateStats() {
        LoadContext<Bill> lc = LoadContext.create(Bill.class);
        lc.setQuery(LoadContext.createQuery("select e from account$Bill e"));

        List<Bill> bills = dataManager.loadList(lc);

        BigDecimal weekSum = new BigDecimal(0);
        BigDecimal monthSum = new BigDecimal(0);
        BigDecimal prevWeekSum = new BigDecimal(0);
        BigDecimal prevMonthSum = new BigDecimal(0);

        Date monthStart = getMonthStart();
        Date weekStart = getWeekStart();

        Date prevMonthStart = getPrevMonthStart();
        Date prevWeekStart = getPrevWeekStart();

        for (Bill bill : bills) {
            if (before(monthStart, bill.getDate())) {
                monthSum = monthSum.add(bill.getAmount());
            }
            if (before(weekStart, bill.getDate())) {
                weekSum = weekSum.add(bill.getAmount());
            }
            if (before(prevMonthStart, bill.getDate()) && monthStart.after(bill.getDate())) {
                prevMonthSum = prevMonthSum.add(bill.getAmount());
            }
            if (before(prevWeekStart, bill.getDate()) && weekStart.after(bill.getDate())) {
                prevWeekSum = prevWeekSum.add(bill.getAmount());
            }
        }

        BillStatistics statistics = new BillStatistics();
        statistics.setCurrentMonthTotal(monthSum.intValue());
        statistics.setCurrentWeekTotal(weekSum.intValue());
        statistics.setPreviousMonthTotal(prevMonthSum.intValue());
        statistics.setPreviousWeekTotal(prevWeekSum.intValue());

        return statistics;
    }

    private boolean before(Date d1, Date d2) {
        return d1.before(d2) || DateUtils.isSameDay(d1, d2);
    }

    private static Date getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    private static Date getWeekStart() {
        Calendar c2 = Calendar.getInstance();
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, c2.getFirstDayOfWeek());
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        return c2.getTime();
    }

    private static Date getPrevMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        int month = calendar.get(Calendar.MONTH);
        if (month > Calendar.JANUARY) {
            calendar.set(Calendar.MONTH, month - 1);
        } else {
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        }

        return calendar.getTime();
    }

    private static Date getPrevWeekStart() {
        return DateUtils.addDays(getWeekStart(), -7);
    }
}
