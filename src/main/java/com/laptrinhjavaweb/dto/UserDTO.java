package com.laptrinhjavaweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserDTO extends AbstractDTO<UserDTO> {

	@NotNull
	@NotEmpty
	private String userName;

	@NotNull
	@NotEmpty
	private String fullName;

	@NotNull
	@NotEmpty
	private String password;

	private List<RoleDTO> roles = new ArrayList<>();

	private int status;

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
