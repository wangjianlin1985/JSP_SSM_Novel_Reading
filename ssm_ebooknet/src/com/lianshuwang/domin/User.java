// 
// 
// 

package com.lianshuwang.domin;

import java.util.Date;
import java.io.Serializable;

public class User implements Serializable
{
    private long id;
    private String userCode;
    private String userPassword;
    private String userName;
    private String email;
    private int avatarNum;
    private int contribution;
    private Date creationDate;
    
    public User() {
    }
    
    public User(final long id, final String userCode, final String userPassword, final String userName, final String email, final int avatar, final int contribution, final Date creationDate) {
        this.id = id;
        this.userCode = userCode;
        this.userPassword = userPassword;
        this.userName = userName;
        this.email = email;
        this.avatarNum = avatar;
        this.contribution = contribution;
        this.creationDate = creationDate;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }
    
    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(final String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public int getAvatarNum() {
        return this.avatarNum;
    }
    
    public void setAvatarNum(final int avatarNum) {
        this.avatarNum = avatarNum;
    }
    
    public int getContribution() {
        return this.contribution;
    }
    
    public void setContribution(final int contribution) {
        this.contribution = contribution;
    }
    
    public Date getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
}
