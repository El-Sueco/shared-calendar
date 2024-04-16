package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.service.IcalService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/icals")
public class IcalController {

    private static final Logger log = LoggerFactory.getLogger(IcalController.class);

    @Inject
    private IcalService icalService;

    @Get("/{calendarUuid}")
    public StreamedFile getCalendarIcal(@PathVariable String calendarUuid) {
        log.info("getCalendarIcal, getting Ical of Calendar {}", calendarUuid);
        return new StreamedFile(icalService.getCalendarIcal(calendarUuid), MediaType.TEXT_PLAIN_TYPE);
    }
}
