package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.CalendarEntryRepository;
import at.home.sharedcalendar.repository.CalendarRepository;
import at.home.sharedcalendar.repository.model.CalendarEntryModel;
import at.home.sharedcalendar.repository.model.CalendarModel;
import com.dropbox.core.DbxException;
import jakarta.inject.Inject;
import net.fortuna.ical4j.extensions.property.WrCalName;
import net.fortuna.ical4j.extensions.property.WrTimezone;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;
import net.fortuna.ical4j.util.TimeZones;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarIcalServiceImpl implements CalendarIcalService {

    @Inject
    private CalendarRepository calendarRepository;

    @Inject
    private CalendarEntryRepository calendarEntryRepository;

    @Inject
    private DbxClientServiceImpl dbxClientService;

    @Override
    public InputStream createCalendarIcal(String calendarUuid) throws UnsupportedOperationException, DbxException {
        CalendarModel calendarModel = calendarRepository.findById(calendarUuid).orElseThrow(() -> new UnsupportedOperationException("exit"));
        List<CalendarEntryModel> calendarEntryModelList = calendarEntryRepository.findAllByCalendar(calendarModel);

        Calendar calendar = new Calendar();
        calendar.add(ImmutableVersion.VERSION_2_0);
        calendar.add(new ProdId("-//AT HOME//Shared Calendar//EN"));
        calendar.add(ImmutableCalScale.GREGORIAN);
        calendar.add(new Uid(calendarModel.getUuid()));
        calendar.add(new Name(calendarModel.getName()));
        calendar.add(new WrCalName.Factory().createProperty(calendarModel.getName()));
        calendar.add(new WrTimezone.Factory().createProperty(TimeZones.UTC_ID));
        java.time.Duration duration = java.time.Duration.ofMinutes(10);
        calendar.add(new XProperty("X-PUBLISHED-TTL", duration.toString()));
        calendar.add(new XProperty("REFRESH-INTERVAL;VALUE=DURATION", duration.toString()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm00'Z'");
        for (CalendarEntryModel calendarEntryModel : calendarEntryModelList) {
            VEvent vEvent = new VEvent();
            vEvent.add(new Uid(calendarEntryModel.getUuid()));
            vEvent.add(new Summary(calendarEntryModel.getName()));
            vEvent.add(new Description(calendarEntryModel.getDescription()));
            vEvent.add(new XProperty("DTSTART;VALUE=DATE-TIME", calendarEntryModel.getStartDateTime().atZone(ZoneOffset.UTC).format(dtf)));
            vEvent.add(new XProperty("DTEND;VALUE=DATE-TIME", calendarEntryModel.getEndDateTime().atZone(ZoneOffset.UTC).format(dtf)));
            //TODO value DATe for duration
            calendar.add(vEvent);
        }
        InputStream icalInputStream = new ByteArrayInputStream(calendar.toString().getBytes());
        dbxClientService.uploadFile(calendarModel.getUuid().replaceAll("-", ""), icalInputStream);
        return icalInputStream;
    }
}
