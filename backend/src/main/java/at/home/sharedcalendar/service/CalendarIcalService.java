package at.home.sharedcalendar.service;

import com.dropbox.core.DbxException;

import java.io.InputStream;

public interface CalendarIcalService {

    InputStream createCalendarIcal(String calendarUuid) throws DbxException;
}
