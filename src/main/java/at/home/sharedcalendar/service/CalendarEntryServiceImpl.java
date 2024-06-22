package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.CalendarEntryRepository;
import at.home.sharedcalendar.repository.CalendarRepository;
import at.home.sharedcalendar.repository.model.CalendarEntryModel;
import at.home.sharedcalendar.repository.model.CalendarModel;
import at.home.sharedcalendar.rest.model.CalendarEntry;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;


@Singleton
public class CalendarEntryServiceImpl implements CalendarEntryService {

    @Inject
    private CalendarRepository calendarRepository;

    @Inject
    private CalendarEntryRepository calendarEntryRepository;

    @Inject
    private ModelMapper modelMapper;

    @Override
    public List<CalendarEntry> getCalendarEntries(String calendarUuid) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(calendarUuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + calendarUuid + " does not exist");
        }
        return modelMapper.map(calendarEntryRepository.findAllByCalendar(optionalCalendarModel.get()), new TypeToken<List<CalendarEntry>>() {
        }.getType());
    }

    @Override
    public CalendarEntry createCalendarEntry(String calendarUuid, CalendarEntry calendarEntry) {
        if (!calendarUuid.equals(calendarEntry.getCalendarUuid())) {
            throw new UnsupportedOperationException("calendarUuid " + calendarUuid + " and request uuid " + calendarEntry.getCalendarUuid() + " did not match");
        }
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(calendarUuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + calendarUuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        CalendarEntryModel calendarEntryModel = new CalendarEntryModel();
        calendarEntryModel.setName(calendarEntry.getName());
        calendarEntryModel.setDescription(calendarEntry.getDescription());
        calendarEntryModel.setStartDateTime(LocalDateTime.ofInstant(calendarEntry.getStartDateTime().toInstant(), ZoneOffset.UTC));
        calendarEntryModel.setEndDateTime(LocalDateTime.ofInstant(calendarEntry.getEndDateTime().toInstant(), ZoneOffset.UTC));
        calendarEntryModel.setAllDay(calendarEntry.getAllDay());
        calendarEntryModel.setCalendar(calendarModel);
        calendarEntryModel = calendarEntryRepository.save(calendarEntryModel);
        return modelMapper.map(calendarEntryModel, CalendarEntry.class);
    }

    @Override
    public CalendarEntry updateCalendarEntry(String calendarUuid, String uuid, CalendarEntry calendarEntry) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(calendarUuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + calendarUuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        Optional<CalendarEntryModel> optionalCalendarEntryModel = calendarEntryRepository.findByIdAndCalendar(uuid, calendarModel);
        if (optionalCalendarEntryModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        CalendarEntryModel calendarEntryModel = optionalCalendarEntryModel.get();
        calendarEntryModel.setName(calendarEntry.getName());
        calendarEntryModel.setDescription(calendarEntry.getDescription());
        calendarEntryModel.setStartDateTime(LocalDateTime.ofInstant(calendarEntry.getStartDateTime().toInstant(), ZoneOffset.UTC));
        calendarEntryModel.setEndDateTime(LocalDateTime.ofInstant(calendarEntry.getEndDateTime().toInstant(), ZoneOffset.UTC));
        calendarEntryModel.setAllDay(calendarEntry.getAllDay());
        calendarEntryModel.setCalendar(calendarModel);
        calendarEntryModel = calendarEntryRepository.merge(calendarEntryModel);
        return modelMapper.map(calendarEntryModel, CalendarEntry.class);
    }

    @Override
    public CalendarEntry getCalendarEntry(String calendarUuid, String uuid) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(calendarUuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + calendarUuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        Optional<CalendarEntryModel> optionalCalendarEntryModel = calendarEntryRepository.findByIdAndCalendar(uuid, calendarModel);
        if (optionalCalendarEntryModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        return modelMapper.map(optionalCalendarEntryModel.get(), CalendarEntry.class);
    }

    @Override
    public void deleteCalendarEntry(String calendarUuid, String uuid) {
        Optional<CalendarModel> optionalCalendarModel = calendarRepository.findById(calendarUuid);
        if (optionalCalendarModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + calendarUuid + " does not exist");
        }
        CalendarModel calendarModel = optionalCalendarModel.get();
        Optional<CalendarEntryModel> optionalCalendarEntryModel = calendarEntryRepository.findByIdAndCalendar(uuid, calendarModel);
        if (optionalCalendarEntryModel.isEmpty()) {
            throw new UnsupportedOperationException("uuid " + uuid + " does not exist");
        }
        calendarEntryRepository.deleteById(uuid);
    }
}
