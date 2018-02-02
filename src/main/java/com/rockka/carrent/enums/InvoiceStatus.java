package com.rockka.carrent.enums;

/*
	OrderStatus enum for clear representation of status variable
*/

public enum InvoiceStatus{
	DELETED(0, "deleted")
	, CLOSED(1, "closed")
	, EXPIRED(2, "expired")
	, DENIED(3, "denied")
	, NOT_PAID(4, "not paid")
	, ACTIVE(5, "active")
	;

	private int status;
	private String representation;

	InvoiceStatus(int status, String representation) {
		this.status = status;
		this.representation = representation;
	}

	public static InvoiceStatus get(int i) {
		switch (i) {
			case 0:
				return DELETED;
			case 1:
				return CLOSED;
			case 2:
				return EXPIRED;
			case 3:
				return DENIED;
			case 4:
				return NOT_PAID;
			case 5:
				return ACTIVE;
			default:
				return NOT_PAID;
		}
	}

	public int toInt() {
		return this.status;
	}
	public String toString() {
		return this.representation;
	}
}
