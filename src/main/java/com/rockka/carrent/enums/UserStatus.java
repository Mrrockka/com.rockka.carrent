package com.rockka.carrent.enums;
/*
	UserStatus enum for clear representation of status variable
*/
public enum UserStatus {

	DELETED(0, "deleted")
	, ACTIVE(1, "active")
	;

	private int status;
	private String representation;

	UserStatus(int status, String representation) {
		this.status = status;
		this.representation = representation;
	}
	/*
	 ** Returns object by number representation (for converter)
	 */
	public static UserStatus get(int i) {
		switch (i) {
			case 0:
				return DELETED;
			case 1:
				return ACTIVE;
			default:
				return ACTIVE;
		}
	}

	/*
	 ** Returns object number representation (for converter)
	 */
	public int toInt() {
		return this.status;
	}
	/*
	 ** Returns object string representation (for server response)
	 */
	public String toString() {
		return this.representation;
	}
}

