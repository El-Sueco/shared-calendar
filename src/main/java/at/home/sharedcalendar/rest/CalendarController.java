package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.repository.model.Calendar;
import at.home.sharedcalendar.service.CalendarService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;


@Controller
public class CalendarController {

    @Inject
    private CalendarService calendarService;

    @Get()
    public HttpResponse<?> getCalendars() {
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.getCalendars());
    }

    @Post()
    public HttpResponse<?> createCalendars(@Body Calendar calendar) {
        return HttpResponse.status(HttpStatus.OK).body(this.calendarService.createCalendar(calendar));
    }
}