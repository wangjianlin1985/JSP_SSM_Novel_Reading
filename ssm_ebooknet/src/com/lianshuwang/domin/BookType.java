// 
// 
// 

package com.lianshuwang.domin;

import java.io.Serializable;

public class BookType implements Serializable
{
    private int id;
    private int book_large_type;
    private int book_small_type;
    private String large_type_name;
    private String small_type_name;
    
    public BookType() {
    }
    
    public BookType(final int id, final int book_large_type, final int book_small_type, final String large_type_name, final String small_type_name) {
        this.id = id;
        this.book_large_type = book_large_type;
        this.book_small_type = book_small_type;
        this.large_type_name = large_type_name;
        this.small_type_name = small_type_name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getBook_large_type() {
        return this.book_large_type;
    }
    
    public void setBook_large_type(final int book_large_type) {
        this.book_large_type = book_large_type;
    }
    
    public int getBook_small_type() {
        return this.book_small_type;
    }
    
    public void setBook_small_type(final int book_small_type) {
        this.book_small_type = book_small_type;
    }
    
    public String getLarge_type_name() {
        return this.large_type_name;
    }
    
    public void setLarge_type_name(final String large_type_name) {
        this.large_type_name = large_type_name;
    }
    
    public String getSmall_type_name() {
        return this.small_type_name;
    }
    
    public void setSmall_type_name(final String small_type_name) {
        this.small_type_name = small_type_name;
    }
}
