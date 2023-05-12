// 
// 
// 

package com.lianshuwang.domin;

import java.util.Date;
import java.io.Serializable;

public class Book implements Serializable
{
    private long id;
    private String book_title;
    private String book_author;
    private Date book_pubYear;
    private String book_summary;
    private int type_id;
    private String book_format;
    private int download_times;
    private String book_file;
    private String book_cover;
    
    public Book() {
    }
    
    public Book(final long id, final String book_title, final String book_author, final Date book_pubYear, final String book_summary, final int type_id, final String book_format, final int download_times, final String book_file, final String book_cover) {
        this.id = id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pubYear = book_pubYear;
        this.book_summary = book_summary;
        this.type_id = type_id;
        this.book_format = book_format;
        this.download_times = download_times;
        this.book_file = book_file;
        this.book_cover = book_cover;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getBook_title() {
        return this.book_title;
    }
    
    public void setBook_title(final String book_title) {
        this.book_title = book_title;
    }
    
    public String getBook_author() {
        return this.book_author;
    }
    
    public void setBook_author(final String book_author) {
        this.book_author = book_author;
    }
    
    public Date getBook_pubYear() {
        return this.book_pubYear;
    }
    
    public void setBook_pubYear(final Date book_pubYear) {
        this.book_pubYear = book_pubYear;
    }
    
    public String getBook_summary() {
        return this.book_summary;
    }
    
    public void setBook_summary(final String book_summary) {
        this.book_summary = book_summary;
    }
    
    public int getType_id() {
        return this.type_id;
    }
    
    public void setType_id(final int type_id) {
        this.type_id = type_id;
    }
    
    public String getBook_format() {
        return this.book_format;
    }
    
    public void setBook_format(final String book_format) {
        this.book_format = book_format;
    }
    
    public int getDownload_times() {
        return this.download_times;
    }
    
    public void setDownload_times(final int download_times) {
        this.download_times = download_times;
    }
    
    public String getBook_file() {
        return this.book_file;
    }
    
    public void setBook_file(final String book_file) {
        this.book_file = book_file;
    }
    
    public String getBook_cover() {
        return this.book_cover;
    }
    
    public void setBook_cover(final String book_cover) {
        this.book_cover = book_cover;
    }
}
