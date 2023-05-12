// 
// 
// 

package com.lianshuwang.helper;

public class UploadHelper
{
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private String uploadedDate;
    private int downloadTimes;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getBookTitle() {
        return this.bookTitle;
    }
    
    public void setBookTitle(final String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    public String getBookAuthor() {
        return this.bookAuthor;
    }
    
    public void setBookAuthor(final String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    
    public String getUploadedDate() {
        return this.uploadedDate;
    }
    
    public void setUploadedDate(final String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
    
    public int getDownloadTimes() {
        return this.downloadTimes;
    }
    
    public void setDownloadTimes(final int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }
}
