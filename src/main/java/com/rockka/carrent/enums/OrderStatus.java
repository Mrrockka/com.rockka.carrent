package com.rockka.carrent.enums;

/*
	OrderStatus enum for clear representation of status variable
*/

public enum OrderStatus {
	DELETED(0, "deleted")
	, ACTIVE(1, "active")
	, NEW(2, "new")
	, DENIED(3, "denied")
	, EXPIRED(4, "expired")
	, DEBT(5, "debt")
	, CLOSED(6, "closed")
	;

	private int status;
	private String representation;

	OrderStatus(int status, String representation) {
		this.status = status;
		this.representation = representation;
	}

	public static OrderStatus get(int i) {
		switch (i) {
			case 0:
				return DELETED;
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
			case 6:
				return CLOSED;
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
