// 
// 
// 

package com.lianshuwang.domin;

import java.io.Serializable;

public class Contribution implements Serializable
{
    private int id;
    private int lowerLimit;
    private int upperLimit;
    private String level_txt;
    private String level_img;
    
    public Contribution() {
    }
    
    public Contribution(final int id, final int lowerLimit, final int upperLimit, final String level_txt, final String level_img) {
        this.id = id;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.level_txt = level_txt;
        this.level_img = level_img;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getMinValue() {
        return this.lowerLimit;
    }
    
    public void setMinValue(final int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
    
    public int getMaxValue() {
        return this.upperLimit;
    }
    
    public void setMaxValue(final int upperLimit) {
        this.upperLimit = upperLimit;
    }
    
    public String getLevel_txt() {
        return this.level_txt;
    }
    
    public void setLevel_txt(final String level_txt) {
        this.level_txt = level_txt;
    }
    
    public String getLevel_img() {
        return this.level_img;
    }
    
    public void setLevel_img(final String level_img) {
        this.level_img = level_img;
    }
}
