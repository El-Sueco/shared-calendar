package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.CalendarRepository;
import at.home.sharedcalendar.repository.model.CalendarModel;
import at.home.sharedcalendar.rest.model.Calendar;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

@Singleton
public class CalendarServiceImpl implements CalendarService {

    @Inject
    private CalendarRepository calendarRepository;

    @Inject
    private ModelMapper modelMapper;

    @Override
    public List<Calendar> getCalendars() {
        return modelMapper.map(calendarRepository.findAll(), new TypeToken<List<Calendar>>() {
        }.getType());
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        CalendarModel calendarModel = new CalendarModel();
        calendarModel.setName(calendar.getName());
        calendarModel = calendarRepository.save(calendarModel);
        return modelMapper.map(calendarModel, Calendar.class);
    }

    @Override
    public Calendar updateCalendar(String uuid, Calendar calendar) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(uuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        calendarModel.setName(calendar.getName());
        calendarModel = calendarRepository.merge(calendarModel);
        return modelMapper.map(calendarModel, Calendar.class);
    }


    @Override
    public Calendar getCalendar(String uuid) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(uuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        return modelMapper.map(calendarModel, Calendar.class);
    }

    @Override
    public void deleteCalendar(String uuid) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(uuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        calendarRepository.deleteById(uuid);
    }

}
