// 
// 
// 

package com.lianshuwang.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.lianshuwang.domin.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao
{
    Book queryById(long p0);
    
    int queryNumberOfBooks();
    
    int queryNumberOfSomeTypeBooks(int p0);
    
    int addBook(Book p0);
    
    Book queryByTitle(String p0);
    
    List<Book> getLargeTypeBooks(@Param("list") List<Integer> p0, @Param("startRow") int p1, @Param("pageSize") int p2);
    
    List<Book> getSmallTypeBooks(@Param("type_id") int p0, @Param("startRow") int p1, @Param("pageSize") int p2);
    
    int addDownloadTimes(long p0);
    
    List<Book> queryByDownloadTimes();
    
    List<Book> searchBookByTitle(String p0);
    
    List<Book> searchBookByAuthor(String p0);
    
    int getTotalOfLTBooks(List<Integer> p0);
    
    int getTotalOfSTBooks(int p0);
    
    int deleteBook(long p0);
}
