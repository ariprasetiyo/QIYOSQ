package arprast.qiyosq.ref;

public enum StatusType {

	SAVE_SUCCEED(1), 
	UPDATE_SUCCEED(2), 
	DELETE_SUCCEED(3),
	DUPLICATE_DATA_ERROR(501), 
	DUPLICATE_EMAIL_ERROR(502), 
	NULL_POINTER_ERROR(503), 
	NOT_SAVED_ERROR(504), 
	SAVE_USER_ERROR(505), 
	SAVE_ROLE_ERROR(506), 
	API_REQ_RES_ERROR(507), 
	API_REQ_RES_GLOBAL_ERROR(508), 
	UPDATE_ERROR(509), 
	SAVE_ERROR(510);

	public final int intValue;

	/**
	 * @param intValue
	 */
	private StatusType(int intValue) {
		this.intValue = intValue;
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
		default:
			return null;
		}
	}

}
