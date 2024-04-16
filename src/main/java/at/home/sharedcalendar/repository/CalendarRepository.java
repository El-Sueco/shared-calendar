package at.home.sharedcalendar.repository;

import at.home.sharedcalendar.repository.model.CalendarModel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarModel, String> {
}
