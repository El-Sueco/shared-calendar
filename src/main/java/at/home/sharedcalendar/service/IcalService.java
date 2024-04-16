package at.home.sharedcalendar.service;

import java.io.InputStream;

public interface IcalService {

    InputStream getCalendarIcal(String calendarUuid);
}
