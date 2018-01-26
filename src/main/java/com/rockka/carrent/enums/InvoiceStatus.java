package com.rockka.carrent.enums;

/*
	OrderStatus enum for clear representation of status variable
*/

public enum InvoiceStatus {
	DELETED(-1, "deleted")
	, CLOSED(0, "closed")
	, ACTIVE(1, "active")
	, NEW(2, "new")
	, DENIED(3, "denied")
	, EXPIRED(4, "expired")
	, DEBT(5, "debt")
	;

	private int status;
	private String representation;

	InvoiceStatus(int status, String representation) {
		this.status = status;
		this.representation = representation;
	}

	public static InvoiceStatus get(int i) {
		switch (i) {
			case -1:
				return DELETED;
			case 0:
				return CLOSED;
			case 1:
				return ACTIVE;
			case 2:
				return NEW;
			case 3:
				return DENIED;
			case 4:
				return EXPIRED;
			case 5:
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
