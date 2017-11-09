package arprast.qiyosq.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;

public class JsonMessageDto implements Serializable {

	private static final long serialVersionUID = 2964184990943515733L;

	private String message;

	private MessageErrorType messageErrorType;

	private MessageSuccessType messageSuccessType;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
