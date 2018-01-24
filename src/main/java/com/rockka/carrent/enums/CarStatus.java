package com.rockka.carrent.enums;
/*
	CarStatus enum for clear representation of status variable
*/
public enum CarStatus {

	DELETED(0, "deleted")
	, ACTIVE(1, "active")
	, BROKEN(2, "broken")
	;

	private int status;
	private String representation;

	CarStatus(int status, String representation) {
		this.status = status;
		this.representation = representation;
	}

	public static CarStatus get(int i) {
		switch (i) {
			case 0:
				return DELETED;
			case 1:
				return BROKEN;
			case 2:
				return ACTIVE;
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
