/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.account.gui.bill;

import com.haulmont.cuba.gui.components.Formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at: 2/4/17.
 */
public class DateFormatter implements Formatter<Date> {

    @Override
    public String format(Date value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy EEE", Locale.forLanguageTag("ru"));
        return sdf.format(value);
    }
}
