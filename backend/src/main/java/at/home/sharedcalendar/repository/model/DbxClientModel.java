package at.home.sharedcalendar.repository.model;


import at.home.sharedcalendar.config.SecretCryptoConverter;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Serdeable
@Table(name = "dbx_clients")
public class DbxClientModel {

    @Id
    @Column(name = "client_key", unique = true, updatable = false, nullable = false)
    private String clientKey;

    @Convert(converter = SecretCryptoConverter.class)
    @Column(name = "client_secret", unique = true, updatable = false, nullable = false)
    private String clientSecret;

    @Convert(converter = SecretCryptoConverter.class)
    @Column(name = "refresh_token", unique = true, updatable = true, nullable = false)
    private String refreshToken;

    @Column(name = "created_at", unique = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", unique = false, updatable = false, nullable = false)
    private LocalDateTime updatedAt;

    public DbxClientModel(String clientKey, String clientSecret, String refreshToken) {
        this.clientKey = clientKey;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
    }

    public DbxClientModel() {
    }

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
