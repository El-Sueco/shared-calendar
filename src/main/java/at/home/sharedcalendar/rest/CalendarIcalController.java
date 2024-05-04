package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.service.CalendarIcalService;
import com.dropbox.core.DbxException;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/calendars/{calendarUuid}/icals")
public class CalendarIcalController {

    private static final Logger log = LoggerFactory.getLogger(CalendarIcalController.class);

    @Inject
    private CalendarIcalService icalService;

    @Get()
    public StreamedFile createCalendarIcal(@PathVariable String calendarUuid) throws DbxException {
        log.info("createCalendarIcal, creating Ical of Calendar {}", calendarUuid);
        return new StreamedFile(icalService.createCalendarIcal(calendarUuid), MediaType.TEXT_PLAIN_TYPE);
    }
}
