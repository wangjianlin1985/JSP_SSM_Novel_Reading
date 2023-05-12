// 
// 
// 

package com.lianshuwang.domin;

import java.util.Date;
import java.io.Serializable;

public class Upload implements Serializable
{
    private int id;
    private long uploader;
    private long uploadedBook;
    private Date uploadedDate;
    
    public Upload() {
    }
    
    public Upload(final int id, final long uploader, final long uploadedBook, final Date uploadedDate) {
        this.id = id;
        this.uploader = uploader;
        this.uploadedBook = uploadedBook;
        this.uploadedDate = uploadedDate;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public long getUploader() {
        return this.uploader;
    }
    
    public void setUploader(final long uploader) {
        this.uploader = uploader;
    }
    
    public long getUploadedBook() {
        return this.uploadedBook;
    }
    
    public void setUploadedBook(final long uploadedBook) {
        this.uploadedBook = uploadedBook;
    }
    
    public Date getUploadedDate() {
        return this.uploadedDate;
    }
    
    public void setUploadedDate(final Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}
