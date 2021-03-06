package com.rockka.carrent.enums;
/*
	CarStatus enum for clear representation of status variable
*/
public enum CarStatus{

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
	/*
	 ** Returns object by number representation (for converter)
	 */
	public static CarStatus get(int i) {
		switch (i) {
			case 0:
				return DELETED;
			case 1:
				return ACTIVE;
			case 2:
				return BROKEN;
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
