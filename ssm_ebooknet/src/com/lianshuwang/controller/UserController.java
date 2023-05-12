// 
// 
// 

package com.lianshuwang.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import com.lianshuwang.helper.UploadHelper;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.lianshuwang.domin.User;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lianshuwang.helper.UserHelper;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
    @Autowired
    private UserService userService;
    private static final Log logger;
    
    static {
        logger = LogFactory.getLog((Class)UserController.class);
    }
    
    @RequestMapping({ "/person" })
    public String getPerosonInfo(final Model model, final HttpSession session) {
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        if (userHelper == null) {
            return "redirect:index";
        }
        model.addAttribute("user", (Object)userHelper);
        UserController.logger.info((Object)("The user(" + userHelper.getUserName() + ") is coming to person information page!"));
        return "personInfo";
    }
    
    @RequestMapping({ "/infoModify" })
    public String infoModify(final String name, final String email, final String avatarImg, final HttpSession session) {
        UserController.logger.info((Object)"The user is modifying his information!");
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        final User user = new User();
        user.setId(userHelper.getId());
        user.setUserName(name);
        user.setEmail(email);
        final int avatarId = this.userService.getAvatarId(avatarImg);
        user.setAvatarNum(avatarId);
        this.userService.updateUserInfo(user);
        final User user2 = this.userService.queryById(userHelper.getId());
        final UserHelper newUserHelper = this.userService.getLoginUser(user2.getUserCode(), user2.getUserPassword());
        session.setAttribute("userHelper", (Object)newUserHelper);
        return "redirect:/person";
    }
    
    @RequestMapping({ "/pwdModify" })
    public String pwdModify(final String newPwd, final HttpSession session) {
        UserController.logger.info((Object)"The user is modifying his password!");
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        this.userService.updateUserPassword(userHelper.getId(), newPwd);
        session.invalidate();
        return "redirect:/index";
    }
    
    @RequestMapping({ "/getUploadHistory" })
    @ResponseBody
    public Map<String, Object> getUploadHistory(final HttpSession session) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        final List<UploadHelper> uploadHelperList = this.userService.getUploadedBook(userHelper.getId());
        resultMap.put("uploadList", uploadHelperList);
        UserController.logger.info((Object)"you are looking up the uploaded books");
        return resultMap;
    }
    
    @RequestMapping({ "/feedback" })
    @ResponseBody
    public void feedback(final String contact, final String suggestion, final HttpSession session) {
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        this.userService.addFeedback(userHelper.getId(), contact, suggestion);
        UserController.logger.info((Object)"you are posting the suggestion!");
        UserController.logger.info((Object)("contact:" + contact));
        UserController.logger.info((Object)("suggestion:" + suggestion));
    }
}
