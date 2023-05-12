// 
// 
// 

package com.lianshuwang.controller;

import com.lianshuwang.domin.Feedback;
import com.lianshuwang.domin.User;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import com.lianshuwang.helper.doBookHelper;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.BackStageService;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/backstage" })
public class BackStageController
{
    private static final Log logger;
    @Autowired
    private BackStageService backStageService;
    
    static {
        logger = LogFactory.getLog((Class)BackStageController.class);
    }
    
    @RequestMapping({ "/" })
    public String getLogin() {
        return "backstage/adminLogin";
    }
    
    @RequestMapping({ "/backlogin" })
    public String login(final String username, final String password, final Model model, final HttpSession session) {
        final boolean isLogin = this.backStageService.getLogin(username, password);
        if (isLogin) {
            session.setAttribute("status", (Object)true);
            return "redirect:bookManage";
        }
        model.addAttribute("error", (Object)"\u767b\u9646\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
        return "backstage/adminLogin";
    }
    
    @RequestMapping({ "/bookManage" })
    public String bookManage(final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<doBookHelper> bookList = this.backStageService.getUploadBooks();
        model.addAttribute("bookList", (Object)bookList);
        return "backstage/bookManage";
    }
    
    @RequestMapping({ "/searchBookByDays" })
    public String searchBookByDays(final int days, final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<doBookHelper> bookList = this.backStageService.getBooksByDays(days);
        model.addAttribute("bookList", (Object)bookList);
        return "backstage/bookManage";
    }
    
    @RequestMapping({ "/searchBookByTitle" })
    public String searchBookByTitle(final String title, final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<doBookHelper> bookList = this.backStageService.getBooksByTitle(title);
        model.addAttribute("bookList", (Object)bookList);
        return "backstage/bookManage";
    }
    
    @RequestMapping({ "/searchBookByUser" })
    public String searchBookByUser(final long userId, final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<doBookHelper> bookList = this.backStageService.getBooksByUserId(userId);
        model.addAttribute("bookList", (Object)bookList);
        return "backstage/bookManage";
    }
    
    @RequestMapping({ "/deleteBook" })
    @ResponseBody
    public Map<String, Object> deleteBook(final long bookId) {
        BackStageController.logger.info((Object)"you are removing a book!");
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        final int result = this.backStageService.deleteBook(bookId);
        resultMap.put("result", result);
        return resultMap;
    }
    
    @RequestMapping({ "/userManage" })
    public String userManage(final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final Map<String, Object> resultMap = this.backStageService.getUserByContribution();
        final List<User> userList = resultMap.get("userList");
        final int totalUser = resultMap.get("totalUser");
        final int weekUser = resultMap.get("weekUser");
        final int monthUser = resultMap.get("monthUser");
        model.addAttribute("userList", (Object)userList);
        model.addAttribute("weekUser", (Object)weekUser);
        model.addAttribute("monthUser", (Object)monthUser);
        model.addAttribute("totalUser", (Object)totalUser);
        return "backstage/userManage";
    }
    
    @RequestMapping({ "/deleteUser" })
    @ResponseBody
    public Map<String, Integer> deleteUser(final long userId) {
        BackStageController.logger.info((Object)"you are removing a user!");
        final Map<String, Integer> resultMap = new HashMap<String, Integer>();
        final int result = this.backStageService.deleteUser(userId);
        resultMap.put("result", result);
        return resultMap;
    }
    
    @RequestMapping({ "/searchUser" })
    public String searchUser(final String user, final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<User> userList = this.backStageService.getUserBySearch(user);
        final Map<String, Object> resultMap = this.backStageService.getUserByContribution();
        final int totalUser = resultMap.get("totalUser");
        final int weekUser = resultMap.get("weekUser");
        final int monthUser = resultMap.get("monthUser");
        model.addAttribute("userList", (Object)userList);
        model.addAttribute("weekUser", (Object)weekUser);
        model.addAttribute("monthUser", (Object)monthUser);
        model.addAttribute("totalUser", (Object)totalUser);
        return "backstage/userManage";
    }
    
    @RequestMapping({ "/feedbackManage" })
    public String feedbackManage(final Model model, final HttpSession session) {
        if (session.getAttribute("status") == null) {
            return "backstage/adminLogin";
        }
        final List<Feedback> feedbackList = this.backStageService.getFeedback();
        model.addAttribute("feedbackNum", (Object)feedbackList.size());
        model.addAttribute("feedbackList", (Object)feedbackList);
        return "backstage/feedbackManage";
    }
    
    @RequestMapping({ "/setRead" })
    @ResponseBody
    public void setFeedbackRead(final int feedbackId) {
        this.backStageService.setOneFeedbackRead(feedbackId);
    }
    
    @RequestMapping({ "/setAllRead" })
    public String setAllFeedbackRead() {
        this.backStageService.setAllFeedbackRead();
        return "redirect:feedbackManage";
    }
}
