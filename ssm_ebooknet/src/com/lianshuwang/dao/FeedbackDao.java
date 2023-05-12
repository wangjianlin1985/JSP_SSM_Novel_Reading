// 
// 
// 

package com.lianshuwang.dao;

import java.util.List;
import com.lianshuwang.domin.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackDao
{
    int addFeedback(Feedback p0);
    
    List<Feedback> queryAllNotRead();
    
    int setOneRead(int p0);
    
    int setAllRead();
}
