// 
// 
// 

package com.lianshuwang.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.lianshuwang.domin.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao
{
    User queryById(long p0);
    
    User queryByLogin(@Param("userCode") String p0, @Param("userPassword") String p1);
    
    int queryByUserCode(String p0);
    
    int addUser(User p0);
    
    int updateUserPassword(@Param("id") long p0, @Param("userPassword") String p1);
    
    int updateUserContribution(@Param("addValue") int p0, @Param("id") long p1);
    
    int updateUserInfo(User p0);
    
    List<User> queryUserByContribution();
    
    int queryUserNumber();
    
    int queryUserNumberByWeek();
    
    int queryUserNumberByMonth();
    
    int deleteUser(long p0);
    
    List<User> queryUserByUserName(String p0);
}
