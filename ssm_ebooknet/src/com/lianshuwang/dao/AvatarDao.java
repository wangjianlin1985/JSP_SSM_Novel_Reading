// 
// 
// 

package com.lianshuwang.dao;

import java.util.List;
import com.lianshuwang.domin.Avatar;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarDao
{
    Avatar queryById(int p0);
    
    List<Avatar> queryAll();
    
    int queryByImgPath(String p0);
}
