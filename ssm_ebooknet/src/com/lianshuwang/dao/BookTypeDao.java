// 
// 
// 

package com.lianshuwang.dao;

import java.util.List;
import com.lianshuwang.domin.BookType;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeDao
{
    BookType queryById(int p0);
    
    List<BookType> queryByLargeTypeName(String p0);
}
