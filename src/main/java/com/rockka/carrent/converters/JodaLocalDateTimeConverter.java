package com.rockka.carrent.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JodaLocalDateTimeConverter implements AttributeConverter<org.joda.time.LocalDateTime, java.sql.Timestamp> {
	@Override
	public java.sql.Timestamp convertToDatabaseColumn(org.joda.time.LocalDateTime attribute) {
		return attribute == null ? null : java.sql.Timestamp.valueOf(attribute.toString());
	}

	@Override
	public org.joda.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbData) {
		return dbData == null ? null : org.joda.time.LocalDateTime.parse(dbData.toString());
	}
}
