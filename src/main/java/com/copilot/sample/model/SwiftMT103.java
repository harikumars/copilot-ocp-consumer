// create pojo for swift_mt103 controller
package com.copilot.sample.model;

import java.util.Date;

public class SwiftMT103 {
	private String id;
	private String messageType;
	private String messagePriority;
	private String sender;
	private String receiver;
	private String message;
	private Date createdAt;
	private Date updatedAt;

	public SwiftMT103() {
	}

	public SwiftMT103(String id, String messageType, String messagePriority, String sender, String receiver,
			String message, Date createdAt, Date updatedAt) {
		this.id = id;
		this.messageType = messageType;
		this.messagePriority = messagePriority;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessagePriority() {
		return messagePriority;
	}

	public void setMessagePriority(String messagePriority) {
		this.messagePriority = messagePriority;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
