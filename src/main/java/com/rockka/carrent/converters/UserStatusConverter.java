package com.rockka.carrent.converters;

import com.rockka.carrent.enums.UserStatus;

import javax.persistence.AttributeConverter;

public class UserStatusConverter implements AttributeConverter<UserStatus, Integer>{
	@Override
	public Integer convertToDatabaseColumn(UserStatus status) {
		return status == null ? null : status.toInt();
	}

	@Override
	public UserStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : UserStatus.get(dbData);
	}
}
