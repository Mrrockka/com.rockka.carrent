package com.rockka.carrent.converters;

import com.rockka.carrent.enums.CarStatus;

import javax.persistence.AttributeConverter;
/*
** Converter between CarStatus enum and database value
*/
public class CarStatusConverter implements AttributeConverter<CarStatus, Integer> {
//	To database value
	@Override
	public Integer convertToDatabaseColumn(CarStatus status) {
		return status == null ? null : status.toInt();
	}
//  To enum object
	@Override
	public CarStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : CarStatus.get(dbData);
	}
}
