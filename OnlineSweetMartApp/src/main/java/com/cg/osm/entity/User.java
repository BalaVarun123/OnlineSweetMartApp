package com.cg.osm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GenerationType;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String type;
    private boolean isLoggedIn;
    
    public User() {
    	
    }
	public User(Long userId, String username, String password, String type,boolean isLoggedIn) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.type = type;
		this.isLoggedIn = isLoggedIn;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", type=" + type
				+ ", isLoggedIn=" + isLoggedIn + "]";
	}
	
    
    
    
    
}
