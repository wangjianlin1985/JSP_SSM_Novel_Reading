// 
// 
// 

package com.lianshuwang.domin;

import java.io.Serializable;

public class Avatar implements Serializable
{
    private int id;
    private String avatar_txt;
    private String avatar_img;
    
    public Avatar() {
    }
    
    public Avatar(final int id, final String avatar_txt, final String avatar_img) {
        this.id = id;
        this.avatar_txt = avatar_txt;
        this.avatar_img = avatar_img;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getAvatar_txt() {
        return this.avatar_txt;
    }
    
    public void setAvatar_txt(final String avatar_txt) {
        this.avatar_txt = avatar_txt;
    }
    
    public String getAvatar_img() {
        return this.avatar_img;
    }
    
    public void setAvatar_img(final String avatar_img) {
        this.avatar_img = avatar_img;
    }
}
