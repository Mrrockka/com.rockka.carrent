package com.rockka.carrent.converters;

import com.rockka.carrent.enums.InvoiceStatus;

import javax.persistence.AttributeConverter;

/*
 ** Converter between InvoiceStatus enum and database value
 */
public class InvoiceStatusConverter implements AttributeConverter<InvoiceStatus, Integer>{
//	To database value
	@Override
	public Integer convertToDatabaseColumn(InvoiceStatus status) {
		return status == null ? null : status.toInt();
	}
//	To enum object
	@Override
	public InvoiceStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : InvoiceStatus.get(dbData);
	}
}
