package com.olx.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "blacklisttoken")
public class UserBlackListTokenDocument {

	@Id
    private String _id;
	
    @Indexed(direction = IndexDirection.ASCENDING)
    private String token;

	public UserBlackListTokenDocument(String _id, String token) {
		super();
		this._id = _id;
		this.token = token;
	}
	
	public UserBlackListTokenDocument(String token) {
		super();
		this.token = token;
	}
	
	public UserBlackListTokenDocument() {}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserBlackListTokenDocument [_id=" + _id + ", token=" + token + "]";
	}
	
}
