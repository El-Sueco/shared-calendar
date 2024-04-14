package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.model.Calendar;

import java.util.List;

public interface CalendarService {
    List<Calendar> getCalendars();
    Calendar createCalendar(Calendar calendar);
}
