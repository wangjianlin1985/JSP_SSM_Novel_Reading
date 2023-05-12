// 
// 
// 

package com.lianshuwang.helper;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class doBookHelper
{
    private long id;
    private String title;
    private String author;
    private String puYear;
    private int largeType;
    private int smallType;
    private String summary;
    private MultipartFile bookCover;
    private long uploader;
    private Date uploadedDate;
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }
    
    public String getPuYear() {
        return this.puYear;
    }
    
    public void setPuYear(final String puYear) {
        this.puYear = puYear;
    }
    
    public int getLargeType() {
        return this.largeType;
    }
    
    public void setLargeType(final int largeType) {
        this.largeType = largeType;
    }
    
    public int getSmallType() {
        return this.smallType;
    }
    
    public void setSmallType(final int smallType) {
        this.smallType = smallType;
    }
    
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(final String summary) {
        this.summary = summary;
    }
    
    public MultipartFile getBookCover() {
        return this.bookCover;
    }
    
    public void setBookCover(final MultipartFile bookCover) {
        this.bookCover = bookCover;
    }
    
    public long getUploader() {
        return this.uploader;
    }
    
    public void setUploader(final long uploader) {
        this.uploader = uploader;
    }
    
    public Date getUploadedDate() {
        return this.uploadedDate;
    }
    
    public void setUploadedDate(final Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}
