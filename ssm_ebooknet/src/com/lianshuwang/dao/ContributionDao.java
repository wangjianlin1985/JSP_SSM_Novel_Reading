// 
// 
// 

package com.lianshuwang.dao;

import com.lianshuwang.domin.Contribution;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionDao
{
    Contribution queryByValue(int p0);
}
