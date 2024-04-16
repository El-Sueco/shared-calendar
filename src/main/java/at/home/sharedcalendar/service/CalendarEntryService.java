package at.home.sharedcalendar.service;

import at.home.sharedcalendar.rest.model.CalendarEntry;

import java.util.List;

public interface CalendarEntryService {
    List<CalendarEntry> getCalendarEntries(String calendarUuid);

    CalendarEntry createCalendarEntry(String calendarUuid, CalendarEntry calendarEntry);

    CalendarEntry updateCalendarEntry(String calendarUuid, String uuid, CalendarEntry calendarEntry);

    CalendarEntry getCalendarEntry(String calendarUuid, String uuid);

    void deleteCalendarEntry(String calendarUuid, String uuid);
}
