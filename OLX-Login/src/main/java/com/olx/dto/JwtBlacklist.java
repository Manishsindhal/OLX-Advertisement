package com.olx.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class JwtBlacklist {

	@Id
    private String _id;
	
    @Indexed(direction = IndexDirection.ASCENDING)
    private String token;
    
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
        return "JwtBlacklist{" +
                "_id='" + _id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
