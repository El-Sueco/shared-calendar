package at.home.sharedcalendar.repository;

import at.home.sharedcalendar.repository.model.CalendarEntryModel;
import at.home.sharedcalendar.repository.model.CalendarModel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntryModel, String> {
    List<CalendarEntryModel> findAllByCalendar(CalendarModel calendarModel);

    Optional<CalendarEntryModel> findByIdAndCalendar(String uuid, CalendarModel calendarModel);
}
