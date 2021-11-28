package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue
	private int Id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "roles")
	private String roles;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "active")
	private boolean active;
	
	//@Enumerated(value =  EnumType.ORDINAL)
	//private active active;

	public UserEntity(int id, String username, String password, String roles, String firstname, String lastname,
			boolean active) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.firstname = firstname;
		this.lastname = lastname;
		this.active = active;
	}
	
	public UserEntity(String username, String password, String roles, String firstname, String lastname,
			boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.firstname = firstname;
		this.lastname = lastname;
		this.active = active;
	}
	
	public UserEntity() {}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserEntity [Id=" + Id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", active=" + active + "]";
	}
	
}
