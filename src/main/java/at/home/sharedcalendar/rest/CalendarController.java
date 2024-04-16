package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.rest.model.Calendar;
import at.home.sharedcalendar.service.CalendarService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/calendars")
public class CalendarController {

    private static final Logger log = LoggerFactory.getLogger(CalendarController.class);

    @Inject
    private CalendarService calendarService;

    @Get()
    public HttpResponse<?> getCalendars() {
        log.info("getCalendars,");
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.getCalendars());
    }

    @Post()
    public HttpResponse<?> createCalendar(@Body Calendar calendar) {
        log.info("createCalendar, creating calendar {}", calendar);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.createCalendar(calendar));
    }

    @Get("/{uuid}")
    public HttpResponse<?> getCalendar(@PathVariable String uuid) {
        log.info("getCalendar, getting calendar {}", uuid);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.getCalendar(uuid));
    }

    @Put("/{uuid}")
    public HttpResponse<?> updateCalendar(@PathVariable String uuid, @Body Calendar calendar) {
        log.info("updateCalendar, updating calendar {} with {}", uuid, calendar);
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.updateCalendar(uuid, calendar));
    }

    @Delete("/{uuid}")
    public HttpResponse<?> deleteCalendar(@PathVariable String uuid) {
        log.info("deleteCalendar, deleting calendar {}", uuid);
        this.calendarService.deleteCalendar(uuid);
        return HttpResponse.status(HttpStatus.OK);
    }

}