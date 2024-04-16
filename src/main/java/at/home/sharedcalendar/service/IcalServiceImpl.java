package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.CalendarRepository;
import jakarta.inject.Inject;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

public class IcalServiceImpl implements IcalService {

    @Inject
    private CalendarRepository calendarRepository;

    @Override
    public InputStream getCalendarIcal(String calendarUuid) {
        Calendar calendar = new Calendar();
        calendar.add(new ProdId("-//Shared Calendar//Shared Calendar//EN"));
        calendar.add(ImmutableVersion.VERSION_2_0);
        calendar.add(ImmutableCalScale.GREGORIAN);
        calendar.add(new Uid(calendarUuid));
        calendar.add(new Name("hopefully the name"));
        VEvent vEvent = new VEvent();
        vEvent.add(new Uid(UUID.randomUUID().toString()));
        vEvent.add(new Summary("Test"));
        vEvent.add(new Description("TestDesc"));
        vEvent.add(new DtStart<>(LocalDateTime.now()));
        vEvent.add(new DtEnd<>(LocalDateTime.now().plusDays(2)));
        calendar.add(vEvent);
        return new ByteArrayInputStream(calendar.toString().getBytes());
    }
}
