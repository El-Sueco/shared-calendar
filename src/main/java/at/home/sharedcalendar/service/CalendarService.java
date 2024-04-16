package at.home.sharedcalendar.service;

import at.home.sharedcalendar.rest.model.Calendar;

import java.util.List;

public interface CalendarService {

    List<Calendar> getCalendars();

    Calendar createCalendar(Calendar calendar);

    Calendar updateCalendar(String uuid, Calendar calendar);

    Calendar getCalendar(String uuid);

    void deleteCalendar(String uuid);
}
