// 
// 
// 

package com.lianshuwang.helper;

public class RankingBook
{
    private long id;
    private String bookName;
    private String uploadDate;
    private int download_times;
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getBookName() {
        return this.bookName;
    }
    
    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }
    
    public String getUploadDate() {
        return this.uploadDate;
    }
    
    public void setUploadDate(final String uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    public int getDownload_times() {
        return this.download_times;
    }
    
    public void setDownload_times(final int download_times) {
        this.download_times = download_times;
    }
}
