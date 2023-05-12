// 
// 
// 

package com.lianshuwang.domin;

import java.sql.Timestamp;
import java.io.Serializable;

public class Feedback implements Serializable
{
    private int id;
    private long loginedUser;
    private String contact;
    private String suggestion;
    private Timestamp postTime;
    private int status;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public long getLoginedUser() {
        return this.loginedUser;
    }
    
    public void setLoginedUser(final long loginedUser) {
        this.loginedUser = loginedUser;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(final String contact) {
        this.contact = contact;
    }
    
    public String getSuggestion() {
        return this.suggestion;
    }
    
    public void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }
    
    public Timestamp getPostTime() {
        return this.postTime;
    }
    
    public void setPostTime(final Timestamp postTime) {
        this.postTime = postTime;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
}
