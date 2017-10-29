package arprast.qiyosq.util;

import org.slf4j.Logger;

public class LogsUtil {
	public static void logDebug(Logger logger, boolean isSpesificDisabled, String message, Object... parameter) {
		try {
			if (logger.isDebugEnabled() && isSpesificDisabled) {
				logger.debug(message, parameter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
