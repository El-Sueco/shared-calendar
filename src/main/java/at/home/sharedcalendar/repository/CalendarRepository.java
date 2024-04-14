package at.home.sharedcalendar.repository;

import at.home.sharedcalendar.repository.model.Calendar;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, String> {
}
