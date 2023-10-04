package com.financeSystem.registration.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
  
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String userName;
    private String password;
    private String email;
    private boolean isAccountCreated;
    
    

    public boolean isAccountCreated() {
		return isAccountCreated;
	}
	public void setAccountCreated(boolean isAccountCreated) {
		this.isAccountCreated = isAccountCreated;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

}