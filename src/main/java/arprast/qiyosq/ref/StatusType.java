package arprast.qiyosq.ref;

public enum StatusType {

	SAVE_SUCCEED(1, "SAVE SUCCEDD"), 
	UPDATE_SUCCEED(2, "UPDATE SUCCEDD"), 
	DELETE_SUCCEED(3 , "DELETE SUCCEDD"),
	DUPLICATE_DATA_ERROR(501, "DUPLICATE DATA ERROR"), 
	DUPLICATE_EMAIL_ERROR(502, "DUPLICATE EMAIL ERROR"), 
	NULL_POINTER_ERROR(503, "NULL POINTER ERROR"), 
	NOT_SAVED_ERROR(504, "NOT SAVED ERROR"), 
	SAVE_USER_ERROR(505, "SAVE USER ERROR" ), 
	SAVE_ROLE_ERROR(506, "SAVE ROLE ERROR"), 
	API_REQ_RES_ERROR(507, "API REQ RES ERROR"), 
	API_REQ_RES_GLOBAL_ERROR(508, "API REQ RES GLOBAL ERROR"), 
	UPDATE_ERROR(509, "UPDATE ERROR"), 
	SAVE_ERROR(510, "SAVE ERROR"),
	WRONG_OLD_PASSWORD(511, "WRONG OLD PASSWORD");

	public final int intValue;
	public final String stringValue;

	/**
	 * 
	 * @param intValue
	 * @param message
	 */
	private StatusType(int intValue, String stringValue) {
		this.intValue = intValue;
		this.stringValue = stringValue;
	}

	public static StatusType valueOf(int intValue) {
		switch (intValue) {
		case 1:
			return SAVE_SUCCEED;
		case 2:
			return UPDATE_SUCCEED;
		case 3:
			return DELETE_SUCCEED;
		case 501:
			return DUPLICATE_DATA_ERROR;
		case 502:
			return DUPLICATE_EMAIL_ERROR;
		case 503:
			return NULL_POINTER_ERROR;
		case 504:
			return NOT_SAVED_ERROR;
		case 505:
			return SAVE_USER_ERROR;
		case 506:
			return SAVE_ROLE_ERROR;
		case 507:
			return API_REQ_RES_ERROR;
		case 508:
			return API_REQ_RES_GLOBAL_ERROR;
		case 509:
			return UPDATE_ERROR;
		case 510:
			return SAVE_ERROR;
		case 511:
			return WRONG_OLD_PASSWORD;
		default:
			return null;
		}
	}

}