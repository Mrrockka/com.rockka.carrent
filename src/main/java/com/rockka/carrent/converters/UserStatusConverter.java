package com.rockka.carrent.converters;

import com.rockka.carrent.enums.UserStatus;

import javax.persistence.AttributeConverter;

/*
 ** Converter between UserStatus enum and database value
 */
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer>{
//	To database value
	@Override
	public Integer convertToDatabaseColumn(UserStatus status) {
		return status == null ? null : status.toInt();
	}
//	To enum object
	@Override
	public UserStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : UserStatus.get(dbData);
	}
}
