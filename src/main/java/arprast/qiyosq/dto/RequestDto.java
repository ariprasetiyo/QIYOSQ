package arprast.qiyosq.dto;

import javax.validation.Valid;

public class RequestDto<T extends Dto> {

	@Valid
	private T requestData;
	private String id;
	private String key;

	public T getRequestData() {
		return requestData;
	}

	public void setRequestData(T requestData) {
		this.requestData = requestData;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
