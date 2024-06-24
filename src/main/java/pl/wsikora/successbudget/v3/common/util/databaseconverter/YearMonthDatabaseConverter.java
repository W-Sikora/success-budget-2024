package pl.wsikora.successbudget.v3.common.util.databaseconverter;

import jakarta.persistence.AttributeConverter;

import java.sql.Date;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class YearMonthDatabaseConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    public Date convertToDatabaseColumn(YearMonth yearMonth) {

        return nonNull(yearMonth) ? Date.valueOf(yearMonth.atDay(1)) : null;
    }

    @Override
    public YearMonth convertToEntityAttribute(Date date) {

        return isNull(date) ? null :
            YearMonth.from(Instant
                .ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            );
    }
}
