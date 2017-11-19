package arprast.qiyosq.ref;

public enum MessageErrorType {

	DUPLICATE_DATA_ERROR(1), DUPLICATE_EMAIL_ERROR(2), NULL_POINTER_ERROR(3), NOT_SAVED_ERROR(4), SAVE_USER_ERROR(
			5), SAVE_ROLE_ERROR(6);

	public final int intValue;

	/**
	 * @param intValue
	 */
	private MessageErrorType(int intValue) {
		this.intValue = intValue;
	}

	public static MessageErrorType valueOf(int intValue) {
		switch (intValue) {
		case 1:
			return DUPLICATE_DATA_ERROR;
		case 2:
			return DUPLICATE_EMAIL_ERROR;
		case 3:
			return NULL_POINTER_ERROR;
		case 4:
			return NOT_SAVED_ERROR;
		case 5:
			return SAVE_USER_ERROR;
		case 6:
			return SAVE_ROLE_ERROR;
		default:
			return null;
		}
	}

}