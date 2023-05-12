// 
// 
// 

package com.lianshuwang.helper;

import java.util.Date;
import com.lianshuwang.domin.Contribution;
import com.lianshuwang.domin.Avatar;

public class UserHelper
{
    private long id;
    private String userCode;
    private String userName;
    private String email;
    private Avatar avatar;
    private int contributionValue;
    private Contribution contribution;
    private Date creationDate;
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public int getContributionValue() {
        return this.contributionValue;
    }
    
    public void setContributionValue(final int contributionValue) {
        this.contributionValue = contributionValue;
    }
    
    public void setUserCode(final String userCode) {
        this.userCode = userCode;
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
    
    public Avatar getAvatar() {
        return this.avatar;
    }
    
    public void setAvatar(final Avatar avatar) {
        this.avatar = avatar;
    }
    
    public Contribution getContribution() {
        return this.contribution;
    }
    
    public void setContribution(final Contribution contribution) {
        this.contribution = contribution;
    }
    
    public Date getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
}
