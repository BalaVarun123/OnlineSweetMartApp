package com.cg.osm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String username;
    private String password;
    private String passwordConfirm;
    private String type;
    private boolean isLoggedIn;
    
    public User() {
    	
    }
	public User(Long userId, String username, String password,String passwordConfirm, String type,boolean isLoggedIn) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.type = type;
		this.isLoggedIn = isLoggedIn;
	}
	public long getUserId() {
		return userId;
	}
	public long setUserId(long userId) {
		return this.userId = userId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", passwordConfirm="
				+ passwordConfirm + ", type=" + type + ", isLoggedIn=" + isLoggedIn + "]";
	}
	
	
	
    
    
    
    
}
