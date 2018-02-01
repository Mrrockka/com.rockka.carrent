package com.rockka.carrent.enums;

/*
	OrderStatus enum for clear representation of status variable
*/

public enum InvoiceStatus{
	DELETED(0, "deleted")
	, CLOSED(1, "closed")
	, EXPIRED(2, "expired")
	, NEW(3, "new")
	, ACTIVE(4, "active")
	, DENIED(5, "denied")
	, DEBT(6, "debt")
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
				return NEW;
			case 4:
				return ACTIVE;
			case 5:
				return DENIED;
			case 6:
				return DEBT;
			default:
				return ACTIVE;
		}
	}

	public int toInt() {
		return this.status;
	}
	public String toString() {
		return this.representation;
	}
}
