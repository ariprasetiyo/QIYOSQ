package arprast.qiyosq.dto;

import arprast.qiyosq.ref.StatusType;

public class ResponseDto<T extends Dto> {

	/*
	 * protected Class<T> clazz;
	 * 
	 * protected JsonMessageDto(Class<T> clazz) { this.clazz = clazz; }
	 * 
	 * public Class<T> getClazz() { return clazz; }
	 */

	private StatusType statusType;

	private String message;

	private T responseData;

	public ResponseDto() {

	}

	/**
	 * 
	 * @param statusType
	 * @param message
	 * @param responseData
	 */
	public ResponseDto(StatusType statusType, String message, T responseData) {
		this.message = message;
		this.statusType = statusType;
		this.responseData = responseData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

}
