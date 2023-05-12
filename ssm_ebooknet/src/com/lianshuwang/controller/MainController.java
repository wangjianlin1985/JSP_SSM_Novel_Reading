// 
// 
// 

package com.lianshuwang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.lianshuwang.helper.RankingBook;
import java.util.List;
import com.lianshuwang.util.PropertyConfigurer;
import org.springframework.ui.Model;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.BookService;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;

@Controller
public class MainController
{
    private static final Log logger;
    @Autowired
    private BookService bookService;
    
    static {
        logger = LogFactory.getLog((Class)MainController.class);
    }
    
    @RequestMapping({ "/index" })
    public String index(final Model model) {
        MainController.logger.info((Object)"Welcome to gatheringbook.cn!");
        final String name = (String)PropertyConfigurer.getProperty("book_path");
        System.out.println("hello, " + name);
        final int sumOfBooks = this.bookService.queryNumberOfBooks();
        model.addAttribute("sumOfBooks", (Object)sumOfBooks);
        final List<Integer> everyTypeBooks = this.bookService.queryNumberOfSomeTypeBooks();
        model.addAttribute("sumOfTypeBooks", (Object)everyTypeBooks);
        final String maxUploadDate = this.bookService.getMaxUploadDate();
        model.addAttribute("maxUploadDate", (Object)maxUploadDate);
        final List<RankingBook> rankingBooks = this.bookService.queryByUploadedDate();
        model.addAttribute("rankingBooks", (Object)rankingBooks);
        final List<RankingBook> rankingBooks2 = this.bookService.queryByDownloadTimes();
        model.addAttribute("rankingBooks1", (Object)rankingBooks2);
        return "main";
    }
}
