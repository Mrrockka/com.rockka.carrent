package com.rockka.carrent.converters;

import com.rockka.carrent.enums.CarStatus;

import javax.persistence.AttributeConverter;

public class CarStatusConverter implements AttributeConverter<CarStatus, Integer> {
	@Override
	public Integer convertToDatabaseColumn(CarStatus status) {
		return status == null ? null : status.toInt();
	}

	@Override
	public CarStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : CarStatus.get(dbData);
	}
}
