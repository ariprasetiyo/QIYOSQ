package arprast.qiyosq.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;

public class JsonMessageDto implements Serializable {

	private static final long serialVersionUID = 2964184990943515733L;

	@JsonIgnore
	private String message;

	@JsonIgnore
	private MessageErrorType messageErrorType;

	@JsonIgnore
	private MessageSuccessType messageSuccessType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageErrorType getMessageErrorType() {
		return messageErrorType;
	}

	public void setMessageErrorType(MessageErrorType messageErrorType) {
		this.messageErrorType = messageErrorType;
	}

	public MessageSuccessType getMessageSuccessType() {
		return messageSuccessType;
	}

	public void setMessageSuccessType(MessageSuccessType messageSuccessType) {
		this.messageSuccessType = messageSuccessType;
	}

}
