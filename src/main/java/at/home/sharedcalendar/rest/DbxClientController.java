package at.home.sharedcalendar.rest;

import at.home.sharedcalendar.service.DbxClientService;
import com.dropbox.core.DbxException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

@Controller("/dropbox")
public class DbxClientController {

    private static final Logger log = LoggerFactory.getLogger(DbxClientController.class);

    @Inject
    private DbxClientService dbxClientService;

    @Get("/auth/start")
    public HttpResponse<?> startOAuthFlow(@Nullable @QueryValue(defaultValue = "false") Boolean browser) throws DbxException {
        return HttpResponse.status(HttpStatus.OK).body(dbxClientService.startOAuthFlow(browser));
    }

    @Get("/auth/finish/{code}")
    public HttpResponse<?> finishOAuthFlow(String code) throws DbxException {
        dbxClientService.finishOAuthFlow(code);
        return HttpResponse.status(HttpStatus.OK);
    }
}
