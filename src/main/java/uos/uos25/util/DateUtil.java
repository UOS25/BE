package uos.uos25.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import org.springframework.stereotype.Component;

import uos.uos25.common.BaseEntity;

@Component
public class DateUtil {
    public <T extends BaseEntity> Boolean filterBetweenDate(
            T entity, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime createdAt = entity.getCreatedAt();
        return isDateTimeBetween(createdAt, startDate, endDate);
    }

    public Boolean isDateTimeBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return target.isAfter(start) && target.isBefore(end);
    }

    public LocalDateTime getStartOfMonth(LocalDateTime dateTime) {
        LocalDate firstDayOfMonth = dateTime.toLocalDate().withDayOfMonth(1);

        return LocalDateTime.of(firstDayOfMonth, LocalTime.MIN);
    }

    public LocalDateTime getEndOfMonth(LocalDateTime dateTime) {
        YearMonth yearMonth = YearMonth.from(dateTime);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        return LocalDateTime.of(lastDayOfMonth, LocalTime.MAX);
    }
}
