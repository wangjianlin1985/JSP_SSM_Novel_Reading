// 
// 
// 

package com.lianshuwang.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lianshuwang.helper.UserHelper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import com.lianshuwang.domin.User;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController
{
    @Autowired
    private UserService userService;
    private static final Log logger;
    
    static {
        logger = LogFactory.getLog((Class)LoginController.class);
    }
    
    @RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> index(@RequestBody final User user, final HttpSession session) {
        LoginController.logger.info((Object)"you are logging in!");
        final String userCode = user.getUserCode();
        final String password = user.getUserPassword();
        final UserHelper userHelper = this.userService.getLoginUser(userCode, password);
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        if (userHelper != null) {
            session.setAttribute("userHelper", (Object)userHelper);
            resultMap.put("isLogined", true);
            resultMap.put("user", userHelper);
        }
        else {
            resultMap.put("isLogined", false);
        }
        return resultMap;
    }
    
    @RequestMapping({ "/logout" })
    public String logout(final HttpSession session) {
        LoginController.logger.info((Object)"you have logged out!");
        session.invalidate();
        return "redirect:/";
    }
}
