// 
// 
// 

package com.lianshuwang.helper;

import java.io.Serializable;

public class PageHelper implements Serializable
{
    private int pageSize;
    private int currentPage;
    private int startRow;
    private int totalPage;
    private int totalRows;
    
    public PageHelper() {
        this.pageSize = 12;
        this.currentPage = 1;
        this.startRow = 1;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public int getCurrentPage() {
        return this.currentPage;
    }
    
    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getStartRow() {
        return this.startRow = (this.currentPage - 1) * this.pageSize;
    }
    
    public void setStartRow(final int startRow) {
        this.startRow = startRow;
    }
    
    public int getTotalPage() {
        if (this.totalRows % this.pageSize == 0) {
            this.totalPage = this.totalRows / this.pageSize;
        }
        else {
            this.totalPage = this.totalRows / this.pageSize + 1;
        }
        return this.totalPage;
    }
    
    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }
    
    public int getTotalRows() {
        return this.totalRows;
    }
    
    public void setTotalRows(final int totalRows) {
        this.totalRows = totalRows;
    }
}
