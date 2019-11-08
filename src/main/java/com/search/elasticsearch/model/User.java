package com.search.elasticsearch.model;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "my_index", type = "user")
//@Data
//@Getter
//@Setter
@Data
public class User {

	@Id
	private String userId;
	private String name;
	private String first_name;
	private String last_name;
	private String team_name;
	private String email;
	private String manager_email;
	private String github_username;
	private Boolean is_active;
	private String employee_type;
	private Date creationDate = new Date();

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

	public String getGithub_username() {
		return github_username;
	}

	public void setGithub_username(String github_username) {
		this.github_username = github_username;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public String getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
