package arprast.qiyosq.util;

import org.slf4j.Logger;

import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;

public class LogsUtil {

	private static StringBuilder messages = new StringBuilder();
	private static final String MESSAGE_SUCCESS_TYPE = "MessageSuccessType";
	private static final String MESSAGE_ERROR_TYPE = "MessageErrorType";
	private static final String ACTION_TYPE = "ActionType";
	private static final String COLON = ":";
	private static final String DIRECTION = " -> ";

	/**
	 * Future for audit trail
	 * 
	 * @param logger
	 * @param isEnabled
	 * @param paramMessageStatus
	 *            : {@value MessageSuccessType, MessageErrorType, ActionType}
	 * @param message
	 * @param values
	 * 
	 */
	public static void logDebug(Logger logger, boolean isEnabled, Object paramMessageStatus, String message,
			Object... values) {
		try {
			if (paramMessageStatus instanceof MessageSuccessType) {
				if (logger.isDebugEnabled() && isEnabled) {
					messages.append(MESSAGE_SUCCESS_TYPE);
					messages.append(COLON);
					messages.append(paramMessageStatus.toString());
					messages.append(DIRECTION);
					messages.append(message);
					logger.debug(messages.toString(), values);
				}
			} else if (paramMessageStatus instanceof MessageErrorType) {
				if (logger.isDebugEnabled() && isEnabled) {
					messages.append(MESSAGE_ERROR_TYPE);
					messages.append(COLON);
					messages.append(paramMessageStatus.toString());
					messages.append(DIRECTION);
					messages.append(message);
					logger.debug(messages.toString(), values);
				}
			} else if (paramMessageStatus instanceof ActionType) {
				if (logger.isDebugEnabled() && isEnabled) {
					messages.append(ACTION_TYPE);
					messages.append(COLON);
					messages.append(paramMessageStatus.toString());
					messages.append(DIRECTION);
					messages.append(message);
					logger.debug(messages.toString(), values);
				}
			} else {
				if (logger.isDebugEnabled() && isEnabled) {
					logger.debug(message, values);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messages.delete(0, messages.length());
		}
	}

	/**
	 *  
	 * @param logger
	 * @param isEnabled
	 * @param message
	 * @param values
	 * 
	 * */
	public static void logDebug(Logger logger, boolean isEnabled, String message, Object... values) {
		if (logger.isDebugEnabled() && isEnabled) {
			logger.debug(message, values);
		}
	}
}
