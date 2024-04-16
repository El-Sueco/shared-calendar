package at.home.sharedcalendar.config;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Factory
public class AppConfig {

    @Singleton
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new OffsetDateTimeToLocalDateTimeConverter());
        modelMapper.addConverter(new LocalDateTimeToOffsetDateTimeConverter());
        return modelMapper;
    }

    private class OffsetDateTimeToLocalDateTimeConverter implements ConditionalConverter<OffsetDateTime, LocalDateTime> {

        @Override
        public MatchResult match(Class<?> src, Class<?> dest) {
            return OffsetDateTime.class.isAssignableFrom(src) && LocalDateTime.class.isAssignableFrom(dest) ? MatchResult.FULL : MatchResult.NONE;
        }

        @Override
        public LocalDateTime convert(MappingContext<OffsetDateTime, LocalDateTime> mappingContext) {
            if (mappingContext.getSource() == null) {
                return null;
            }
            OffsetDateTime offset = mappingContext.getSource();
            LocalDateTime local = LocalDateTime.ofInstant(offset.toInstant(), ZoneId.systemDefault());
            return local;
        }
    }

    private class LocalDateTimeToOffsetDateTimeConverter implements ConditionalConverter<LocalDateTime, OffsetDateTime> {

        @Override
        public MatchResult match(Class<?> src, Class<?> dest) {
            return LocalDateTime.class.isAssignableFrom(src) && OffsetDateTime.class.isAssignableFrom(dest) ? MatchResult.FULL : MatchResult.NONE;
        }

        @Override
        public OffsetDateTime convert(MappingContext<LocalDateTime, OffsetDateTime> mappingContext) {
            if (mappingContext.getSource() == null) {
                return null;
            }
            LocalDateTime local = mappingContext.getSource();
            OffsetDateTime offset = local.atZone(ZoneId.systemDefault()).toOffsetDateTime();
            return offset;
        }
    }
}
