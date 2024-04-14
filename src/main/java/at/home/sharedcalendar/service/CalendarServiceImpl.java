package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.CalendarRepository;
import at.home.sharedcalendar.repository.model.Calendar;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class CalendarServiceImpl implements CalendarService {

    @Inject
    private CalendarRepository calendarRepository;

    @Override
    public List<Calendar> getCalendars() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
}
