package arprast.qiyosq.ref;

public enum MessageSuccessType {
	SAVE_SUCCEED(1), UPDATE_SUCCEED(2), DELETE_SUCCEED(3);

	public final int intValue;

	MessageSuccessType(int intValue) {
		this.intValue = intValue;
	}

	public static MessageSuccessType valueOf(int intValue) {
		switch (intValue) {
		case 1:
			return SAVE_SUCCEED;
		case 2:
			return UPDATE_SUCCEED;
		case 3:
			return DELETE_SUCCEED;
		default:
			return null;
		}
	}

}
