package com.cg.osm.entity;


/*
 * Author      : UJJWAL SINGH A
 * Version     : 1.0
 * Date        : 03-04-2021
 * Description : This is Login POJO class
*/

public class Login {
private long userId;
private String password;
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Login [userId=" + userId + ", password=" + password + "]";
}


}
