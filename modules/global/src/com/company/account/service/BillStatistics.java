package com.company.account.service;

import java.io.Serializable;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at 18/09/2017.
 */
public class BillStatistics implements Serializable {

    private int currentMonthTotal;
    private int currentWeekTotal;
    private int previousMonthTotal;
    private int previousWeekTotal;

    public int getCurrentMonthTotal() {
        return currentMonthTotal;
    }

    public void setCurrentMonthTotal(int currentMonthTotal) {
        this.currentMonthTotal = currentMonthTotal;
    }

    public int getCurrentWeekTotal() {
        return currentWeekTotal;
    }

    public void setCurrentWeekTotal(int currentWeekTotal) {
        this.currentWeekTotal = currentWeekTotal;
    }

    public int getPreviousMonthTotal() {
        return previousMonthTotal;
    }

    public void setPreviousMonthTotal(int previousMonthTotal) {
        this.previousMonthTotal = previousMonthTotal;
    }

    public int getPreviousWeekTotal() {
        return previousWeekTotal;
    }

    public void setPreviousWeekTotal(int previousWeekTotal) {
        this.previousWeekTotal = previousWeekTotal;
    }
}
