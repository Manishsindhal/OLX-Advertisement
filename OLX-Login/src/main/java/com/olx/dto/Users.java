package com.olx.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("User Model")
public class Users {

	@ApiModelProperty("Unique identifier of the User")
	private int Id;
	
	@ApiModelProperty("Username for Authentication")
	private String username;
	
	@ApiModelProperty("Password for Authentication")
	private String password;
	
	@ApiModelProperty("Role of the User")
	private String roles;
	
	@ApiModelProperty("First Name of the User")
	private String firstname;
	
	@ApiModelProperty("Last Name of the User")
	private String lastname;
	
	@ApiModelProperty("Active status of the User")
	private boolean active;

	public Users(int id, String username, String password, String roles, String firstname, String lastname,
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
	
	public Users() {}

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
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", active=" + active + "]";
	}
	
	
}
