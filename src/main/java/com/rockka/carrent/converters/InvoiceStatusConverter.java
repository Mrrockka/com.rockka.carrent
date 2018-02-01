package com.rockka.carrent.converters;

import com.rockka.carrent.enums.InvoiceStatus;

import javax.persistence.AttributeConverter;

public class InvoiceStatusConverter implements AttributeConverter<InvoiceStatus, Integer>{
	@Override
	public Integer convertToDatabaseColumn(InvoiceStatus status) {
		return status == null ? null : status.toInt();
	}

	@Override
	public InvoiceStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : InvoiceStatus.get(dbData);
	}
}
