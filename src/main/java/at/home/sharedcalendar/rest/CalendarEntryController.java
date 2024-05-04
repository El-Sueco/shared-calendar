package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.rest.model.CalendarEntry;
import at.home.sharedcalendar.service.CalendarEntryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/api/calendars/{calendarUuid}/entries")
public class CalendarEntryController {

    private static final Logger log = LoggerFactory.getLogger(CalendarEntryController.class);

    @Inject
    private CalendarEntryService calendarEntryService;

    @Get()
    public HttpResponse<?> getCalendarEntries(@PathVariable String calendarUuid) {
        log.info("getCalendarEntries, getting CalendarEntries from Calendar {}", calendarUuid);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarEntryService.getCalendarEntries(calendarUuid));
    }

    @Post()
    public HttpResponse<?> createCalendarEntry(@PathVariable String calendarUuid, @Body CalendarEntry calendarEntry) {
        log.info("createCalendarEntry, creating {} in Calendar {}", calendarEntry, calendarUuid);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarEntryService.createCalendarEntry(calendarUuid, calendarEntry));
    }

    @Get("/{uuid}")
    public HttpResponse<?> getCalendarEntry(@PathVariable String calendarUuid, @PathVariable String uuid) {
        log.info("getCalendarEntry, getting CalendarEntry {} from Calendar {}", uuid, calendarUuid);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarEntryService.getCalendarEntry(calendarUuid, uuid));
    }

    @Put("/{uuid}")
    public HttpResponse<?> updateCalendarEntry(@PathVariable String calendarUuid, @PathVariable String uuid, @Body CalendarEntry calendarEntry) {
        log.info("updateCalendarEntry, updating CalendarEntry {} with {} in Calendar {}", uuid, calendarEntry, calendarUuid);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarEntryService.updateCalendarEntry(calendarUuid, uuid, calendarEntry));
    }

    @Delete("/{uuid}")
    public HttpResponse<?> deleteCalendarEntry(@PathVariable String calendarUuid, @PathVariable String uuid) {
        log.info("deleteCalendarEntry, deleting CalendarEntry {} from Calendar {}", uuid, calendarUuid);
        this.calendarEntryService.deleteCalendarEntry(calendarUuid, uuid);
        return HttpResponse.status(HttpStatus.OK);
    }
}