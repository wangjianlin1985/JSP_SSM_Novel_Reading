// 
// 
// 

package com.lianshuwang.helper;

import org.springframework.web.multipart.MultipartFile;

public class BookHelper
{
    private long id;
    private String title;
    private String author;
    private String pubYear;
    private int largeType;
    private int smallType;
    private String summary;
    private MultipartFile bookFile;
    private MultipartFile bookCover;
    
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
    
    public String getPubYear() {
        return this.pubYear;
    }
    
    public void setPubYear(final String pubYear) {
        this.pubYear = pubYear;
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
    
    public MultipartFile getBookFile() {
        return this.bookFile;
    }
    
    public void setBookFile(final MultipartFile bookFile) {
        this.bookFile = bookFile;
    }
    
    public MultipartFile getBookCover() {
        return this.bookCover;
    }
    
    public void setBookCover(final MultipartFile bookCover) {
        this.bookCover = bookCover;
    }
}
