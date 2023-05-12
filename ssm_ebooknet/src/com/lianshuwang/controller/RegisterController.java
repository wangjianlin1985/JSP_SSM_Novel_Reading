// 
// 
// 

package com.lianshuwang.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import com.lianshuwang.domin.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController
{
    @Autowired
    private UserService userService;
    private static final Log logger;
    
    static {
        logger = LogFactory.getLog((Class)RegisterController.class);
    }
    
    @RequestMapping({ "userCodeCheck" })
    @ResponseBody
    public Map<String, Integer> checkUserCode(final String userCode) {
        final Map<String, Integer> resultMap = new HashMap<String, Integer>();
        final int count = this.userService.checkUserCode(userCode);
        resultMap.put("isRegistered", count);
        return resultMap;
    }
    
    @RequestMapping(value = { "register" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> register(@RequestBody final User user) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        this.userService.addUser(user);
        RegisterController.logger.info((Object)"you have registered!");
        return resultMap;
    }
}
