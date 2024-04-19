package at.home.sharedcalendar.repository;

import at.home.sharedcalendar.repository.model.DbxClientModel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface DbxClientRepository extends JpaRepository<DbxClientModel, String> {
    Optional<DbxClientModel> findByClientKeyAndClientSecret(String clientKey, String clientSecret);
}
