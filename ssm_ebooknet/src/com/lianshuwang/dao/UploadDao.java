// 
// 
// 

package com.lianshuwang.dao;

import java.util.List;
import java.util.Date;
import com.lianshuwang.domin.Upload;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDao
{
    Upload queryById(int p0);
    
    int addUploadRecord(Upload p0);
    
    Upload queryByBookId(long p0);
    
    Date getMaxUploadDate();
    
    List<Upload> queryByUploadedDate();
    
    List<Upload> queryByUserId(long p0);
    
    int deleteUploadRow(long p0);
    
    int deleteUploadRowByUser(long p0);
    
    List<Upload> searchByToday();
    
    List<Upload> searchBySevenDays();
    
    List<Upload> searchByThirtyDays();
}
