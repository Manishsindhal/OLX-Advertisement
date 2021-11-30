package com.olx.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

public class UserBlackListToken {

	@Id
    private String _id;
	
    @Indexed(direction = IndexDirection.ASCENDING)
    private String token;

	public UserBlackListToken(String _id, String token) {
		super();
		this._id = _id;
		this.token = token;
	}
	
	public UserBlackListToken(String token) {
		super();
		this.token = token;
	}
	
	public UserBlackListToken() {}

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
