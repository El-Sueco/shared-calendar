package at.home.sharedcalendar.repository;

import at.home.sharedcalendar.repository.model.Entry;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, String> {
}
