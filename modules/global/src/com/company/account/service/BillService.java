package com.company.account.service;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at 18/09/2017.
 */
public interface BillService {

    String NAME = "account_BillService";

    BillStatistics calculateStats();
}